package sample.xmlviewer.openfile;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileOpener {

    private File file;

    public FileOpener(){

    }

    public void xmlOpener(Stage stage){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("XML", "*.xml")
        );
        this.file = fileChooser.showOpenDialog(stage);

    }

    public void xdsOpener(Stage stage){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("XDS", "*.xds")
        );
        fileChooser.showOpenDialog(stage);

    }

    public File getFile() {
        return file;
    }
}
