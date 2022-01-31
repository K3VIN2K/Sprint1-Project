package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	private String category = new String();
	private String difficulty = new String();
	MusicDriver music = new MusicDriver();

	@Override
	public void start(Stage Stage) {
		try {
			getCategory();
			getDifficulty();
			Stage.setScene(GameScreen());
			Stage.setTitle("Trivia");
			Stage.show();

			Stage.setOnCloseRequest(e -> {
				Alert confirmClose = new Alert(AlertType.CONFIRMATION);
				confirmClose.setHeaderText("Close?");
				confirmClose.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						music.stop();
						Stage.close();
					} else {
						e.consume();
					}
				});
			});
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public Scene GameScreen() {
		BorderPane bp = new BorderPane();
		VBox Screen = new VBox(20);
		HBox buttons = new HBox(20);
		TextField answerBox = new TextField();
		Label questionBox = new Label();
		Label score = new Label();
		Label help = new Label("multiple answrs should be separated with commas ',' ");
		Button check = new Button("Check");
		Button end = new Button("End Game");
		Button playMusic = new Button("Play Music");
		score.setTextAlignment(TextAlignment.CENTER);

		buttons.getChildren().addAll(playMusic, check, end);
		buttons.setAlignment(Pos.CENTER);
		answerBox.setMaxWidth(200);
		Screen.getChildren().addAll(questionBox, answerBox, buttons, score, help);
		help.setAlignment(Pos.BOTTOM_CENTER);
		bp.setCenter(Screen);
		Screen.setAlignment(Pos.CENTER);

		Questions question = new Questions(category, difficulty);
		Answers answer = new Answers();

		Scene scene = new Scene(bp, 400, 400);
		String Question = question.getNext();
		answer.SetCode(Question);
		questionBox.setText(Question.split(",")[0]);

		check.setOnAction(e -> {
			String currentQ = new String();
			check.setDisable(true);
			score.setText(answer.Check(answerBox.getText()));
			currentQ = question.getNext();
			if (question.gameOn()) {
				questionBox.setText(currentQ.split(",")[0]);
				answer.SetCode(currentQ);
				check.setDisable(false);
			} else {
				questionBox.setText(currentQ);
			}
			answerBox.setText("");
		});
		playMusic.setOnAction(e -> {
			try {
				music.start();
				Alert info = new Alert(AlertType.INFORMATION);
				info.setContentText("visit at simulatorradio.com");
				info.setHeaderText("Now Playing Simulator Radio");
				info.setTitle("Playing Music");
				info.show();
			} catch (Exception v) {
				System.err.println(v.getMessage());
				System.out.println("Ignore Me!");
			}
		});
		end.setOnAction(e -> {
			score.setText(answer.scoreString());
			Alert restart = new Alert(AlertType.CONFIRMATION);
			restart.setHeaderText("New Game?");
			restart.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					score.setText("");
					answerBox.setText("");
					getCategory();
					getDifficulty();
					question.reset(category, difficulty);
					String currentQ = new String();
					currentQ = question.getNext();
					questionBox.setText(currentQ.split(",")[0]);
					answer.SetCode(currentQ);
					answer.reset();
					check.setDisable(false);
				}
			});
		});
		return scene;
	}

	private void getCategory() {
		ChoiceDialog getCategory = new ChoiceDialog("All", "Geography", "Automotive", "Science", "History", "All");
		getCategory.setTitle("Category Selection");
		getCategory.setHeaderText("Choose Category");
		getCategory.setOnCloseRequest(e -> {
			category = getCategory.getSelectedItem().toString();
			System.out.println(category);
		});
		getCategory.showAndWait();
	}

	private void getDifficulty() {
		ChoiceDialog getDifficulty = new ChoiceDialog("All", "Easy", "Normal", "Hard", "All");
		getDifficulty.setTitle("Difficulty Selection");
		getDifficulty.setHeaderText("Choose Difficulty");
		getDifficulty.setOnCloseRequest(e -> {
			difficulty = getDifficulty.getSelectedItem().toString();
			System.out.println(difficulty);
		});
		getDifficulty.showAndWait();
	}

}
