package sample.xmlviewer.openfile;

import javafx.scene.control.TreeItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.xmlviewer.data.ViewerManager;
import sample.xmlviewer.helpers.XMLFileHelper;

import java.io.File;

public class FileOpener {

    private File file;
    private XMLFileHelper xmlFileHelper = new XMLFileHelper();

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
            xmlFileHelper.loadData(this.file);
            ViewerManager.setTreeItem(xmlFileHelper.getTreeRootItem());



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

    public File getFile() {
        return this.file;
    }

    public TreeItem<String> getTreeItem() {
        TreeItem<String> treeItems = null;


        if(this.file != null){
            xmlFileHelper.loadData(this.file);
            treeItems = xmlFileHelper.getTreeRootItem();


        }

        return treeItems;

    }





}
