package sample.xmlviewer.data;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import sample.FileHelper;
import sample.XMLValidator;
import sample.xmlviewer.main.ViewerWindowMain;

import java.awt.*;
import java.io.File;

public class ViewerManager {

    private static File xmlFile;
    private static File xdsFile;

    public static Image builderIcon;
    public static Image subdivisionIcon;
    public static Image specIcon;
    public static Image planIcon;
    public static Image officeIcon;

    private static TreeItem<String> treeItem;


    //create listeners
    //private static List<>

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
        //this is the treeview
        //it gets set by File Opener
        //it gets called by ContentPanel
        treeItem = item;
        ViewerWindowMain.contentPanel.setRootItem();


    }

    public static TreeItem<String> getTreeItem() {
        return treeItem;
    }
}
