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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.stage.Stage;

public class GUIDriver extends Application {
	
	private String category = new String();
	private String difficulty = new String();

	@Override
	public void start(Stage Stage) throws Exception {
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
					Stage.close();
				} else {
					e.consume();
				}
			});
		});
		
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
		Button check = new Button("Check");
		Button end = new Button("End Game");
		
		Media test = new Media("");
		
		buttons.getChildren().addAll(check, end);
		buttons.setAlignment(Pos.CENTER);
		answerBox.setMaxWidth(200);
		Screen.getChildren().addAll(questionBox, answerBox, buttons, score);
		bp.setCenter(Screen);
		Screen.setAlignment(Pos.CENTER);
		
		Questions question = new Questions(category, difficulty);
		Answers answer = new Answers();
		
		Scene scene = new Scene(bp,400,400);
		String Question = question.getNext();
		answer.SetCode(Question);
		questionBox.setText(Question.split(",")[0]);
		
		check.setOnAction(e ->{
			try {
				String currentQ = new String();
				check.setDisable(true);
				score.setText(answer.Check(answerBox.getText()));
				if (question.gameOn()) {
					currentQ = question.getNext();
					questionBox.setText(currentQ.split(",")[0]);
					answer.SetCode(currentQ);
					check.setDisable(false);
				}
				answerBox.setText("");
			} catch (Exception v) {
				System.out.println(v.getMessage());
			}
		});
		end.setOnAction(e -> {
			score.setText(answer.scoreString());
			Alert restart = new Alert(AlertType.CONFIRMATION);
			restart.setHeaderText("New Game?");
			restart.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					score.setText("");
					getCategory();
					getDifficulty();
					question.setNew(category, difficulty);
					String currentQ = new String();
					currentQ = question.getNext();
					questionBox.setText(currentQ.split(",")[0]);
					answer.SetCode(currentQ);
					check.setDisable(false);
				}
			});
		});
		return scene;
	}
	
	private void getCategory() {
		ChoiceDialog getCategory = new ChoiceDialog("All","test","test","test","cat4","cat5","All");
		getCategory.setTitle("Category Selection");
		getCategory.setHeaderText("Choose Category");
		getCategory.setOnCloseRequest(e -> {
			category = getCategory.getSelectedItem().toString();
			System.out.println(category);
		});
		getCategory.showAndWait();
	}
	private void getDifficulty() {
		ChoiceDialog getDifficulty = new ChoiceDialog("All","easy","normal","hard","All");
		getDifficulty.setTitle("Difficulty Selection");
		getDifficulty.setHeaderText("Choose Difficulty");
		getDifficulty.setOnCloseRequest(e->{
			difficulty = getDifficulty.getSelectedItem().toString();
			System.out.println(difficulty);
		});
		getDifficulty.showAndWait();
	}

}
