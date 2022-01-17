package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Questions {
	private String c;
	private String d;
	private int num;
	private int count;
	private ArrayList<String> questions = new ArrayList<>();
	Questions(String category, String difficulty, int numQ){
		c = category;
		d = difficulty;
		num = numQ;
		count = 0;
	}
	Questions(){
		
	}
	
	public void Load() {
		try {
			File f1 = new File("Questions.txt");
			Scanner fRead = new Scanner(f1);
			while (fRead.hasNextLine()) {
				String[] line = fRead.nextLine().split(",");
				if (line[0].equals(c) && line[1].equals(d)) {
					questions.add(line[2] + "," + line[3]);
				}
			}
			fRead.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String getQuestion() {
		Random rand = new Random();
		int index;
		index = rand.nextInt(questions.size());
		count ++;
		return questions.get(index);
	}
	public String getNext() {
		if (count < num) {
			return getQuestion();
		} else {
			return "";
		}
	}
}