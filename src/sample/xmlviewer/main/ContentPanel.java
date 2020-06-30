package sample.xmlviewer.main;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.xmlviewer.data.ViewerManager;

import java.io.File;

public class ContentPanel {

    private HBox content = new HBox();

    private TextField textField = new TextField();
    private TextArea textArea = new TextArea();

    private TreeItem rootItem = new TreeItem("Builder");
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

            EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
                handleMouseClicked(event);
            };

            treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
        }

        else{
            content.getChildren().addAll(emptyContentPanel.getContent());
        }


    }

    private void writeMessage(String msg)
    {
        this.textField.setText(msg);
    }
    private void writeNodes(String msg){
        this.textArea.setText(msg);
    }

//    private void setContext(String type){
//
//
//        switch (type){
//            case "office":
//                this.contextBox.getChildren().clear();
//                this.contextBox.setSpacing(10);
//                this.contextBox.getChildren().addAll(new Label("Address:"), textArea);
//                break;
//
//            default:
//                this.contextBox.getChildren().clear();
//                break;
//
//
//        }
//
//
//
//
//
//    }

    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if ((node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null))) {
            TreeItem selectedItem = (TreeItem) treeView.getSelectionModel().getSelectedItem();
            String name = (String) selectedItem.getValue();

            ObservableList<TreeItem> tempItems = selectedItem.getChildren();

            ViewerManager.isWorking();


            int level = treeView.getTreeItemLevel(selectedItem);
            if(level == 3){
                String temp = "";
                for (int x = 0; x < tempItems.size(); x++ ) {
                    temp = temp.concat(tempItems.get(x).getValue() + "\n");


                }

                //address view vs plan spec view
//                writeMessage(name);
//                writeNodes(temp);
//                setContext("office");
                System.out.println("test");
            }
            else {
                //setContext("other");
            }

        }
    }

    public HBox getContent() {
        return content;
    }
}
