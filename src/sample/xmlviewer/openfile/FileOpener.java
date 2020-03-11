package sample.xmlviewer.openfile;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.xmlviewer.data.ViewerManager;
import sample.xmlviewer.helpers.XMLTreeHelper;

import java.io.File;

public class FileOpener {

    private File file;
    private XMLTreeHelper xmlTreeHelper = new XMLTreeHelper();

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

        if(this.file != null){
            xmlTreeHelper.loadData(this.file);
            ViewerManager.setTreeItem(xmlTreeHelper.getTreeRootItem());

        }

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

}
