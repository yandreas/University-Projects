package Buttons;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import language.LanguageSettings;

/**
 * Class for creating alerts used for buttons.
 *
 * Each method returns the requested alert.
 * @author Yuye Andreas Meier
 */
public class alerts {
	
	private Alert alert;
	
	public alerts() {}

	/**
	 * Alert if not all information is filled out.
	 */
	public Alert blanksalert() {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setGraphic(null);
		alert.setContentText(LanguageSettings.get("mimport.alert1"));

		Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(ok);
		ok.getStyleClass().add("button-create");

		return alert;
	}

	/**
	 * Alert if model already exists.
	 */
	public Alert existalert() {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setGraphic(null);
		alert.setContentText(LanguageSettings.get("mimport.alert2"));

		Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(ok);
		ok.getStyleClass().add("button-create");

		return alert;
	}

	/**
	 * Alert for further action after importing a model.
	 * Create a new model or close the window.
	 */
	public Alert furtheractionalert() {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(LanguageSettings.get("mimport.alert3.t"));
		alert.setHeaderText(null);
		alert.setGraphic(null);
		alert.setContentText(LanguageSettings.get("mimport.alert3.t1") +"\n" + LanguageSettings.get("mimport.alert3.t2"));

		//Changes text of OK and CANCEL button of CONFIRMATION dialog
		Button yes = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		yes.setText(LanguageSettings.get("mimport.yes"));
		Button no = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
		no.setText(LanguageSettings.get("mimport.no") );
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(yes);
		shadow.setshadow(no);
		yes.getStyleClass().add("button-create");
		no.getStyleClass().add("button-create");

		return alert;
	}

	/**
	 * Alert telling the user which images are currently missing and not saved at the same path as their dae file.
	 */
	public Alert parsealert() {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setGraphic(null);

		Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(ok);
		ok.getStyleClass().add("button-create");

		return alert;
	}

	/**
	 * Alert if given dae file is not a proper xml file.
	 */
	public Alert validealert() {
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(null);
		alert.setHeaderText(null);
		alert.setGraphic(null);
		alert.setContentText(LanguageSettings.get("mimport.valide"));

		Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(ok);
		ok.getStyleClass().add("button-create");

		return alert;
	}
}
