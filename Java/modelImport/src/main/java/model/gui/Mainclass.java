package model.gui;

import javafx.application.Application;
import javafx.stage.Stage;


public class Mainclass extends Application {

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		new createDirectory();
	}
}
