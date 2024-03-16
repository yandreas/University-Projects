package main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import language.LanguageSettings;

/**
 * Class for the description HBox containing the descriptiontextfield and label.
 *
 * @author Yuye Andreas Meier
 */
public class description {
	
	private Label descriptionlabel;
	private TextField descriptiontextfield;
	private HBox desc;
	public description() {}

	/**
	 * Method to create the description textfield and label and put it into an HBox with its own positions and styles.
	 *
	 * @return desc HBox.
	 */
	public HBox createdescription() {
		descriptionlabel = new Label(LanguageSettings.get("mimport.desc"));
		descriptionlabel.setPrefWidth(150);
		descriptionlabel.setPadding(new Insets(3, 0, 0, 0));
		descriptionlabel.getStyleClass().add("label");
		descriptiontextfield = new TextField();
		descriptiontextfield.setPrefWidth(350); //sets width of textfield to 350
		descriptiontextfield.getStyleClass().add("fillin");
		desc = new HBox();
		desc.getChildren().add(descriptionlabel);
		desc.getChildren().add(descriptiontextfield);
		desc.setPadding(new Insets(0, 0, 5, 0));
		return desc;
	}

	/**
	 * Method to get description textfield.
	 *
	 * @return textfield input of the description.
	 */
	public TextField getdescriptiontextfield() {
		return descriptiontextfield;
	}
}
