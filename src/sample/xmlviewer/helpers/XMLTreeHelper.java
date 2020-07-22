package sample.xmlviewer.helpers;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class XMLTreeHelper {

    private final String RESOURCE_PATH = "/sample/xmlviewer/resources/";

    private ArrayList<TreeItem<String>> treeRootItem = null;

    private boolean fileLoaded = false;

    private final Image folderIcon = new Image(getClass().getResourceAsStream(RESOURCE_PATH+"folder2.png"));
    private final Image fileIcon = new Image(getClass().getResourceAsStream(RESOURCE_PATH+"file.png"));

    public XMLTreeHelper(){

    }


    private Document document (File file){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();

        }


        Document doc = null;

        try {
            doc = db.parse(new FileInputStream(file));

        } catch (SAXException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("There seems to be an error with your file. Make sure you open a valid one.");

            alert.showAndWait();

            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

        return doc;

    }

    public void loadData(File file){

        Document doc = document(file);

        if(doc != null) {

            treeRootItem = nodeList2Tree(doc);
            fileLoaded = true;

        }
        else {
            fileLoaded = false;
        }

    }

    public ArrayList<TreeItem<String>> nodeList2Tree(org.w3c.dom.Node node){

        ArrayList<TreeItem<String>> tempList = new ArrayList<TreeItem<String>>();
        NodeList list = node.getChildNodes();

        for (int i=0; i<list.getLength(); i++) {
            org.w3c.dom.Node childNode = list.item(i);

            if(childNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE){
                String name = childNode.getNodeName();

                String nodeText = "";

                Node rootIcon = null;

                if(childNode.hasChildNodes() && (childNode.getFirstChild().getNodeType() == org.w3c.dom.Node.TEXT_NODE) && (!childNode.getFirstChild().getTextContent().trim().replaceAll("\\s+", " ").isEmpty())){
                    rootIcon =  new ImageView(fileIcon);
                    String text = childNode.getFirstChild().getTextContent();
                    nodeText = name + ": " + text;
                }
                else {
                    rootIcon =  new ImageView(folderIcon);
                    nodeText = name;
                }

                TreeItem<String> tempItem = new TreeItem<>(nodeText, rootIcon);
                tempItem.getChildren().addAll(nodeList2Tree(childNode));
                tempList.add(tempItem);

            }

        }

        return tempList;

    }

    public ArrayList<TreeItem<String>> getTreeRootItem() {
        return treeRootItem;
    }

    public boolean isFileLoaded() {
        return fileLoaded;
    }


}
