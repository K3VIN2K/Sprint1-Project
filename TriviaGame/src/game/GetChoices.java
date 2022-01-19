package game;

import javafx.application.Application;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

public class GetChoices extends Application{
	
	private String category = new String();
	private String difficulty = new String();
	
	public void start(Stage Stage) throws Exception {
		
	}
	
	public void showChoice() throws Exception {
		ChoiceDialog getCategory = new ChoiceDialog("cat1","test","test","test","cat4","cat5");
		ChoiceDialog getDifficulty = new ChoiceDialog("easy","easy","normal","hard");
		getCategory.show();
		while (difficulty == ""){
			getCategory.setOnCloseRequest(e -> {
				category = getCategory.getSelectedItem().toString();
				getDifficulty.show();
			});
			getDifficulty.setOnCloseRequest(e->{
				difficulty = getDifficulty.getSelectedItem().toString();
			});	
		}
	}
	
	GetChoices(){
		try {
			showChoice();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getCategory() {
		return category;
	}
	public String getDifficulty() {
		return difficulty;
	}
}
