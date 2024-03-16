package Buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * Class for creating the remove button to remove selected image file paths from the image listview.
 *
 * @author Yuye Andreas Meier
 */
public class removebutton {

	private Button removebtn;
	
	public removebutton() {}

	/**
	 * Method to create the pathbtn.
	 * @param listview which holds the image files.
	 * @return returns the removebtn.
	 */
	public Button createremovebtn(ListView<String> listview) {
		removebtn = new Button();
		removebtn.setGraphic(new Images().createdelimage());
		removebtn.getStyleClass().add("button-chooser");

		/**
		 * Event handler for removebtn.
		 * Removes selected images from listview.
		 */
		removebtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				
				//If item selected
				if(listview.getSelectionModel().getSelectedIndex() != -1) {
		            listview.getItems().remove(listview.getSelectionModel().getSelectedIndex()); 
		
		            //Disables remove button if listview is empty
		            if (listview.getItems().size() == 0) {
		            	removebtn.setDisable(true);
					}
				}
			}
		});
		
			
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(removebtn);
		removebtn.setDisable(true); //Disables the button
		return removebtn;
	}
}
