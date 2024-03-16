package Buttons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class for creating the imageviews that are used.
 *
 * @author Yuye Andreas Meier
 */
public class Images {
	private ImageView imageview;
	private Image imageOpen;
	private Image imageDel;
	private Image modelimg;

	/**
	 * Constructor to assign the images.
	 */
	public Images() {
		imageOpen = new Image(getClass().getResourceAsStream("/images/open.png"));	
		imageDel = new Image(getClass().getResourceAsStream("/images/delete.png"));
		modelimg = new Image(getClass().getResourceAsStream("/images/modelimport.png"));
	}

	/**
	 * Method creates imageview for the open image for the filechooser buttons.
	 * @return imageview.
	 */
	public ImageView createopenimage() {
		imageview = new ImageView(imageOpen);
		imageview.setFitWidth(20);
		imageview.setFitHeight(20);
		return imageview;
	}

	/**
	 * Method creates imageview for the delete image button.
	 * @return imageview.
	 */
	public ImageView createdelimage() {
		imageview = new ImageView(imageDel);
		imageview.setFitWidth(20);
		imageview.setFitHeight(20);
		return imageview;
	}

	/**
	 * Method creates imageview for the image of the softwarepart.
	 * @return imageview.
	 */
	public ImageView createmodelimage() {
		imageview = new ImageView(modelimg);
		imageview.setFitWidth(50);
		imageview.setFitHeight(50);
		return imageview;
	}
}
