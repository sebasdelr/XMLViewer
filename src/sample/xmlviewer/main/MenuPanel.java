package sample.xmlviewer.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import sample.xmlviewer.data.ViewerManager;
import sample.xmlviewer.openfile.FileOpener;

import java.io.File;

public class MenuPanel {
    private MenuBar menuMain = new MenuBar();
    private Stage stage;
    private File file;

    public MenuPanel(Stage stage){
        this.stage = stage;
        initPanel();

    }

    private void initPanel(){

        FileOpener fileOpener = new FileOpener();

        Menu menuFile = new Menu("_File");
        MenuItem menuFileOpen = new MenuItem("_Open");
        MenuItem menuFileChecker = new MenuItem("_Process");
        MenuItem menuFileExit = new MenuItem("_Exit");

        menuMain.getMenus().addAll(menuFile);
        menuFile.getItems().addAll(menuFileOpen, menuFileChecker, menuFileExit);


        menuFileExit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        //Open loads file chooser
        menuFileOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //fileChooser.showOpenDialog(stage);
                fileOpener.xmlOpener(stage);
                ViewerManager.isWorking();
                if (fileOpener.getFile() != null) {
                    //open File method below
                   setFile(fileOpener.getFile());
                }

            }
        });

  }


  public void setFile(File file) {
      this.file = file;
  }



    public File getFile() {
        return file;
    }

    public MenuBar getMenuMain() {
        return menuMain;
    }
}
