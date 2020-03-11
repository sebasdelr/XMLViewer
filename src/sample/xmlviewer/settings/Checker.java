package sample.xmlviewer.settings;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

        //Boxes
        HBox row1 = new HBox();
        VBox layout= new VBox();

        //Buttons
        Button runChecker = new Button("Run Checker");
        Button loadXML = new Button("Load XML");

        //Checkboxes
        CheckBox officeCoord = new CheckBox();
        CheckBox subCoord = new CheckBox();

        //Labels
        Label instruction = new Label("Set settings:");
        Label label1 = new Label("Office coordinates");
        Label label2= new Label("Subdivision coordinates");
        Label xdsFile;

        CheckHelper checkHelper = new CheckHelper();


        row1.setSpacing(10);
        row1.getChildren().addAll(label1, officeCoord, label2, subCoord);

        layout.setSpacing(10);
        layout.setPadding(new Insets(10,0,10,10));
        layout.getChildren().addAll(instruction, row1, runChecker, loadXML);


        officeCoord.setSelected((checkHelper.getOfficeCoord()!= 0));

        //Handlers
        officeCoord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkHelper.getOfficeCoord()==0){
                    checkHelper.setOfficeCoord(1);

                }
                else {
                    checkHelper.setOfficeCoord(0);
                }
                ViewerManager.isWorking();
                checkHelper.writeData();
            }
        });

        loadXML.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileOpener.xmlOpener(popupwindow);
            }
        });


//        runChecker.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                String file1 = "note.xml";
//                String file2 = "test.xsd";
//                xmlValidator.validateXMLSchema(file2, file1);
//
//
//            }
//        });

        Scene scene1= new Scene(layout, 400, 250);

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");
        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }



}


