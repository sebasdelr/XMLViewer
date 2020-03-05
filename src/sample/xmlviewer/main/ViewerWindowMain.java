package sample.xmlviewer.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewerWindowMain extends Application {

    //Main VBox
    private VBox mainVBox = new VBox();

    public static void main(String[] args)
    {
        Application.launch(args);
    }


    @Override
    public void start(Stage stage){

        //Panels
        ContentPanel contentPanel = new ContentPanel();
        MenuPanel menuPanel = new MenuPanel();
        FooterPanel footerPanel = new FooterPanel();


        //Set main box
        mainVBox.getChildren().setAll(menuPanel.getMenuMain(), contentPanel.getContent(), footerPanel.getFooter());
        mainVBox.setSpacing(10);

        //Set scene
        Scene scene = new Scene(mainVBox, 530,400);

        stage.setTitle("XML Viewer");
        stage.setScene(scene);
        stage.show();

    }


}
