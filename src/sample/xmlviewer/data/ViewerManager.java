package sample.xmlviewer.data;

import javafx.scene.control.TreeItem;
import sample.xmlviewer.main.ViewerWindowMain;
import java.io.File;
import java.util.ArrayList;

public class ViewerManager {

    private static File xmlFile;
    private static String xmlFileName;
    private static ArrayList<TreeItem<String>> treeItem;

    public static void isWorking(){
        System.out.println("is working");
    }

    public static void setTreeItem(ArrayList<TreeItem<String>> item){
        treeItem = item;
        ViewerWindowMain.contentPanel.setRootItem();

    }

    public static void setXmlFile(File xmlFile) {
        ViewerManager.xmlFile = xmlFile;
    }

    public static File getXmlFile() {
        return xmlFile;
    }


    public static ArrayList<TreeItem<String>> getTreeItem() {
        return treeItem;
    }

    public static void setXmlFileName(String xmlFileName) {
        ViewerWindowMain.footerPanel.setLabel(xmlFileName);
    }

    public static String getXmlFileName() {
        return xmlFileName;
    }

    public static String getXmlFilePath(){
        return xmlFile.getAbsolutePath();
    }
}
