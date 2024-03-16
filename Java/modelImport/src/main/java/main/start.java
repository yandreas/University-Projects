package main;

import Buttons.Images;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import language.LanguageSettings;

/**
 * Class for the welcome text put into and HBox
 *
 * @author Yuye Andreas Meier
 */
public class start {

	private TextArea text;
	private Button model;
	private HBox welcome;
	
	public start() { }

	/**
	 * Method to create welcome text and put it into an HBox with its own positions and styles.
	 *
	 * @return welcome HBox.
	 */
	public HBox createstart() {
		text = new TextArea(LanguageSettings.get("mimport.welcomeText"));
		text.setPrefSize(400, 50);
		text.setMaxSize(400, 50);
		text.setPadding(new Insets(20,0,0,0));
		text.setDisable(true);
		text.getStyleClass().add("welcomep");
		text.setOpacity(1);

		model = new Button();
		model.getStyleClass().add("icon");
		model.setGraphic(new Images().createmodelimage());

		// merge in an Hbox
		welcome = new HBox();
		welcome.getChildren().add(model);
		welcome.getChildren().add(text);
		welcome.setAlignment(Pos.CENTER);
		
		return welcome;
	}
}
