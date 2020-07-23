package sample.xmlviewer.openfile;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileOpener {

    private File xmlFile;
    private File xsdFile;

    public FileOpener(){

    }

    public void xmlOpener(Stage stage){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(

                new FileChooser.ExtensionFilter("XML", "*.xml")
        );
        this.xmlFile = fileChooser.showOpenDialog(stage);

    }

    public void xsdOpener(Stage stage){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XSD", "*.xsd")
        );
        this.xsdFile = fileChooser.showOpenDialog(stage);

    }

    public File getXsdFile() {
        return xsdFile;
    }

    public File getXmlFile() {
        return xmlFile;
    }
}
