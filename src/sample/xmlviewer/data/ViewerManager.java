package sample.xmlviewer.data;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import sample.xmlviewer.main.ViewerWindowMain;

import java.io.File;

public class ViewerManager {

//    private static File xmlFile;
//    private static File xdsFile;
//
//    public static Image builderIcon;
//    public static Image subdivisionIcon;
//    public static Image specIcon;
//    public static Image planIcon;
//    public static Image officeIcon;

    private static TreeItem<String> treeItem;
    private static ViewerWindowMain windowMain;


    public static void setWindowMain(ViewerWindowMain window){
        windowMain = window;
    }

    public static ViewerWindowMain getWindowMain(){
        return windowMain;
    }

    public static void isWorking(){
        System.out.println("is working");
    }

    public static void setTreeItem(TreeItem<String> item){
        treeItem = item;
        ViewerWindowMain.contentPanel.setRootItem();

    }

    public static TreeItem<String> getTreeItem() {
        return treeItem;
    }
}
