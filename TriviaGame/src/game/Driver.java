package game;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
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

}
/**
 * choose category
 * choose difficulty
 * generate q bank
 * category, difficulty, question, answer code
 * randomly select q
 * user answers
 * check with answers.txt using answer code
 */