package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.File;
import java.sql.SQLOutput;


public class Checker {

    TextArea textArea = new TextArea();


    public static void display()
    {

        File xmlFile;
        XMLValidator xmlValidator = new XMLValidator();

        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");
        CheckHelper checkHelper = new CheckHelper();




        HBox row1 = new HBox();
        row1.setSpacing(10);
        Label label1 = new Label("Office coordinates");
        CheckBox officeCoord = new CheckBox();
        officeCoord.setSelected((checkHelper.getOfficeCoord()!= 0));
        Label label2= new Label("Subdivision coordinates");
        CheckBox subCoord = new CheckBox();

// open button for xml, open button for XSD

        row1.getChildren().addAll(label1, officeCoord, label2, subCoord);

        Label instruction = new Label("Set settings:");

        Button runChecker = new Button("Run Checker");
        Button loadXML = new Button("Load XML");
        Button loadXDS = new Button("Load XDS");

        VBox layout= new VBox(10);
        layout.setPadding(new Insets(10,0,10,10));


        layout.getChildren().addAll(instruction, row1, runChecker, loadXDS, loadXML);


        Scene scene1= new Scene(layout, 400, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("XML", "*.xds,*.xml")
        );

        officeCoord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkHelper.getOfficeCoord()==0){
                    checkHelper.setOfficeCoord(1);

                }
                else {
                    checkHelper.setOfficeCoord(0);
                }
                checkHelper.writeData();
            }
        });

        runChecker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String file1 = "note.xml";
                String file2 = "test.xsd";
                xmlValidator.validateXMLSchema(file2, file1);
                //writeMessage()




            }
        });







    }

    private void writeMessage(String msg)
    {
        this.textArea.setText(msg);
    }



}


