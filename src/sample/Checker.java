package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class Checker {


    public static void display()
    {
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







        row1.getChildren().addAll(label1, officeCoord, label2, subCoord);

        Label instruction = new Label("Set settings:");

        Button runChecker = new Button("Run Checker");

        VBox layout= new VBox(10);


        layout.getChildren().addAll(instruction, row1, runChecker);


        Scene scene1= new Scene(layout, 400, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}

