package GUIs;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Initial {
	private Stage stage;
	String[] btnStr= {"Close","シーザー暗号","数字アルファベット変換"};
	Button[] btn =new Button[btnStr.length];
	HBox[] hb=new HBox[btnStr.length];
	VBox vb;
	
	
	public Initial(Stage stage) {
		this.stage=stage;
		for(int i=0;i<btnStr.length;i++) {
			btn[i]=new Button(btnStr[i]);
			hb[i]=new HBox(btn[i]);
		}
		btn[0].setOnAction(e->stage.close());
		btn[1].setOnAction(e->btnClicked1());
	}
	
	public void makeInitScene() {
		vb=new VBox(hb[1],hb[2],hb[0]);
		Scene scene=new Scene(vb,300,400);
		stage.setScene(scene);
		stage.setTitle("Tools");
		stage.show();
	}
	
	private void btnClicked1() {
		Stage stage1=new Stage();
		CaesarGUI a=new CaesarGUI(stage,stage1);
		a.doCaesar();
	}
	
	
	
}
