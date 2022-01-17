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
	
	public boolean Check(String a) throws Exception{
		String[] answer = Load().split(",");
		String[] player = a.split(",");
		System.out.println(Arrays.toString(answer));
		System.out.println(Arrays.toString(player));
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
