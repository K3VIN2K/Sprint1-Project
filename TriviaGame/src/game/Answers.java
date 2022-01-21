package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Answers {
	private int code;
	private int totalAnswers;
	private int totalRight;

	Answers() {
		code = 0;
		totalAnswers = 0;
		totalRight = 0;
	}

	private String Load() {
		try {
			String answer = new String();
			File f1 = new File("Answers.txt");
			Scanner fRead = new Scanner(f1);
			while (fRead.hasNextLine()) {
				String[] line = fRead.nextLine().split(":");
				if (Integer.valueOf(code).equals(Integer.valueOf(line[0]))) {
					answer = line[1];
				}
			}
			fRead.close();
			return answer;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public String Check(String a) {
		try {
			String[] answer = Load().split(",");
			String[] player = a.split(",");
			System.out.println(Arrays.toString(player));
			int numA = answer.length;
			int numRight = numA;
			int Count = 0;
			totalAnswers += numA;
			ArrayList<String> answers = new ArrayList<>();
			ArrayList<String> Stringanswers = new ArrayList<>();
			ArrayList<String> wrongAnswers = new ArrayList<>();
			for (int i = 0; i < answer.length; i++) {
				answers.add(answer[i].toLowerCase().replaceAll(" ", ""));
				Stringanswers.add(answer[i]);
			}
			System.out.println(answers.toString());
			if (numA >= player.length) {
				Count = numA;
			} else {
				Count = player.length;
			}
			for (int i = 0; i < Count; i++) {
				if (answers.contains(player[i].toLowerCase().replaceAll(" ", ""))) {
					Stringanswers.remove(answers.indexOf(player[i].toLowerCase().replaceAll(" ", "")));
					answers.remove(player[i].toLowerCase().replaceAll(" ", ""));
				} else {
					if (numRight > 0) {
						numRight--;
					}
					wrongAnswers.add(player[i]);
				}
			}
			totalRight += numRight;
			String info = new String("You Missed:  " + Stringanswers.toString().replace("[", "").replace("]", "") + "\n" +
					"Incorrect Answers:  " + wrongAnswers.toString().replace("[", "").replace("]", ""));
			if (getPercent(numRight, numA) == 100) {
				return "Correct: \n" + getPercent(numRight, numA) + "%";
			} else if (getPercent(numRight, numA) >= 50){
				return "Correct: \n" + getPercent(numRight, numA) + "% \n" + info;
			} else {
				return "Incorrect \n" + getPercent(numRight, numA) + "% \n" + info;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	public void SetCode(String q) {
		code = Integer.valueOf(q.split(",")[1]);
	}

	private double getPercent(int a, int b) {
		return ((double) a / (double) b) * 100;
	}

	public String scoreString() {
		String score = new String("Correct Answers:  " + totalRight + "/" + totalAnswers + "\n Percent Correct:  "
				+ getPercent(totalRight, totalAnswers) + "%");
		return score;
	}
	public void reset() {
		totalAnswers = 0;
		totalRight = 0;
	}
}
