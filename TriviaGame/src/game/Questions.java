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
	private boolean playing;
	Questions(String category, String difficulty){
		c = category;
		d = difficulty;
		count = 0;
		playing = true;
		num = 0;
		Load();
	}
	/**
	 * resets questions with a new category and difficulty
	 * used for restarting the game
	 * @param category
	 * @param difficulty
	 */
	public void reset(String category, String difficulty) {
		c = category;
		d = difficulty;
		count = 0;
		playing = true;
		num = 0;
		Load();
	}
	
	private void Load() {
		questions.clear();
		try {
			File f1 = new File("Questions.txt");
			Scanner fRead = new Scanner(f1);
			while (fRead.hasNextLine()) {
				String[] line = fRead.nextLine().split(",");
				if (c.equals("All") && d.equals("All")) {
					questions.add(line[2] + "," + line[3]);
				} else if (c.equals("All")) {
					if (line[1].equals(d)) {
						questions.add(line[2] + "," + line[3]);
					}
				} else if (d.equals("All")){
					if (line[0].equals(c)) {
						questions.add(line[2] + "," + line[3]);
					}
				} else {
					if (line[0].equals(c) && line[1].equals(d)) {
						questions.add(line[2] + "," + line[3]);
					}
				}
			}
			num = questions.size();
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
	
	/**
	 * randomly selects a new question within selected category and difficulty
	 * @return String "question, answer code"
	 */
	public String getNext() {
		if (count < num) {
			playing = true;
			return getQuestion();
		} else {
			playing = false;
			return "Finished";
		}
	}
	/**
	 * returns whether the game is playing
	 * @return boolean true when game is playing
	 */
	public boolean gameOn() {
		return playing;
	}
}