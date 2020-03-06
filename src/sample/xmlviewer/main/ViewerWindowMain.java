package sample.xmlviewer.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.xmlviewer.openfile.FileOpener;

import java.io.File;

public class ViewerWindowMain extends Application {

    //Main VBox
    private VBox mainVBox = new VBox();

    public static void main(String[] args)
    {
        Application.launch(args);
    }


    @Override
    public void start(Stage stage){
        File file = null;
        FileOpener fileOpener = new FileOpener();
        fileOpener.xmlOpener(stage);

        //Panels
        EmptyContentPanel emptyContentPanel = new EmptyContentPanel();
        ContentPanel contentPanel = new ContentPanel();
        MenuPanel menuPanel = new MenuPanel(stage);
        FooterPanel footerPanel = new FooterPanel();

        HBox hbox = emptyContentPanel.getContent();

//        if(file.exists()){
//            hbox = contentPanel.getContent();
//
//        }


        //Set main box
        mainVBox.getChildren().setAll(menuPanel.getMenuMain(), hbox, footerPanel.getFooter());
        mainVBox.setSpacing(10);

        //Set scene
        Scene scene = new Scene(mainVBox, 530,400);

        stage.setTitle("XML Viewer");
        stage.setScene(scene);
        stage.show();

    }


}
