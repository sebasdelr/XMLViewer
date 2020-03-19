package sample.xmlviewer.settings;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.xmlviewer.data.ViewerManager;
import sample.xmlviewer.helpers.CheckHelper;
import sample.xmlviewer.helpers.XMLValidator;
import sample.xmlviewer.openfile.FileOpener;


public class Checker {

    TextArea textArea = new TextArea();
    private static Stage stage;

    public Checker(Stage stage) {
        this.stage = stage;
    }


    public static void display()
    {

        Stage popupwindow=new Stage();
        FileOpener fileOpener = new FileOpener();
        XMLValidator xmlValidator = new XMLValidator();

        //Boxes
        HBox row1 = new HBox();
        HBox row2 = new HBox();
        HBox xsd = new HBox();
        HBox xml = new HBox();
        VBox layout= new VBox();

        Scene scene1= new Scene(layout, 500, 400);

        //Buttons
        Button loadXML = new Button("Load XML");
        Button changeXSD = new Button("Change XSD");
        Button runChecker = new Button("Run Checker");
        Button openViewer = new Button("Open Viewer");
        runChecker.setDisable(true);
        openViewer.setDisable(true);

        CheckHelper checkHelper = new CheckHelper();

        //Checkboxes
        CheckBox officeCoord = new CheckBox();
        CheckBox subCoord = new CheckBox();

        //Labels
        Label instruction = new Label("Settings:");
        Label label1 = new Label("Office coordinates");
        Label label2 = new Label("Subdivision coordinates");
        Label currentXML = new Label("Current XML File: ");
        Label currentXSD = new Label("Current XSD File: ");
        Label xsdFileName = new Label(checkHelper.getXsdFileName());
        Label xmlFileName = new Label("No XML file has been loaded yet.");

        TextArea textArea = new TextArea();

        textArea.setEditable(false);

        officeCoord.setSelected((checkHelper.getOfficeCoord()!= "0"));


        row1.setSpacing(10);
        row1.getChildren().addAll(label1, officeCoord, label2, subCoord);
        row2.setSpacing(10);
        row2.getChildren().addAll(loadXML, changeXSD, runChecker, openViewer);
        row2.setAlignment(Pos.CENTER);


        xsd.setSpacing(10);
        xsd.getChildren().addAll(currentXSD, xsdFileName);

        xml.setSpacing(10);
        xml.getChildren().addAll(currentXML, xmlFileName);

        layout.setSpacing(15);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(instruction, row2, xsd, xml, textArea);




        //Handlers
        officeCoord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkHelper.getOfficeCoord()=="0"){
                    checkHelper.setOfficeCoord("1");

                }
                else {
                    checkHelper.setOfficeCoord("0");
                }
                ViewerManager.isWorking();
                checkHelper.writeData();
            }
        });

        loadXML.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileOpener.xmlOpener(popupwindow);
                if(fileOpener.getXmlFile() != null){
                    xmlFileName.setText(fileOpener.getXmlPath());
                    runChecker.setDisable(false);

                }



            }
        });

        changeXSD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileOpener.xsdOpener(popupwindow);
                if(fileOpener.getXsdFile() != null){
                    checkHelper.setXsdFileName(fileOpener.getXsdPath());
                    checkHelper.writeData();
                    xsdFileName.setText(checkHelper.getXsdFileName());
                }
            }
        });


        runChecker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String file1 = "note.xml";
                String file2 = "test.xsd";

                try {
                    xmlValidator.initValidator(checkHelper.getXsdFileName(), fileOpener.getXmlPath());
                    System.out.println(checkHelper.getXsdFileName());
                    textArea.setText(xmlValidator.getResults());
                    if(xmlValidator.isFlag()){
                        openViewer.setDisable(false);

                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        openViewer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popupwindow.close();
            }
        });



        popupwindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });



        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Validate XML First");
        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }



}


