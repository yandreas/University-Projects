package model.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Class to import a new model:
 * - creates directory
 * - creates sdf and config
 * - moves image and dae files into right directory
 */
public class createDirectory {

    private Stage stage;
    private Scene scene;

    public createDirectory() throws MalformedURLException {
        stage = new Stage();
        scene = initializeScene();

        //scene.getStylesheets().add(createDirectory.class.getResourceAsStream("/style/Style.css").toString());//Stylesheet Style.css included into scene
        stage.setTitle("Create a new model"); //Title of stage
        //stage.getIcons().add(new Image(createDirectory.class.getResourceAsStream("/images/import.png"))); //Image of application
        stage.setScene(scene); //Sets scene for stage
        stage.show(); //shows the stage: opens the application
    }

    private Scene initializeScene() throws MalformedURLException {
        //Labels
        Label daelabel = new Label("Please select dae file:");
        Label pnglabel = new Label("Please select png files:");
        Label pathlabel = new Label("Please select model path:");
        Label modellabel = new Label("New model name:");
        Label authorlabel = new Label("Author name:");
        Label descriptionlabel = new Label("Brief description of your model:");

        //Images for the buttons
        Image imageOpen = new Image(createDirectory.class.getResourceAsStream("/images/OpenFolder.png"));
        Background imageImport = new Background(
                new BackgroundImage(
                        new Image(createDirectory.class.getResourceAsStream("/images/import.png")),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));


        ImageView imageview = new ImageView(imageOpen);
        imageview.setFitWidth(20);
        imageview.setFitHeight(20);

        ImageView imageview2 = new ImageView(imageOpen);
        imageview2.setFitWidth(20);
        imageview2.setFitHeight(20);

        ImageView imageview3 = new ImageView(imageOpen);
        imageview3.setFitWidth(20);
        imageview3.setFitHeight(20);

        Button pathbtn = new Button(); //Buttons for filechooser, directorychooser, removing selected images and creating/importing a new model
        pathbtn.setGraphic(imageview); //Sets imageview as background for the button
        pathbtn.getStyleClass().add("button-chooser"); //Sets style from style class button-chooser from Style.css

        Button daebtn = new Button();
        daebtn.setGraphic(imageview2);
        daebtn.getStyleClass().add("button-chooser");

        Button pngbtn = new Button();
        pngbtn.setGraphic(imageview3);
        pngbtn.setGraphic(imageview3);
        pngbtn.getStyleClass().add("button-chooser");

        Button createbtn = new Button("Create model");
        createbtn.setBackground(imageImport);
        createbtn.setPrefSize(150, 40);
        //createbtn.getStyleClass().add("button-create"); //Sets style from style class button-create from Style.css

        Button removebtn = new Button("remove");
        removebtn.setDisable(true); //Disables the button

        TextField modeltextfield = new TextField(); //Textfields for modelname, authorname and brief description of the model
        modeltextfield.setMaxWidth(300);    //sets width of textfield to 300

        TextField authortextfield = new TextField();
        authortextfield.setMaxWidth(300);

        TextField descriptiontextfield = new TextField();
        descriptiontextfield.setMaxWidth(300);

        ListView<String> listview = new ListView<String>(); //Listviews for paths selected by filechooser and directorychooser
        listview.setPrefHeight(24); //Sets prefered height of listview

        ListView<String> listview2 = new ListView<String>();
        listview2.setPrefHeight(24 * 3);

        ListView<String> listview3 = new ListView<String>();
        listview3.setPrefHeight(24);

        //Alterts for errors and confirmation
        Alert alert1 = new Alert(AlertType.ERROR);
        alert1.setTitle(null);
        alert1.setHeaderText(null);
        alert1.setContentText("Please fill in the blanks!");

        Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle(null);
        alert2.setHeaderText(null);
        alert2.setContentText("Model already exists!");

        Alert alert3 = new Alert(AlertType.CONFIRMATION);
        alert3.setTitle("Select further action!");
        alert3.setHeaderText(null);
        alert3.setContentText("Successfully created directory and moved files!\n" + "Create another model?");
        //Changes text of OK and CANCEL button of CONFIRMATION dialog
        ((Button) alert3.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
        ((Button) alert3.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("NO");


        VBox layout = new VBox(10); //Vertical box layout with spacing 10
        layout.setPadding(new Insets(10)); //Sets padding to border to 10

        HBox eins = new HBox(5); //Horizontal box layouts with spacing 5
        eins.getChildren().addAll(pathbtn, pathlabel); //Adds elements into HBox
        eins.setAlignment(Pos.BASELINE_LEFT); //Alignment of the elements in HBox

        HBox zwei = new HBox(5);
        zwei.getChildren().addAll(daebtn, daelabel);
        zwei.setAlignment(Pos.BASELINE_LEFT);

        HBox drei = new HBox(5);
        drei.getChildren().addAll(pngbtn, pnglabel, removebtn);
        drei.setAlignment(Pos.BASELINE_LEFT);

        HBox vier = new HBox(5);
        vier.getChildren().addAll(createbtn);
        vier.setPadding(new Insets(10)); //Sets padding to border to 10
        vier.setAlignment(Pos.BASELINE_RIGHT);

        //Adds elements into VBox including the HBoxes
        layout.getChildren().addAll(
                modellabel, modeltextfield, authorlabel, authortextfield,
                descriptionlabel, descriptiontextfield, eins,
                listview3, zwei, listview, drei, listview2, vier);


        //Event handler for daebtn --> filtered filechooser for one dae file path
        daebtn.setOnAction(event -> handleSelectDAE(listview));

        //Event handler for daebtn --> filtered filechooser for multiple png file paths
        pngbtn.setOnAction(event -> handleSelectImages(listview2));

        //Event handler for pathbtn --> directorychooser with one directory path (modelpath)
        pathbtn.setOnAction(event -> handleSelectModelPath(listview3));

        createbtn.setOnAction(event -> handleCreate(modeltextfield, listview, listview2, listview3, authortextfield, descriptiontextfield, alert3, stage, alert2, alert1));

        //Removes selected Images from listview
        removebtn.setOnAction(event -> handleRemove(listview2, removebtn));

        //Disables removebtn if no item is selected or listview is empty
        listview2.setOnMouseClicked(event -> handleDisable(listview2, removebtn));

        //Dropshadow effect if mouse enters button area
        btnshadow(daebtn);
        btnshadow(pngbtn);
        btnshadow(pathbtn);
        btnshadow(createbtn);
        btnshadow(removebtn);

        return new Scene(layout, 400, 550); //Scene of the stage with layout from VBox at width of 400 and height of 530
    }


    private void handleDisable(ListView<String> listview2, Button removebtn) {
        if (listview2.getItems().size() == 0 || listview2.getSelectionModel().getSelectedIndex() == -1) {
            removebtn.setDisable(true);
        } else {
            removebtn.setDisable(false);
        }
    }

    private void handleRemove(ListView<String> listview2, Button removebtn) {
        //Wenn Item selected
        if (listview2.getSelectionModel().getSelectedIndex() != -1) {
            listview2.getItems().remove(listview2.getSelectionModel().getSelectedIndex());

            //Disables remove button if listview is empty
            if (listview2.getItems().size() == 0) {
                removebtn.setDisable(true);
            }
        }
    }

    private void handleCreate(TextField modeltextfield, ListView<String> listview, ListView<String> listview2, ListView<String> listview3, TextField authortextfield, TextField descriptiontextfield, Alert alert3, Stage window, Alert alert2, Alert alert1) {
        //If all blanks are filled in else alert: empty blanks
        if (!(modeltextfield.getText().isEmpty() || listview.getItems().isEmpty()
                || listview2.getItems().isEmpty() || listview3.getItems().isEmpty()
                || authortextfield.getText().isEmpty() || descriptiontextfield.getText().isEmpty())) {

            //Files for directories
            File files = new File(listview3.getItems().get(0) + "/" + modeltextfield.getText());
            File meshes = new File(files + "/meshes");
            File materials = new File(files + "/materials/textures");
            File config = new File(files + "/model.config");
            File sdf = new File(files + "/model.sdf");
            File daefile = new File(listview.getItems().get(0));

            ArrayList<String> pngfiles = new ArrayList<String>();
            for (int i = 0; i < listview2.getItems().size(); i++) {
                pngfiles.add(listview2.getItems().get(i));
            }

            //Not importing model if it already exists
            if (!files.exists()) {
                //Creates directories
                files.mkdirs();
                meshes.mkdirs();
                materials.mkdirs();

                //Moves images and dae files
                try {

                    File temp = new File(listview.getItems().get(0));
                    temp.renameTo(new File(meshes + "/" + temp.getName()));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {

                    for (int i = 0; i < pngfiles.size(); i++) {
                        File temp = new File(pngfiles.get(i));
                        temp.renameTo(new File(materials + "/" + temp.getName()));
                    }

                } catch (Exception e) {
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
                            "  <model name=\"" + modeltextfield.getText() + "\">\r\n" +
                            "    <static>true</static>\r\n" +
                            "    <link name='" + modeltextfield.getText() + "_link'>\r\n" +
                            "      <collision name='" + modeltextfield.getText() + "_collision'>\r\n" +
                            "        <max_contacts>10</max_contacts>\r\n" +
                            "        <geometry>\r\n" +
                            "          <mesh>\r\n" +
                            "            <uri>model://" + modeltextfield.getText() + "/meshes/" + daefile.getName() + "</uri>\r\n" +
                            "          </mesh>\r\n" +
                            "        </geometry>\r\n" +
                            "      </collision>\r\n" +
                            "      <visual name='" + modeltextfield.getText() + "_visual'>\r\n" +
                            "        <geometry>\r\n" +
                            "          <mesh>\r\n" +
                            "            <uri>model://" + modeltextfield.getText() + "/meshes/" + daefile.getName() + "</uri>\r\n" +
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
                            "  <name>" + modeltextfield.getText() + "</name>\r\n" +
                            "  <version>1.0</version>\r\n" +
                            "  <author>\r\n" +
                            "   <name>" + authortextfield.getText() + "</name>\r\n" +
                            "  </author>\r\n" +
                            "  <sdf>model.sdf</sdf>\r\n" +
                            "  <description>\r\n" +
                            "   " + descriptiontextfield.getText() + "\r\n" +
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
                if (result.get() == ButtonType.OK) {
                    // ... user chose YES
                    listview2.getItems().clear();
                    listview.getItems().clear();
                    modeltextfield.clear();
                    descriptiontextfield.clear();
                } else {
                    // ... user chose NO or closed the dialog
                    window.close();
                }
            } else {
                alert2.showAndWait();
            }
        } else {
            alert1.showAndWait();
        }
    }

    private void handleSelectModelPath(ListView<String> listview3) {
        DirectoryChooser directorychooser = new DirectoryChooser();
        directorychooser.setTitle("Select model path");
        File selectedFile = directorychooser.showDialog(null);


        if (selectedFile != null) {
            if (listview3.getItems().size() == 1) {
                listview3.getItems().clear();
            }
            //Inserts path of selected directory
            listview3.getItems().add(selectedFile.getAbsolutePath());
        }
    }

    private void handleSelectImages(ListView<String> listview2) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select images");
        filechooser.getExtensionFilters().addAll(new ExtensionFilter(".png Files", "*.png"));
        List<File> selectedFiles = filechooser.showOpenMultipleDialog(null);


        if (selectedFiles != null) {
            boolean neu;
            for (int i = 0; i < selectedFiles.size(); i++) {
                neu = true;
                for (int j = 0; j < listview2.getItems().size(); j++) {
                    //Inserts paths of selected files
                    if (listview2.getItems().get(j).contentEquals(selectedFiles.get(i).getAbsolutePath())) {
                        neu = false;
                        break;
                    }
                }
                if (neu == true) {
                    listview2.getItems().add(selectedFiles.get(i).getAbsolutePath());
                }
            }
        }
    }

    private void handleSelectDAE(ListView<String> listview) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select dae");
        filechooser.getExtensionFilters().addAll(new ExtensionFilter(".dae Files", "*.dae"));
        File selectedFile = filechooser.showOpenDialog(null);


        if (selectedFile != null) {
            //Only one element in listview
            if (listview.getItems().size() == 1) {
                listview.getItems().clear();
            }
            //Inserts path of selected file
            listview.getItems().add(selectedFile.getAbsolutePath());
        }
    }

    //Dropshadow effect for button
    private DropShadow shadow = new DropShadow();

    private void btnshadow(Button btn) {
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        btn.setEffect(shadow);
                    }
                });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        btn.setEffect(null);
                    }
                });
    }


}
