package sample.xmlviewer.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.xmlviewer.settings.Checker;


public class ViewerWindowMain extends Application {

    //Main VBox
    private VBox mainVBox = new VBox();
    public static ContentPanel contentPanel = new ContentPanel();

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){

        //Panels and pop up
        Checker checker = new Checker(stage);
        MenuPanel menuPanel = new MenuPanel(stage);
        FooterPanel footerPanel = new FooterPanel();

        //Load checker
        //checker.display();


        //Set main box
        mainVBox.getChildren().setAll(menuPanel.getMenuMain(), contentPanel.getContent(), footerPanel.getFooter());
        //mainVBox.getChildren().setAll(menuPanel.getMenuMain(), contentPanel.getContent());

        mainVBox.setSpacing(10);


        //Set scene
        Scene scene = new Scene(mainVBox, 550,400);

        stage.setTitle("XML Viewer");
        stage.setScene(scene);
        stage.show();



    }




}
