package sample.xmlviewer.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ViewerWindowMain extends Application {

    //Main VBox
    private VBox mainVBox = new VBox();
    public static ContentPanel contentPanel = new ContentPanel();
    public static FooterPanel footerPanel = new FooterPanel();

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){

        //Panels and pop up

        MenuPanel menuPanel = new MenuPanel(stage);

        mainVBox.getChildren().setAll(menuPanel.getMenuMain(), contentPanel.getContent(), footerPanel.getFooter());

        mainVBox.setSpacing(10);


        //Set scene
        Scene scene = new Scene(mainVBox, 550,400);

        stage.setTitle("XML Viewer");
        stage.setScene(scene);
        stage.show();



    }




}
