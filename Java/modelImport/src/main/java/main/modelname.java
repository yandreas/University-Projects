package main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import language.LanguageSettings;

/**
 * Class for the modelname HBox containing the model name textfield and label.
 *
 * @author Yuye Andreas Meier
 */
public class modelname {

	private Label modellabel;
	private TextField modeltextfield;
	private HBox modelName;
	
	public modelname() {}

	/**
	 * Method to create the model name textfield and label and put it into an HBox with its own positions and styles.
	 *
	 * @return modelName HBox.
	 */
	public HBox createmodelname() {
		modellabel = new Label(LanguageSettings.get("mimport.mname")); //Name of the textfield
		modellabel.setPrefWidth(150);
		modellabel.setPadding(new Insets(3, 0, 0, 0));
		modellabel.getStyleClass().add("label");
		modeltextfield = new TextField(); //Textfields for modelname
		modeltextfield.setPrefWidth(350); //sets width of textfield to 350
		modeltextfield.getStyleClass().add("fillin");
		modelName = new HBox();
		modelName.getChildren().add(modellabel);
		modelName.getChildren().add(modeltextfield);
		modelName.setPadding(new Insets(15, 0, 5, 0));
		return modelName;
	}

	/**
	 * Method to get model name textfield.
	 *
	 * @return textfield input of the model name.
	 */
	public TextField getmodeltextfield() {
		return modeltextfield;
	}
}
