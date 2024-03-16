package Buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Class for creating a dropshadow effect for buttons.
 *
 * Instead of repeating the method in every button class its accessible as its own class.
 * @author Yuye Andreas Meier
 */
public class Buttonshadow {

	public Buttonshadow() {}

	/**
	 * Sets dropshadow if mouse hovers over the button.
	 * Releases dropshadow when mouse leaves the button.
	 * @param btn Button to give the dropshadow effect to.
	 */
	public void setshadow(Button btn) {
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			       public void handle(MouseEvent e) {
					   btn.getStyleClass().add("mainbuttonhover");
			        }
			});
			
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        public void handle(MouseEvent e) {
						btn.getStyleClass().remove("mainbuttonhover");
			        }
			});
	}
}
