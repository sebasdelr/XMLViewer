package sample.xmlviewer.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.cell.TextFieldTreeCell;

public class ViewerWindowMain extends Application {

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    ContentViewer contentViewer = new ContentViewer();

    Circle circle = new Circle(20,20,20);
    Group group = new Group(circle);
    Scene scene = new Scene(contentViewer.getContent(), 530,400);



    @Override
    public void start(Stage stage){



        stage.setTitle("Simple Scene 2");
        stage.setScene(scene);
        stage.show();

    }


}
