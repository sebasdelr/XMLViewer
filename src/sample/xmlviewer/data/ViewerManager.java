package sample.xmlviewer.data;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import sample.FileHelper;
import sample.XMLValidator;
import sample.xmlviewer.main.ViewerWindowMain;

import java.io.File;

public class ViewerManager {
    private final TreeView treeView = new TreeView();
    private final TextArea textArea = new TextArea();
    private TextField textField = new TextField();

    private VBox contextBox = new VBox();
    private VBox vbox = new VBox();

    private FileHelper fileHelper = new FileHelper();

    private TreeItem rootItem = new TreeItem("Builder");

    private File xmlFile;
    private File xdsFile;
    final private String CONFIGINI = "config.ini";

    private int subdivNum;

    private XMLValidator xmlValidator = new XMLValidator();

    public static void main(String[] args){
        new ViewerWindowMain();
    }
}
