package main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import language.LanguageSettings;

/**
 * Class for author email and name input, used for the config file.
 *
 * @author Yuye Andreas Meier
 */
public class author {

	private Label authorlabel;
	private TextField authortextfield;
	private Label authoremaillabel;
	private TextField authoremail;
	private HBox authorName;
	private HBox authoremailbox;
	
	public author() {}

	/**
	 * Method to create the author name textfield input.
	 *
	 * @return authorName HBox with its changed style and positions containing the author name textfield.
	 */
	public HBox createauthorname(){
		authorlabel = new Label(LanguageSettings.get("mimport.aname"));
		authorlabel.setPrefWidth(150);
		authorlabel.setPadding(new Insets(3, 0, 0, 0));
		authorlabel.getStyleClass().add("label");
		authortextfield = new TextField();
		authortextfield.setPrefWidth(350); //sets width of textfield to 350
		authortextfield.getStyleClass().add("fillin");
		authorName = new HBox();
		authorName.getChildren().add(authorlabel);
		authorName.getChildren().add(authortextfield);
		authorName.setPadding(new Insets(0, 0, 5, 0));
		return authorName;
	}

	/**
	 * Method to create the author name textfield input.
	 *
	 * @return authormailbox HBox with its changed style and positions containing the author email textfield.
	 */
	public HBox createauthoremail() {
		authoremaillabel = new Label(LanguageSettings.get("mimport.email"));
		authoremaillabel.setPrefWidth(150);
		authoremaillabel.setPadding(new Insets(3, 0, 0, 0));
		authoremaillabel.getStyleClass().add("label");
		authoremail = new TextField();
		authoremail.setPrefWidth(350); //sets width of textfield to 350
		authoremail.getStyleClass().add("fillin");
		authoremailbox = new HBox();
		authoremailbox.getChildren().add(authoremaillabel);
		authoremailbox.getChildren().add(authoremail);
		authoremailbox.setPadding(new Insets(0, 0, 5, 0));
		return authoremailbox;
	}

	/**
	 * Method to get author name textfield.
	 *
	 * @return author name textfield.
	 */
	public TextField getauthortextfield() {
		return authortextfield;
	}

	/**
	 * Method to get author email textfield.
	 *
	 * @return author email textfield.
	 */
	public TextField getauthoremail() {
		return authoremail;
	}
}
