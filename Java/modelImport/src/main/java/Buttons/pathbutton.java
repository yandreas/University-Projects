package Buttons;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import language.LanguageSettings;

/**
 * Class for creating the button to choose the modelpath, which opens the filechooser to select the path.
 *
 * @author Yuye Andreas Meier
 */
public class pathbutton {

	private Button pathbtn;
	private DirectoryChooser directorychooser;
	private File selectedFile;
	
	public pathbutton() {}

	/**
	 * Method to create the pathbtn.
	 * @param listview which holds the filepath to the model directory.
	 * @return returns the pathbtn.
	 */
	public Button createpathbtn(ListView<String> listview) {
		pathbtn = new Button();
		pathbtn.setGraphic(new Images().createopenimage());
		pathbtn.getStyleClass().add("button-chooser");

		/**
		 * Event handler for pathbtn.
		 * Filechooser for one file path (path of model directory).
		 */
		pathbtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
			directorychooser = new DirectoryChooser();
			directorychooser.setTitle(LanguageSettings.get("mimport.mpath"));
			selectedFile = directorychooser.showDialog(null);

			if (selectedFile != null){
				//clear listview if one item is inside already (by adding the chosen directory it more or less is replaced).
				if(listview.getItems().size() == 1) {
					listview.getItems().clear();
				}
				//Inserts path of selected directory
				listview.getItems().add(selectedFile.getAbsolutePath());
			}
		}
		});
		
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(pathbtn);
		return pathbtn;
	}
}
