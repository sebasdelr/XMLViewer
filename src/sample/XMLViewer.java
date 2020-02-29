package sample;

import java.awt.*;
import java.io.File;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.event.EventHandler;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javafx.geometry.Pos;

public class XMLViewer extends Application
{
    // Create the TreeView
    private final TreeView treeView = new TreeView();
    // Create the TextArea
    private final TextArea textArea = new TextArea();
    // Create the TextField
    private TextField textField = new TextField();

    private File file;

    //private Desktop desktop = Desktop.getDesktop();

    private VBox contextBox = new VBox();
    private VBox vbox = new VBox();

    private FileHelper fileHelper = new FileHelper();

    private TreeItem rootItem = new TreeItem("Builder");

    private int subdivNum;

    private XMLValidator xmlValidator = new XMLValidator();





    private final Node rootIcon = new ImageView(
            new Image(getClass().getResourceAsStream("monitor.png"))
    );




    @Override
    public void start(Stage stage)
    {






        treeView.setEditable(false);
        // Set a cell factory to use TextFieldTreeCell
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());
        // Select the root node
        treeView.getSelectionModel().selectFirst();

        textField.setPrefColumnCount(18);
        textField.setEditable(false);
        textArea.setEditable(false);

        rootItem.setExpanded(false);

        EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
            handleMouseClicked(event);
        };

        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);



        VBox rightPane = getRightPane();

        VBox root = new VBox();
        HBox content = new HBox();
        content.setPadding(new Insets(0,0,0,10));

        HBox menu = new HBox();
        menu.getChildren().addAll(new Label("File"));

        MenuBar mnuMain = new MenuBar();

        Menu mnuFile = new Menu("_File");

        mnuMain.getMenus().addAll(mnuFile);

        MenuItem mnuFileOpen = new MenuItem("_Open");

        MenuItem mnuFileChecker = new MenuItem("_Process");
        MenuItem mnuFileExit = new MenuItem("E_xit");
        mnuFile.getItems().addAll(mnuFileOpen,
                mnuFileChecker, mnuFileExit);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("XML", "*.xml")
        );





        //Exit quits app

        mnuFileExit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        //Open loads file chooser
        mnuFileOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //fileChooser.showOpenDialog(stage);
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    //open File method below
                    openFile(file);
                }

            }
        });

        mnuFileChecker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                if(fileHelper.isFileLoaded()){
                    Checker.display();
//                }
//                else{
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("File not loaded");
//                    //alert.setHeaderText("Look, a Warning Dialog");
//                    alert.setContentText("A file has not been loaded. Make sure you load a file first.");
//
//                    alert.showAndWait();
//
//                }



            }
        });





        HBox footer = new HBox();

        //footer.setSpacing(10);
        footer.getChildren().addAll(new Label("Number of Subdivisions: " + subdivNum));
        footer.setPadding(new Insets(0,0,10,10));

        // Set the horizontal space between each child in the HBox
        content.setSpacing(10);
        // Add the TreeView to the HBox
        content.getChildren().addAll(treeView,rightPane);
        root.setSpacing(10);

        root.getChildren().addAll(mnuMain, content, footer);

        // Create the Scene
        Scene scene = new Scene(root,530,400);
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
                setContext("office");
                System.out.println("test");
            }
            else {
                setContext("other");
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
        //VBox vbox = new VBox();
        // Add children to the VBox
        this.vbox.getChildren().addAll(hbox, contextBox);

        //vbox.getChildren().
        // Set the vertical space between each child in the VBox
        vbox.setSpacing(20);


        return vbox;
    }



    private void writeMessage(String msg)
    {
        this.textField.setText(msg);
    }
    private void writeNodes(String msg){
        this.textArea.setText(msg);
    }

    private void setContext(String type){


        switch (type){
            case "office":
                this.contextBox.getChildren().clear();
                this.contextBox.setSpacing(10);
                this.contextBox.getChildren().addAll(new Label("Address:"), textArea);
                break;

            default:
                this.contextBox.getChildren().clear();
                break;


        }





    }



    private void openFile(File file){
        //desktop.open(file);
        this.file = file;
        fileHelper.loadData(this.file);

        rootItem.getChildren().addAll(fileHelper.getTreeRootItem());
        // Set the Root Node
        treeView.setRoot(fileHelper.getTreeRootItem());
        subdivNum = fileHelper.getSubdivNum();

    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }

}

