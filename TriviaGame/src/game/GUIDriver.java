package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	@Override
	public void start(Stage Stage) throws Exception {
		BorderPane bp = new BorderPane();
		VBox Screen = new VBox(20);
		TextField answerBox = new TextField();
		Label questionBox = new Label();
		Button check = new Button("Check");
		answerBox.setMaxWidth(200);
		Screen.getChildren().addAll(questionBox, answerBox, check);
		bp.setCenter(Screen);
		Screen.setAlignment(Pos.CENTER);
		
		Questions question = new Questions("test", "easy", 3);
		Answers answer = new Answers();
		question.Load();
		
		Scene scene = new Scene(bp,400,400);
		Stage.setScene(scene);
		Stage.show();
		
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
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
/*
  Scanner in = new Scanner(System.in);
		Questions question = new Questions("test", "easy");
		Answers answer = new Answers();
		question.Load();
		System.out.println("How many questions?");
		int numQuestions = in.nextInt();
		for (int i = 0; i < numQuestions; i++) {
			String Question = question.getQuestion();
			answer.SetCode(Question);
			System.out.println(Question.split(",")[0]);
			String input = in.nextLine();
			if (answer.Check(input)) {
				System.out.println("Correct");
			} else {
				System.out.println("inCorrect");
			}
		}
	}
	*/
