package sample.xmlviewer.main;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuPanel {
    private MenuBar menuMain = new MenuBar();

    public MenuPanel(){

        initPanel();

    }

    private void initPanel(){
        Menu menuFile = new Menu("_File");

        menuMain.getMenus().addAll(menuFile);

        MenuItem menuFileOpen = new MenuItem("_Open");
        MenuItem menuFileChecker = new MenuItem("_Process");
        MenuItem menuFileExit = new MenuItem("_Exit");

        menuFile.getItems().addAll(menuFileOpen, menuFileChecker, menuFileExit);


    }

    public MenuBar getMenuMain() {
        return menuMain;
    }
}
