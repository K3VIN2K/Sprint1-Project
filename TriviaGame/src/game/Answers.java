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
	
	public double Check(String a) throws Exception{
		String[] answer = Load().split(",");
		String[] player = a.split(",");
		int numA = answer.length;
		int numRight = 0;
		totalAnswers += numA;
		ArrayList<String> answers = new ArrayList<>();
		for (int i = 0; i < answer.length; i++) {
			answers.add(answer[i]);
		}
		System.out.println(answers.toString());
		for (int i = 0; i < answers.size(); i++) {
			if (answers.contains(player[i])) {
				answers.remove(player[i]);
				numRight ++;
				totalRight ++;
			}
		}
		return (double) numRight/ (double) numA;
	}
	public void SetCode(String q){
		code = Integer.valueOf(q.split(",")[1]);
	}
}
