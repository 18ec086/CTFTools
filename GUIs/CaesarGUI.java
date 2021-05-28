package GUIs;

import java.util.HashMap;
import java.util.Map;

import functions.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CaesarGUI {
	private Stage preStage;
	private Stage stage;
	private Stage stageHist1=new Stage();
	private Stage stageHist2=new Stage();
	private Label label1	=new Label("復号前");
	private Label label2	=new Label("復号後");
	private Button btn		=new Button("Submit");
	private Button btnClose =new Button("Close");
	private Button btnRst	=new Button("Reset");
	private Button btnCpy	=new Button("Copy");
	private Button btnPaste =new Button("Paste");
	private Button btnNext	=new Button("next");
	private Button btnHist1	=new Button("ヒストグラム");
	private Button btnHist2	=new Button("ヒストグラム");
	private TextField tf1	=new TextField();
	private TextField tf2	=new TextField();
	private HBox[] hb		=new HBox[4];
	private VBox vb ;
	private Scene scene;
	private Clipboard clipboard		=Clipboard.getSystemClipboard();
	private final ComboBox<String> cb=new ComboBox<String>();
	private Caesar caesar;
	private final String[] large	= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	
	//コンストラクタ
	public CaesarGUI(Stage preStage,Stage stage){
		this.preStage=preStage;
		this.stage=stage;
		setInitSetting();	
	}
	
	private void setInitSetting() {
		
		tf1.setPrefSize(200,50);
		tf2.setPrefSize(200,50);
		tf2.setEditable(false);
		
		btn.setOnAction(e->btnClicked());
		//btn.setPadding(new Insets(10));
		btnClose.setOnAction(e->closeClicked());
		//btnClose.setPadding(new Insets(10));
		btnRst.setOnAction(e->resetClicked());
		//btnRst.setPadding(new Insets(10));
		btnCpy.setOnAction(e ->copyClicked());
		btnPaste.setPrefWidth(60);
		btnPaste.setOnAction(e->pasteClicked());
		btnNext.setOnAction(e->nextClicked());
		btnHist1.setOnAction(e->histClicked(stageHist1,tf1));
		btnHist2.setOnAction(e->histClicked(stageHist2,tf2));
		
		cb.getItems().addAll(
				"1","2","3","4","5","6","7","8","9","10",
				"11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25"
		);
		cb.setValue("13");
		
		
		hb[0]=new HBox(label1,tf1,cb,btnPaste,btnHist1);
		hb[1]=new HBox(btn,btnRst);
		hb[2]=new HBox(label2,tf2,btnNext,btnCpy,btnHist2);
		hb[3]=new HBox(btnClose);
		
		for(int i=0;i<hb.length;i++) {
			hb[i].setAlignment(Pos.TOP_LEFT);
			hb[i].setPadding(new Insets(10,10,10,10));
		}
		
		hb[1].setAlignment(Pos.CENTER);
		hb[3].setAlignment(Pos.CENTER);
		
		
		vb=new VBox(hb[0],hb[1],hb[2],hb[3]);
	}
	
	public void doCaesar() {
		scene=new Scene(vb,500,200);
		preStage.close();
		stage.setScene(scene);
		stage.setTitle("シーザー暗号");
		stage.show();
	}
	
	private void btnClicked() {
		int n=Integer.parseInt((String)cb.getValue());
		caesar=new Caesar(tf1.getText(),n);	
		tf2.setText(caesar.shiftStr());
	}
	private void closeClicked() {
		stage.close();
		preStage.show();
	}
	private void resetClicked() {
		tf1.setText("");
		tf2.setText("");
		cb.setValue("13");
		stageHist1.close();
		stageHist2.close();
	}
	
	private void copyClicked() {
		Alert alert=new Alert(AlertType.INFORMATION);
		final Map<DataFormat,Object> content=new HashMap<>();
		content.put(DataFormat.PLAIN_TEXT,tf2.getText());
		clipboard.setContent(content);
		alert.setTitle("通知");
		alert.setHeaderText("クリップボードにコピーしました。");
		alert.setContentText(null);
		alert.showAndWait();
	}
	private void pasteClicked() {
		resetClicked();
		tf1.setText(clipboard.getString());
		
	}
	
	private void nextClicked() {
		int n=Integer.parseInt((String) cb.getValue());
		cb.setValue(Integer.toString((n++)%25+1));
		btnClicked();
		
	}
	
	private void histClicked(Stage stage,TextField tf) {
		//btnClicked();
		HashMap<String,Integer> hmap=new HashMap<String,Integer>();
		hmap=setMap(tf.getText());
		HistogramGUI hist=new HistogramGUI(stage,hmap,tf.getText());
		hist.showHistogram();
	}
	
	private HashMap<String,Integer> setMap(String str) {
		HashMap<String,Integer> hmap=new HashMap<String,Integer>();
		String[] oneByone =new String[str.length()];
		for(int i=0;i<oneByone.length;i++)
			oneByone[i]=str.substring(i,i+1);
		for(int i=0;i<large.length;i++)
			hmap.put(large[i],cntStr(oneByone,large[i]));
		return hmap;
	}
	
	private int cntStr(String[]oneByone,String str) {//strの個数を数える。
		int cnt=0;
		str=str.toLowerCase();
		for(int i=0;i<oneByone.length;i++) {
			if(str.equals(oneByone[i].toLowerCase()))
				cnt++;
		}
		return cnt;
	}
	
	
}
