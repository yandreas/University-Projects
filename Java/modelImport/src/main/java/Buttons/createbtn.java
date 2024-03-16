package Buttons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import language.LanguageSettings;
import main.*;

/**
 * Class for creating the create button which is used for creating a model after collecting all needed information.
 *
 * @author Yuye Andreas Meier
 */
public class createbtn {

	private Button createbtn;
	private Alert alert1;
	private Alert alert2;
	private Alert alert3;

	public createbtn() {}

	/**
	 * Method collects everything necessary for the create button and returns it.
	 * @param modelname modelname needed for giving the right name to the crated files.
	 * @param daechooser filechooser to provide the needed listview for the dae file.
	 * @param author author details needed for creating the right files.
	 * @param description description of the model needed for creating the files.
	 * @param imagechooser filechooser to provide the needed listview of images.
	 * @param modelpath path of where the model should be saved at.
	 * @param window stage needed for closing the window and setting the logo image.
	 * @return returns the create button.
	 */
	public Button createcreatebtn(modelname modelname, daechooser daechooser, imagechooser imagechooser, modelpath modelpath, author author, description description, Stage window) {
		alert1 = new alerts().blanksalert();
		alert2 = new alerts().existalert();
		alert3 = new alerts().furtheractionalert();
		alert1.initOwner(window);
		alert2.initOwner(window);
		alert3.initOwner(window);

			//import/create model button
			createbtn = new Button(LanguageSettings.get("mimport.create"));
			createbtn.setPrefSize(100, 40);
			createbtn.getStyleClass().add("button-create");

		/**
		 * EventHandler for createbtn.
		 * Creates right file paths and files with the needed model sdf and config sdf and moves the dae and image files.
		 */
		createbtn.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event) {
			//If all blanks are filled in else alert: empty blanks
			if(!(modelname.getmodeltextfield().getText().isEmpty() || daechooser.getdaelistview().getItems().isEmpty()
				|| modelpath.getpathlistview().getItems().isEmpty() || author.getauthortextfield().getText().isEmpty()
					|| description.getdescriptiontextfield().getText().isEmpty())) {
				
				//Files for directories
				File files = new File(modelpath.getpathlistview().getItems().get(0) + "/" + modelname.getmodeltextfield().getText());
				File meshes = new File(files + "/meshes");
				File materials = new File(files + "/materials/textures");
				File config = new File(files + "/model.config");
	            File sdf = new File(files + "/model.sdf");
	            File daefile = new File(daechooser.getdaelistview().getItems().get(0));
	            
	            ArrayList<String> pngfiles = new ArrayList<String>();
	            for (int i = 0; i < imagechooser.getimagelistview().getItems().size(); i++) {
	            	pngfiles.add(imagechooser.getimagelistview().getItems().get(i));
	            }
	            
				//Not importing model if it already exists
				if (!files.exists()) {
					//Creates directories
	                files.mkdirs();
	                meshes.mkdirs();
	                materials.mkdirs();
	                
	                //Moves images and dae files
	                try{
	            		
	               	   File temp = new File(daechooser.getdaelistview().getItems().get(0));	
	               	   temp.renameTo(new File(meshes + "/" + temp.getName()));
	               	   
	               	}catch(Exception e){
	               		e.printStackTrace();
	               	}
	                 
	                try{
	            	
	                	for(int i = 0; i < pngfiles.size(); i++) {
		              	   File temp = new File(pngfiles.get(i));	
		              	   temp.renameTo(new File(materials + "/" + temp.getName()));
	                	}
	              	    
	              	}catch(Exception e){
	              		e.printStackTrace();
	              	}
	                
	                //Creates new sdf and config file in model directory
	                try {
						sdf.createNewFile();
						config.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
	                
	                //Writes into sdf and config file
	                try {
	                	String intosdf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
	                			"<sdf version='1.6'>\r\n" + 
	                			"  <model name=\"" + modelname.getmodeltextfield().getText() + "\">\r\n" + 
	                			"    <static>true</static>\r\n" + 
	                			"    <link name='" + modelname.getmodeltextfield().getText() + "_link'>\r\n" + 
	                			"      <collision name='" + modelname.getmodeltextfield().getText() + "_collision'>\r\n" + 
	                			"        <max_contacts>10</max_contacts>\r\n" + 
	                			"        <geometry>\r\n" + 
	                			"          <mesh>\r\n" + 
	                			"            <uri>model://" + modelname.getmodeltextfield().getText() + "/meshes/" + daefile.getName() + "</uri>\r\n" + 
	                			"          </mesh>\r\n" + 
	                			"        </geometry>\r\n" + 
	                			"      </collision>\r\n" + 
	                			"      <visual name='" + modelname.getmodeltextfield().getText() + "_visual'>\r\n" + 
	                			"        <geometry>\r\n" + 
	                			"          <mesh>\r\n" + 
	                			"            <uri>model://" + modelname.getmodeltextfield().getText()+ "/meshes/" + daefile.getName() + "</uri>\r\n" + 
	                			"          </mesh>\r\n" + 
	                			"        </geometry>\r\n" + 
	                			"      </visual>\r\n" + 
	                			"    </link>\r\n" + 
	                			"  </model>\r\n" + 
	                			"</sdf>";
	                    BufferedWriter writer = new BufferedWriter(new FileWriter(sdf));
						writer.write(intosdf);
						writer.close();
						
						String intoconfig = "<?xml version=\"1.0\"?>\r\n" + 
								"<model>\r\n" + 
								"  <name>" + modelname.getmodeltextfield().getText() + "</name>\r\n" + 
								"  <version>1.0</version>\r\n" + 
								"  <author>\r\n" + 
								"   <name>" + author.getauthortextfield().getText() + "</name>\r\n" +
								"	<email>" + author.getauthoremail().getText() + "</email>\r\n" +
								"  </author>\r\n" + 
								"  <sdf>model.sdf</sdf>\r\n" + 
								"  <description>\r\n" + 
								"   " + description.getdescriptiontextfield().getText() + "\r\n" + 
								"  </description>\r\n" + 
								"</model>\r\n";
	                    BufferedWriter writer2 = new BufferedWriter(new FileWriter(config));
						writer2.write(intoconfig);
						writer2.close();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	               
	                //User asked if closing appplication or importing another model
	               Optional<ButtonType> result = alert3.showAndWait();
	               if (result.get() == ButtonType.OK){
	           	    // ... user chose YES
	           		imagechooser.getimagelistview().getItems().clear();
	           		daechooser.getdaelistview().getItems().clear();
	           		modelname.getmodeltextfield().clear();
	           		description.getdescriptiontextfield().clear();
	           	} else {
	           	    // ... user chose NO or closed the dialog
	           		window.close();
	           	}
				}else {
					//Model already exists.
					alert2.showAndWait();
				}
			}else {
				//Not all needed information is filled out.
				alert1.showAndWait();
			}
		}
	});
		Buttonshadow shadow = new Buttonshadow();
		shadow.setshadow(createbtn);
		return createbtn;
	}
}
