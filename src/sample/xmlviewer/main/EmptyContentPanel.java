package sample.xmlviewer.main;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class EmptyContentPanel {
    private HBox content = new HBox();
    private TextArea textArea = new TextArea("No File Selected.");

    public EmptyContentPanel(){
        initPanel();

    }

    private void initPanel(){

        textArea.setPrefRowCount(40);
        textArea.setPrefColumnCount(45);
        textArea.setDisable(true);

        content.getChildren().addAll(textArea);
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);

    }

    public HBox getContent() {
        return content;
    }
}
