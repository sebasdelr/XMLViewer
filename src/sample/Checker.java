package sample;

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



HBox row1 = new HBox();
row1.setSpacing(10);
        Label label1 = new Label("Office coordinates");
        CheckBox officeCoord = new CheckBox();
        Label label2= new Label("Subdivision coordinates");
        CheckBox subCoord = new CheckBox();







row1.getChildren().addAll(label1, officeCoord, label2, subCoord);

        Label instruction = new Label("Set settings:");

        Button runChecker = new Button("Run Checker");

        VBox layout= new VBox(10);


        layout.getChildren().addAll(instruction, row1, runChecker);

        //layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 400, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}

