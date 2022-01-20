package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Answers{
	private int code;
	private int totalAnswers;
	private int totalRight;
	
	Answers(){
		code = 0;
		totalAnswers = 0;
		totalRight = 0;
	}
	
	private String Load() throws Exception{
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
	}
	
	public String Check(String a) throws Exception{
		String[] answer = Load().split(",");
		String[] player = a.toLowerCase().replaceAll(" ","").split(",");
		System.out.println(Arrays.toString(player));
		int numA = answer.length;
		int numRight = 0;
		totalAnswers += numA;
		ArrayList<String> answers = new ArrayList<>();
		for (int i = 0; i < answer.length; i++) {
			answers.add(answer[i].toLowerCase());
		}
		System.out.println(answers.toString());
		for (int i = 0; i < numA; i++) {
			if (answers.contains(player[i])) {
				answers.remove(player[i]);
				numRight ++;
				totalRight ++;
			}
		}
		if (getPercent(numRight, numA) > 50) {
			return "Correct \n"+getPercent(numRight, numA)+"%";
		}else {
			return "Incorrect \n"+getPercent(numRight, numA)+"%";
		}
	}
	public void SetCode(String q){
		code = Integer.valueOf(q.split(",")[1]);
	}
	private double getPercent(int a,int b) {
		return ((double) a/ (double) b) * 100;
	}
	public String scoreString() {
		String score = new String("Correct Answers:  " + totalRight + "/" + totalAnswers
				+ "\n Percent Correct:  "+getPercent(totalRight,totalAnswers) + "%");
		return score;
	}
}
