package sample.xmlviewer.openfile;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.xmlviewer.data.ViewerManager;
import sample.xmlviewer.helpers.XMLTreeHelper;

import java.io.File;

public class FileOpener {

    private File xmlFile;
    private File xsdFile;
    private XMLTreeHelper xmlTreeHelper = new XMLTreeHelper();
    private String xsdPath;
    private String xmlPath;

    public FileOpener(){

    }

    public void xmlOpener(Stage stage){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("XML", "*.xml")
        );
        this.xmlFile = fileChooser.showOpenDialog(stage);

        if(this.xmlFile != null){
            xmlTreeHelper.loadData(this.xmlFile);
            xmlPath = this.xmlFile.getPath();
            ViewerManager.setTreeItem(xmlTreeHelper.getTreeRootItem());

        }

    }

    public void xsdOpener(Stage stage){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("XSD", "*.xsd")
        );
        this.xsdFile = fileChooser.showOpenDialog(stage);

        if(this.xsdFile != null){
            this.xsdPath = xsdFile.getPath();
            //save to config
        }

    }

    public String getXsdPath() {
        return xsdPath;
    }

    public File getXsdFile() {
        return xsdFile;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public File getXmlFile() {
        return xmlFile;
    }
}
