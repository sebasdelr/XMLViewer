package sample.xmlviewer.main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FooterPanel {

    private HBox footer = new HBox();

    public FooterPanel(){
        initPanel(10);

    }

    private void initPanel(int number){
        footer.getChildren().addAll(new Label("Number of Subdivisions: " + number));
        footer.setPadding(new Insets(0,0,10,10));


    }

    public HBox getFooter() {
        return footer;
    }
}
