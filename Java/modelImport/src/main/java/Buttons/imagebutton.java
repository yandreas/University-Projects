package Buttons;

import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import language.LanguageSettings;

/**
 * Class for creating the image button, which opens the filechooser to select the needed image files if not parsed yet.
 *
 * @author Yuye Andreas Meier
 */
public class imagebutton {

	private Button imgbtn;
	private FileChooser filechooser;
	private List<File> selectedFiles;
	
	public imagebutton() {}

	/**
	 * Method to create the image button, which opens a filechooser to select the needed image files.
	 * @param listview image listview to add the images to.
	 * @return returns the image button.
	 */
	public Button createimagebtn(ListView<String> listview) {
		imgbtn = new Button();
		imgbtn.setGraphic(new Images().createopenimage());
		imgbtn.getStyleClass().add("button-chooser");

		/**
		 * Event handler for imgbtn.
		 * Filtered filechooser for multiple image file paths.
		 */
		imgbtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
			filechooser = new FileChooser();
			filechooser.setTitle(LanguageSettings.get("mimport.pngs"));
			filechooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg", "*.png"));
			selectedFiles = filechooser.showOpenMultipleDialog(null);
			
			
			if (selectedFiles != null){
				boolean neu;
				for (int i = 0; i < selectedFiles.size(); i++) {
					neu = true;
					for (int j = 0; j < listview.getItems().size(); j++) {
						//Inserts paths of selected files if they are not in the listview already
						if(listview.getItems().get(j).contentEquals(selectedFiles.get(i).getAbsolutePath())) {
							neu = false;
							break;
							}
						}
					if(neu == true) {
						listview.getItems().add(selectedFiles.get(i).getAbsolutePath());
					}
				}
			}
			}
		});
		
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(imgbtn);
		return imgbtn;
	}
}
