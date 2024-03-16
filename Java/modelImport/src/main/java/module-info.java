module modelImport {
    //requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires javafx.graphics;
    requires javafx.controls;
    requires settings;
    requires java.prefs;

    opens main to javafx.graphics;
    exports main;
}
