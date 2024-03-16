package main;

import Buttons.daebutton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import language.LanguageSettings;

/**
 * Class for dae HBox containing the label, listview for the dae file as well as the dae filechooser button.
 *
 * @author Yuye Andreas Meier
 */
public class daechooser {

	private Label daelabel;
	private ListView<String> listview; 
	private Button daebtn;
	private HBox daec;
	
	public daechooser() {}

	/**
	 * Method to create the dae listview, label and button and put it into an HBox with its own positions and styles.
	 *
	 * @param window needed for project logo.
	 * @param listview2 needed for parsing the image files.
	 * @return daec HBox.
	 */
	public HBox createdaechooser(ListView<String> listview2, Stage window) {
		daelabel = new Label(LanguageSettings.get("mimport.dpath"));
		daelabel.setPrefWidth(150);
		daelabel.setPadding(new Insets(4, 0, 0, 0));
		daelabel.getStyleClass().add("label");
		listview = new ListView<String>();
		listview.setPrefSize(350, 24);
		listview.getStyleClass().add("fillin");
		daebtn = new daebutton().createdaebtn(listview, listview2, window);
		daec = new HBox();
		daec.getChildren().add(daelabel);
		daec.getChildren().add(listview);
		daec.getChildren().add(daebtn);
		daec.setPadding(new Insets(0, 0, 5, 0));
		return daec;
	}

	/**
	 * Method to get daelistview.
	 *
	 * @return listview with dae file.
	 */
	public ListView<String> getdaelistview(){
		return listview;
	}
}
