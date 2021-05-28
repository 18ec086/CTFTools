package ctfTool;

import GUIs.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage){
		Initial init=new Initial(primaryStage);
		init.makeInitScene();
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Application.launch(args);
		System.out.println("完了");
	}

}
