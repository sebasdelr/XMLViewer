package sample.xmlviewer.main;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EmptyContentPanel {
    private HBox content = new HBox();

    public EmptyContentPanel(){
        initPanel();

    }

    private void initPanel(){

        content.getChildren().addAll(new Label("No File Selected."));
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);

    }

    public HBox getContent() {
        return content;
    }
}
