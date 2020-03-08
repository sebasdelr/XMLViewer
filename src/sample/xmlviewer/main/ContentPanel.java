package sample.xmlviewer.main;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.FileHelper;
import sample.xmlviewer.data.ViewerManager;

import java.io.File;
import java.util.ArrayList;

public class ContentPanel {

    private HBox content = new HBox();
    private EmptyContentPanel emptyContentPanel = new EmptyContentPanel();

    private TextField textField = new TextField();
    private TextArea textArea = new TextArea();

    private TreeItem rootItem = new TreeItem("Builder");
    TreeView treeView = new TreeView();
    // Create the Root TreeItem
    private File file;
    private FileHelper fileHelper = new FileHelper();



    public ContentPanel(){
        initPanel();

    }

    private void initPanel(){

        content.getChildren().addAll(emptyContentPanel.getContent());




        content.setSpacing(10);

    }

    private TreeView getTreeView(){


        rootItem.setExpanded(false);
        treeView.setRoot(rootItem);

        return treeView;

    }

    private VBox getRightPane()
    {
        HBox hbox = new HBox();
        VBox vBox = new VBox();

        textArea.setPrefRowCount(15);
        textArea.setPrefColumnCount(20);

        hbox.getChildren().addAll(new Label("Item:"), textField);
        hbox.setSpacing(10);

        vBox.getChildren().addAll(hbox,new Label("Message Log:"), textArea);
        vBox.setSpacing(20);


        return vBox;
    }

    public void setRootItem() {
        if(ViewerManager.getTreeItem() != null){
            rootItem.getChildren().addAll(ViewerManager.getTreeItem());
            content.getChildren().clear();
            content.getChildren().addAll(getTreeView(), getRightPane());


        }

        else{
            content.getChildren().addAll(emptyContentPanel.getContent());
        }


    }

    public HBox getContent() {
        return content;
    }
}
