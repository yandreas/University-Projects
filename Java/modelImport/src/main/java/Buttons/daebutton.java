package Buttons;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javafx.stage.Stage;
import language.LanguageSettings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Class for creating the dae button, which opens the filechooser to select the needed dae file.
 *
 * @author Yuye Andreas Meier
 */
public class daebutton {

	private Button daebtn;
	private FileChooser filechooser;
	private File selectedFile;
	private Alert alert;
	private Alert alert2;
	private DocumentBuilder builder;
    private Document doc;
	
	public daebutton() {}

    /**
     * Method to create the dae button, which opens a filechooser to select the needed dae file.
     * @param listview dae listview needed for the dae file.
     * @param listview2 image listview needed for parsing the images from dae file.
     * @param window stage needed for setting the logo images in alerts.
     * @return returns the dae button.
     */
	public Button createdaebtn(ListView<String> listview, ListView<String> listview2, Stage window) {
		alert = new alerts().parsealert();
		alert2 = new alerts().validealert();
		alert.initOwner(window);
		alert2.initOwner(window);

		daebtn = new Button();
		daebtn.setGraphic(new Images().createopenimage());
		daebtn.getStyleClass().add("button-chooser");

		/**
         * Event handler for daebtn.
         * Filtered filechooser for one dae file path.
         */
		daebtn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
			filechooser = new FileChooser();
			filechooser.setTitle(LanguageSettings.get("mimport.dpath"));
			filechooser.getExtensionFilters().addAll(new ExtensionFilter(".dae Files", "*.dae"));
			selectedFile = filechooser.showOpenDialog(null);
			
			
			if (selectedFile != null){
				//Only one element in listview
				if(listview.getItems().size() == 1) {
					listview.getItems().clear();
				}
                //checks if file is real xml file
                if(valide(selectedFile)) {
                    //Inserts path of selected file
                    listview.getItems().add(selectedFile.getAbsolutePath());
                    //parses textures
                    selectImages(listview2, alert, selectedFile);
                }
                else{
                    alert2.showAndWait();
                }
			}
		}
		});
		
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(daebtn);
		return daebtn;
	}

    /**
     * Method to parse and automatically move the images connected to the dae file into the listview of images.
     * @param selectedFile dae file which is parsed.
     * @param listview image listview to add the images to.
     * @param alert Alert which shows which images are not at the same directory as the dae file.
     */
	public void selectImages(ListView<String> listview, Alert alert, File selectedFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(selectedFile);
            NodeList imagenodelist = doc.getElementsByTagName("image");
            for (int i = 0; i < imagenodelist.getLength(); i++) {
                Node imagenode = imagenodelist.item(i);
                if(imagenode.getNodeType() == Node.ELEMENT_NODE) {
                    Element imel = (Element) imagenode;
                    NodeList imageslist = imel.getChildNodes();
                    for (int j = 0; j<imageslist.getLength(); j++) {
                        Node n = imageslist.item(j);
                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element image = (Element) n;
                            String str = image.getTextContent();
                            String newstr = str.replaceAll("%20", " ");
                            String imagestring = selectedFile.getParent() + "/" + newstr;
                            File imagefile = new File(imagestring);

                            if(imagefile.exists()) {
                                listview.getItems().add(imagestring);
                            }
                            else {
                                alert.setContentText(newstr + " " + LanguageSettings.get("mimport.nexist"));
                                alert.showAndWait();
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which checks if the selected dae file is a proper xml file.
     * @param selectedFile File that is checked.
     * @return false if not a proper xml file --> alert.
     * @return true if proper xml file.
     */
    public boolean valide(File selectedFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(selectedFile);
        } catch (ParserConfigurationException e) {
            return false;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
