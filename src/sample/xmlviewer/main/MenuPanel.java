package sample.xmlviewer.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import sample.xmlviewer.data.ViewerManager;
import sample.xmlviewer.helpers.XMLTreeHelper;
import sample.xmlviewer.openfile.FileOpener;
import sample.xmlviewer.settings.Checker;

import java.io.File;

public class MenuPanel {
    private MenuBar menuMain = new MenuBar();
    private Stage stage;

    public MenuPanel(Stage stage){
        this.stage = stage;
        initPanel();
    }

    private void initPanel(){

        FileOpener fileOpener = new FileOpener();
        Checker checker = new Checker(this.stage);

        //Checker checker =

        XMLTreeHelper xmlTreeHelper = new XMLTreeHelper();

        Menu menuFile = new Menu("_File");
        MenuItem menuFileOpen = new MenuItem("_Open");
        MenuItem menuFileChecker = new MenuItem("_Process");
        MenuItem menuFileExit = new MenuItem("_Exit");



        if(xmlTreeHelper.isFileLoaded()){

            System.out.println("something happened");
        }

        menuMain.getMenus().addAll(menuFile);
        menuFile.getItems().addAll(menuFileOpen, menuFileChecker, menuFileExit);

        menuFileChecker.setDisable(true);




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
                if(fileOpener.getXmlFile() != null){
//                    xmlFileName.setText(fileOpener.getXmlPath());
//                    runChecker.setDisable(false);
                    System.out.println("this ran");
                    xmlTreeHelper.loadData(fileOpener.getXmlFile());
                    System.out.println("this ran as well");
                    //xmlPath = this.xmlFile.getPath();
                    if(xmlTreeHelper.isFileLoaded()){
                        ViewerManager.setTreeItem(xmlTreeHelper.getTreeRootItem());
                        ViewerManager.setXmlFile(fileOpener.getXmlFile());
                        menuFileChecker.setDisable(false);
                    }


                }

            }
        });

        menuFileChecker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //open checker
                checker.display();

            }
        });

  }


    public MenuBar getMenuMain() {
        return menuMain;
    }
}
