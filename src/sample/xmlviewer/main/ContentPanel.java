package sample.xmlviewer.main;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import sample.xmlviewer.data.ViewerManager;

public class ContentPanel {

    private HBox content = new HBox();

    private TreeItem rootItem = new TreeItem("File");
    TreeView treeView = new TreeView();

    private EmptyContentPanel emptyContentPanel = new EmptyContentPanel();

    public ContentPanel(){
        initPanel();

    }

    private void initPanel(){

        content.getChildren().addAll(emptyContentPanel.getContent());
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);

    }

    private TreeView getTreeView(){

        rootItem.setExpanded(false);
        treeView.setRoot(rootItem);

        treeView.setPrefWidth(530);
        treeView.setShowRoot(false);


        return treeView;

    }



    public void setRootItem() {
        if(ViewerManager.getTreeItem() != null){
            rootItem.getChildren().addAll(ViewerManager.getTreeItem());
            content.getChildren().clear();

            content.getChildren().addAll(getTreeView());

        }

        else{
            content.getChildren().addAll(emptyContentPanel.getContent());
        }


    }



    public HBox getContent() {
        return content;
    }
}
