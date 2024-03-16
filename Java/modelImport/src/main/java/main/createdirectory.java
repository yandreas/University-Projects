package main;



import Buttons.createbtn;
import color.ColorSettings;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import language.LanguageSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

/**
 * Class to import a new model:
 * - creates directory
 * - creates sdf and config
 * - moves image and dae files into right directory
 *
 * @author Yuye Andreas Meier
 */
public class createdirectory extends Application {
	private BorderPane root = new BorderPane();
	private HBox createbox;
	private VBox mergeall;
	private Scene scene;
	private static boolean reseti = false;

	/**
	 * Stage window in javafx
	 */
	public void start(Stage window) throws Exception {
		// THE WINDOW ITSELF
		scene = new Scene(root, 600, 425); //Scene of the stage with layout from VBox at width of 400 and height of 530
		if (ColorSettings.getColorTheme().equals("standard"))
			scene.getStylesheets().add((getClass().getResource("/style/standard/Style.css")).toExternalForm());
		else if (ColorSettings.getColorTheme().equals("dark"))
			scene.getStylesheets().add((getClass().getResource("/style/dark/Style.css")).toExternalForm());
		else if (ColorSettings.getColorTheme().equals(""))
			scene.getStylesheets().add((getClass().getResource("/style/standard/Style.css")).toExternalForm());
		window.setResizable(false); // not resizable
		window.setTitle(LanguageSettings.get("mimport.window.title")); //Title of stage
		window.getIcons().add(new Image(getClass().getResourceAsStream("/images/ffglogo.png"))); //Image of application
		window.setScene(scene); //Sets scene for stage

		setReset(true);
		// adds overall a distance to windowframe
		// TOP-PART OF THE WINDOW
		root.setPadding(new Insets(5, 15, 5, 15));
		// creates text to guide the user
		root.setTop(new start().createstart());
		
		// CENTER-PART OF THE WINDOW
					// information about the model-name
					//modelname
					modelname modelname = new modelname();
					
					// information about the author
					author author = new author();
					
					// description of the model
					description description = new description();
					
					// directorychoser
					modelpath modelpath = new modelpath();
					
					//imagechoser
					imagechooser imagechooser = new imagechooser();
					
					// daechoser
					daechooser daechooser = new daechooser();
					
					createbox = new HBox();
					createbox.getChildren().add(new createbtn().createcreatebtn(modelname,
							daechooser, imagechooser, modelpath, author, description, window));
					createbox.setPadding(new Insets(15, 0, 0, 0));
					createbox.setAlignment(Pos.CENTER);

		// merge in a VBox
		mergeall = new VBox();
		mergeall.getChildren().add(modelname.createmodelname());
		mergeall.getChildren().add(author.createauthorname());
		mergeall.getChildren().add(author.createauthoremail());
		mergeall.getChildren().add(description.createdescription());
		mergeall.getChildren().add(modelpath.createmodelpath());
		mergeall.getChildren().add(daechooser.createdaechooser(imagechooser.getimagelistview(), window));
		mergeall.getChildren().add(imagechooser.createimagechooser());
		mergeall.getChildren().add(createbox);
		mergeall.setAlignment(Pos.CENTER);

		root.setCenter(mergeall);


		window.show(); //shows the stage: opens the application

		window.setOnCloseRequest(event -> {
			setReset(false);
			window.close();
		});
	}

	public static void setReset(boolean info) {
		reseti = info;
	}

	public static boolean getReset() {
		return reseti;
	}
}
		
