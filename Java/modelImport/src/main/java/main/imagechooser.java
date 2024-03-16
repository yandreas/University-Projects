package main;

import Buttons.imagebutton;
import Buttons.removebutton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import language.LanguageSettings;

/**
 * Class for image HBox containing the label, listview for the image files as well as the remove and image filechooser button.
 *
 * @author Yuye Andreas Meier
 */
public class imagechooser {

	private Label pnglabel;
	private ListView<String> listview; 
	private Button pngbtn;
	private Button removebtn;
	private VBox pngbuttons;
	private HBox pngc;
	
	public imagechooser() {}

	/**
	 * Method to create the image listview, label and its buttons and put it into an HBox with its own positions and styles.
	 *
	 * @return pngc HBox.
	 */
	public HBox createimagechooser() {
		pnglabel = new Label(LanguageSettings.get("mimport.pngs"));
		pnglabel.setPrefWidth(150);
		pnglabel.setPadding(new Insets(4, 0, 0, 0));
		pnglabel.getStyleClass().add("label");
		listview = new ListView<String>();
		listview.setPrefHeight(24*3);
		listview.setPrefWidth(350);
		listview.getStyleClass().add("fillin");
		pngbtn = new imagebutton().createimagebtn(listview);
		removebtn = new removebutton().createremovebtn(listview);
		pngbuttons = new VBox();
		pngbuttons.getChildren().add(pngbtn);
		pngbuttons.getChildren().add(removebtn);
		pngbuttons.setPadding(new Insets(5, 0, 0, 0));
		pngc = new HBox();
		pngc.getChildren().add(pnglabel);
		pngc.getChildren().add(listview);
		pngc.getChildren().add(pngbuttons);
		pngc.setPadding(new Insets(0, 0, 5, 0));

		/**
		 * Event Handler of the image listview to disable the removebtn if the image listview contains no elements.
		 */
		listview.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    new EventHandler<MouseEvent>() {
			       public void handle(MouseEvent e) {
			            if (listview.getItems().size() == 0 || listview.getSelectionModel().getSelectedIndex() == -1) {
			            	removebtn.setDisable(true);
			            }else {
			            	removebtn.setDisable(false);
			            }
			        }
			});
		
		return pngc;
	}

	/**
	 * Method to get imagelistview.
	 *
	 * @return listview with image files.
	 */
	public ListView<String> getimagelistview(){
		return listview;
	}
}
