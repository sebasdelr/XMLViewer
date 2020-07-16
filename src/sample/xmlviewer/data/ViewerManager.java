package sample.xmlviewer.data;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import sample.xmlviewer.main.ViewerWindowMain;

import java.io.File;
import java.util.ArrayList;

public class ViewerManager {

    private static File xmlFile;
//    private static File xdsFile;
//
//    public static Image builderIcon;
//    public static Image subdivisionIcon;
//    public static Image specIcon;
//    public static Image planIcon;
//    public static Image officeIcon;

    //private static TreeItem<String> treeItem;
    private static ArrayList<TreeItem> treeItem;
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

//    public static void setTreeItem(TreeItem<String> item){
//        treeItem = item;
//        ViewerWindowMain.contentPanel.setRootItem();
//
//    }

    public static void setTreeItem(ArrayList<TreeItem> item){
        treeItem = item;
        ViewerWindowMain.contentPanel.setRootItem();

    }

    public static File getXmlFile() {
        return xmlFile;
    }

    public static void setXmlFile(File xmlFile) {
        //System.out.println("maybes heresz");
        ViewerManager.xmlFile = xmlFile;
    }

    //    public static TreeItem<String> getTreeItem() {
//        return treeItem;
//    }

    public static ArrayList<TreeItem> getTreeItem() {
        return treeItem;
    }
}
