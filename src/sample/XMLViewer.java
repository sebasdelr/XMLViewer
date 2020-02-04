package sample;

import java.io.File;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.event.EventHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLViewer extends Application
{
    // Create the TreeView
    private final TreeView treeView = new TreeView();
    // Create the TextArea
    private final TextArea textArea = new TextArea();
    // Create the TextField
    private TextField textField = new TextField();


    private final Node rootIcon = new ImageView(
            new Image(getClass().getResourceAsStream("monitor.png"))
    );

    public static void main(String[] args)
    {
        Application.launch(args);
    }



    @Override
    public void start(Stage stage)
    {

        FileHelper fileHelper = new FileHelper();
        fileHelper.loadData();

        TreeItem<String> fileItem = fileHelper.getTreeRootItem();






        TreeItem<String> treeRootItem = new TreeItem<String>("Builder", rootIcon);
        // Add children to the root

//        for (int i = 1; i < 6; i++) {
//            TreeItem<String> item = new TreeItem<String> ("Message" + i);
//            rootItem.getChildren().add(item);
//        }
        
        treeView.setEditable(false);
        // Set a cell factory to use TextFieldTreeCell
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());
        // Select the root node
        treeView.getSelectionModel().selectFirst();
        // Create the root node and adds event handler to it
        TreeItem rootItem = new TreeItem("Builder");




        rootItem.getChildren().addAll(fileItem);
        // Set the Root Node
        treeView.setRoot(fileItem);




        rootItem.setExpanded(false);


        EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
            handleMouseClicked(event);
        };

        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);



        VBox rightPane = getRightPane();

        VBox root = new VBox();
        HBox content = new HBox();

        HBox menu = new HBox();
        menu.getChildren().addAll(new Label("File"));

        HBox footer = new HBox();
        footer.setSpacing(10);
        footer.getChildren().addAll(new Label("Number of Subdivisions: "));

        // Set the horizontal space between each child in the HBox
        content.setSpacing(20);
        // Add the TreeView to the HBox
        content.getChildren().addAll(treeView,rightPane);
        root.setSpacing(20);

        root.getChildren().addAll(menu, content, footer);

        // Create the Scene
        Scene scene = new Scene(root,540,400);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title for the Scene
        stage.setTitle("TreeView Example 4");
        // Display the stage
        stage.show();
    }

    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if ((node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null))) {
            TreeItem selectedItem = (TreeItem) treeView.getSelectionModel().getSelectedItem();
            String name = (String) selectedItem.getValue();

            ObservableList<TreeItem> tempItems = selectedItem.getChildren();


            int level = treeView.getTreeItemLevel(selectedItem);
            if(level == 3){
                String temp = "";
                for (int x = 0; x < tempItems.size(); x++ ) {
                    temp = temp.concat(tempItems.get(x).getValue() + "\n");


                }

                //address view vs plan spec view
                writeMessage(name);
                writeNodes(temp);
            }

        }
    }

    // This method creates a VBox and it´s components and returns it to the calling Method
    private VBox getRightPane()
    {

        // Set the preferred number of text rows
        textArea.setPrefRowCount(15);
        // Set the preferred number of text columns
        textArea.setPrefColumnCount(20);

        // Create the HBox
        HBox hbox = new HBox();
        // Add Children to the HBox
        hbox.getChildren().addAll(new Label("Item:"), textField);

        hbox.setSpacing(10);


        // Create the VBox
        VBox vbox = new VBox();
        // Add children to the VBox
        vbox.getChildren().addAll(new Label("Select an item to add to or remove."),hbox,
                new Label("Message Log:"), textArea);
        // Set the vertical space between each child in the VBox
        vbox.setSpacing(20);


        return vbox;
    }




    // Helper Methods for the Event Handlers


    // Method for Logging
    private void writeMessage(String msg)
    {
        this.textField.setText(msg);
    }

    private void writeNodes(String msg){
        this.textArea.setText(msg);
    }

    public static String elementText(org.w3c.dom.Node node, String string){
        return ((Element) node).getElementsByTagName(string)
                .item(0)
                .getTextContent();
    }

    public static org.w3c.dom.Node nodeGetter (NodeList nodeList, int index){

        org.w3c.dom.Node node = nodeList.item(index) ;
        return node;
    }
}

