package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	@Override
	public void start(Stage Stage) throws Exception {
		VBox Screen = new VBox(20);
		TextField answerBox = new TextField();
		Label questionBox = new Label();
		Button check = new Button("Check");
		Screen.getChildren().addAll(questionBox, answerBox, check);
		
		Questions question = new Questions("test", "easy", 3);
		Answers answer = new Answers();
		question.Load();
		
		Scene scene = new Scene(Screen,400,400);
		Stage.setScene(scene);
		Stage.show();
		
		String Question = question.getNext();
		answer.SetCode(Question);
		questionBox.setText(Question.split(",")[0]);
		
		check.setOnAction(e ->{
			try {
				String currentQ = new String();
				check.disarm();
				if (answer.Check(answerBox.getText())) {
					System.out.println("Correct");
				} else {
					System.out.println("inCorrect");
				}
				currentQ = question.getNext();
				questionBox.setText(currentQ.split(",")[0]);
				answer.SetCode(currentQ);
				check.arm();
			} catch (Exception v) {
				
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
