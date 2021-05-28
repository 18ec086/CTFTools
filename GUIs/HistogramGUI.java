package GUIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class HistogramGUI {
	private Stage stage;
	private NumberAxis numberAxis=new NumberAxis();
	private CategoryAxis categoryAxis =new CategoryAxis();
	private Scene scene;
	private final String[] large	= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	public HashMap<String,Integer> hmap;
	private List<Series<Number, String>> seriesList;
	private String lblName;
	
	public HistogramGUI(Stage stage,HashMap<String,Integer> hmap,String lblName) {
		this.stage=stage;
		this.hmap=hmap;
		this.lblName=lblName;
		setInitialSetting();
	}
	private void setInitialSetting() {
		numberAxis.setLabel("頻度[回]");
		categoryAxis.setLabel("文字");
	}
	
	public void showHistogram() {
		seriesList = new ArrayList<XYChart.Series<Number,String>>();//
		//for(String seriesName:large) {
			Series<Number, String> series = new Series<Number, String>();
			
			for (int i=0;i<large.length;i++) {
				String tmp=large[i];
				int j=hmap.get(tmp);
				if(j!=0) 
            		series.getData().add(new XYChart.Data<>(j, tmp));
            }
			series.setName(lblName);
            seriesList.add(series);
		//}
		BarChart<Number,String> bc = new BarChart<>(numberAxis,categoryAxis);
        bc.setBarGap(2);
        bc.setCategoryGap(2);
        bc.getData().addAll(seriesList);
        scene  = new Scene(bc, 600, 600);
        stage.setScene(scene);
		stage.setTitle("頻度分析ヒストグラム");
		stage.show();
	}

	
}
