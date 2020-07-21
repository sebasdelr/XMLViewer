package sample.xmlviewer.main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import sample.xmlviewer.data.ViewerManager;

public class FooterPanel {

    private HBox footer = new HBox();

    private String fileLabel;

    public FooterPanel(){
        //initPanel(10);
        footer.getChildren().addAll(new Label(""));
        footer.setPadding(new Insets(0,0,10,10));

    }

    public void setLabel(String filename){
        footer.getChildren().addAll(new Label("File name: " + filename));
        footer.setPadding(new Insets(0,0,10,10));


    }


    public HBox getFooter() {
        return footer;
    }
}
