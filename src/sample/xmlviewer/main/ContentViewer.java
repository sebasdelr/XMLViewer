package sample.xmlviewer.main;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.FileHelper;

import java.io.File;
import java.util.ArrayList;

public class ContentViewer {

    private HBox content = new HBox();
    //TreeViewHelper helper = new TreeViewHelper();
    private VBox root = new VBox();

    private TextField textField = new TextField();
    private TextArea textArea = new TextArea();

    private FileHelper fileHelper = new FileHelper();

    private TreeItem rootItem = new TreeItem("Builder");

    //ArrayList<TreeItem> products = helper.getProducts();

    // Create the TreeView
    TreeView treeView = new TreeView();
    // Create the Root TreeItem


    private File file;



    public ContentViewer(){
        initViewer();

    }

    private void initViewer(){

           // Set a cell factory to use TextFieldTreeCell
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());
        // Select the root node
        treeView.getSelectionModel().selectFirst();

        textField.setPrefColumnCount(18);
        textField.setEditable(false);
        textArea.setEditable(false);

        rootItem.setExpanded(false);
        // Add children to the root
        //rootItem.getChildren().addAll(products);
        // Set the Root Node
        treeView.setRoot(rootItem);

        content.getChildren().addAll(treeView,new Label("Message Log:"), textArea);

        //content.getChildren().addAll(rootItem, this.textField);


    }

    public HBox getContent() {
        return content;
    }
}
