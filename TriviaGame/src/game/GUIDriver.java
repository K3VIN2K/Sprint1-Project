package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {
	
	private String category = new String();
	private String difficulty = new String();

	@Override
	public void start(Stage Stage) throws Exception {
		ChoiceDialog getCategory = new ChoiceDialog("cat1","test","test","test","cat4","cat5");
		ChoiceDialog getDifficulty = new ChoiceDialog("easy","easy","normal","hard");
		System.out.println(getDifficulty.getModality());
		getDifficulty.setOnCloseRequest(e->{
			difficulty = getDifficulty.getSelectedItem().toString();
			System.out.println(difficulty);
		});
		getCategory.setOnCloseRequest(e -> {
			category = getCategory.getSelectedItem().toString();
			System.out.println(category);
			getDifficulty.showAndWait();
		});
		getCategory.showAndWait();
		Stage.setScene(GameScreen());
		Stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public Scene GameScreen() {
		BorderPane bp = new BorderPane();
		VBox Screen = new VBox(20);
		TextField answerBox = new TextField();
		Label questionBox = new Label();
		Button check = new Button("Check");
		answerBox.setMaxWidth(200);
		Screen.getChildren().addAll(questionBox, answerBox, check);
		bp.setCenter(Screen);
		Screen.setAlignment(Pos.CENTER);
		
		Questions question = new Questions(category, difficulty, 3);
		Answers answer = new Answers();
		question.Load();
		
		Scene scene = new Scene(bp,400,400);
		String Question = question.getNext();
		answer.SetCode(Question);
		questionBox.setText(Question.split(",")[0]);
		
		check.setOnAction(e ->{
			try {
				String currentQ = new String();
				check.setDisable(true);
				System.out.println(answer.Check(answerBox.getText()));
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
		return scene;
	}
	
	/*public void startScreen() {
		ChoiceDialog getCategory = new ChoiceDialog("cat1","cat1","cat2","cat3","cat4","cat5");
		ChoiceDialog getDifficulty = new ChoiceDialog("easy","easy","normal","hard");
		getCategory.show();
		getCategory.setOnCloseRequest(e -> {
			category = getCategory.getSelectedItem().toString();
			getDifficulty.show();
		});
		getDifficulty.setOnCloseRequest(e->{
			difficulty = getDifficulty.getSelectedItem().toString();
		});
	}*/

}
