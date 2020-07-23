package sample.xmlviewer.data;

import javafx.scene.control.TreeItem;
import sample.xmlviewer.main.ViewerWindowMain;
import java.io.File;
import java.util.ArrayList;

public class ViewerManager {

    private static File xmlFile;
    private static String xsdFilePath;
    private static ArrayList<TreeItem<String>> treeItem;

    public static void setTreeItem(ArrayList<TreeItem<String>> item){
        treeItem = item;
        ViewerWindowMain.contentPanel.setRootItem();

    }

    public static ArrayList<TreeItem<String>> getTreeItem() {
        return treeItem;
    }

    public static void setXmlFile(File xmlFile) {
        ViewerManager.xmlFile = xmlFile;
        ViewerWindowMain.footerPanel.setLabel(getXmlFilePath());
    }

    public static String getXmlFilePath(){
        return xmlFile.getAbsolutePath();
    }

    public static void setXsdFilePath(String xsdFilePath) {
        ViewerManager.xsdFilePath = xsdFilePath;
    }

    public static String getXsdFilePath() {
        return xsdFilePath;
    }

}
