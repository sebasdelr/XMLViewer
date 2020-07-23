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
import sample.xmlviewer.data.ViewerManager;
import sample.xmlviewer.helpers.CheckHelper;
import sample.xmlviewer.helpers.XMLValidator;
import sample.xmlviewer.openfile.FileOpener;


public class Checker {

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
        HBox row = new HBox();
        HBox xsd = new HBox();
        VBox layout= new VBox();

        Scene scene1= new Scene(layout, 500, 400);

        //Buttons
        Button changeXSD = new Button("Change XSD");
        Button runChecker = new Button("Run Checker");

        runChecker.setDisable(true);

        CheckHelper checkHelper = new CheckHelper();

        if(ViewerManager.getXsdFilePath() != null){
            runChecker.setDisable(false);
        }
        

        //Labels
        Label instruction = new Label("Change XSD File or Use Current:");
        Label currentXSD = new Label("Current XSD File: ");
        Label xsdFileName = new Label(ViewerManager.getXsdFilePath());

        TextArea textArea = new TextArea();

        textArea.setEditable(false);
        textArea.setPrefRowCount(50);
        
        row.setSpacing(10);
        row.getChildren().addAll(changeXSD, runChecker);

        row.setAlignment(Pos.CENTER);


        xsd.setSpacing(10);
        xsd.getChildren().addAll(currentXSD, xsdFileName);


        layout.setSpacing(15);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(instruction, row, xsd, textArea);




        changeXSD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileOpener.xsdOpener(popupwindow);
                if(fileOpener.getXsdFile() != null){

                    ViewerManager.setXsdFilePath(fileOpener.getXsdFile().getPath());
                    checkHelper.writeData();
                    xsdFileName.setText(ViewerManager.getXsdFilePath());
                    runChecker.setDisable(false);
                }
            }
        });


        runChecker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                try {
                    xmlValidator.initValidator(ViewerManager.getXsdFilePath(), ViewerManager.getXmlFilePath());
                    textArea.setText(xmlValidator.getResults());

                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });


        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("XML Validation");
        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }



}


