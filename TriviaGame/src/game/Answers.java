package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Answers{
	private int code;
	
	Answers(){
		code = 0;
	}
	
	private String Load(){
		String answer = new String();
		try {
			File f1 = new File("Answers.txt");
			Scanner fRead = new Scanner(f1);
			while (fRead.hasNextLine()) {
				String[] line = fRead.nextLine().split(":");
				if (Integer.valueOf(line[0]) == code) {
					answer = line[1];
				}
			}
			fRead.close();
			return answer;
		} catch (Exception e){
			return answer;
		}
	}
	
	public Boolean Check(String a){
		String[] answer = Load().split(",");
		String[] player = a.split(",");
		if (Arrays.equals(answer, player)) {
			return true;
		} else {
			return false;
		}
	}
	public void SetCode(String q){
		code = Integer.valueOf(q.split(",")[1]);
	}
}
