package main;

import Buttons.pathbutton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import language.LanguageSettings;

public class modelpath {

	private Label pathlabel;
	private ListView<String> listview;
	private Button pathbtn;
	private HBox pathc;
	
	public modelpath() {}
	
	public HBox createmodelpath() {
		pathlabel = new Label(LanguageSettings.get("mimport.mpath"));
		pathlabel.setPrefWidth(150);
		pathlabel.setPadding(new Insets(4, 0, 0, 0));
		pathlabel.getStyleClass().add("label");
		listview = new ListView<String>();
		listview.setPrefSize(350, 24);
		listview.getStyleClass().add("fillin");		
		pathbtn = new pathbutton().createpathbtn(listview);
		pathc = new HBox();
		pathc.getChildren().add(pathlabel);
		pathc.getChildren().add(listview);
		pathc.getChildren().add(pathbtn);
		pathc.setPadding(new Insets(10, 0, 5, 0));
		return pathc;
	}
	
	public ListView<String> getpathlistview(){
		return listview;
	}
}
