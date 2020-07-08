package sample.xmlviewer.helpers;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

    //private TreeItem<String> treeRootItem = null;
    private ArrayList<TreeItem> treeRootItem = null;
    private int subdivNum = 0;

    private boolean fileLoaded = false;




    public XMLTreeHelper(){




    }
    //Create classes for each element?

    //Create a separate list

    //Create a method for opening a file

    // This method creates an ArrayList of TreeItems (Products)

    private Document document (File file){

//        try{
//
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(file);
//            return doc;
//        }
//
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

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
            doc = db.parse(new FileInputStream(new File(String.valueOf(file))));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //below method adds items to the product list

        //products = nodeList2Tree(doc);

        return doc;

    }

    public void loadData(File file){

        Document doc = document(file);

        treeRootItem = nodeList2Tree(doc);


    }

    public ArrayList<TreeItem> nodeList2Tree(org.w3c.dom.Node node){

        ArrayList<TreeItem> tempList = new ArrayList<TreeItem>();
        NodeList list = node.getChildNodes();

        for (int i=0; i<list.getLength(); i++) {
            org.w3c.dom.Node childNode = list.item(i);

            //node.getNodeType() == Node.ELEMENT_NODE
            if(childNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE){
                String name = childNode.getNodeName();

                String nodeText = "";

                if(childNode.hasChildNodes() && (childNode.getFirstChild().getNodeType() == org.w3c.dom.Node.TEXT_NODE) && (!childNode.getFirstChild().getTextContent().trim().replaceAll("\\s+", " ").isEmpty())){
                    String text = childNode.getFirstChild().getTextContent();
                    nodeText = name + ": " + text;
                }
                else {
                    nodeText = name;
                }

                TreeItem tempItem = new TreeItem(nodeText);
                tempItem.getChildren().addAll(nodeList2Tree(childNode));
                tempList.add(tempItem);

            }




        }



        return tempList;

    }

    public String elementText(org.w3c.dom.Node node, String string){
        return ((Element) node).getElementsByTagName(string)
                .item(0)
                .getTextContent();
    }

    public org.w3c.dom.Node nodeGetter (NodeList nodeList, int index){
        org.w3c.dom.Node node = nodeList.item(index) ;
        return node;
    }

//    public TreeItem<String> getTreeRootItem() {
//        return treeRootItem;
//    }

    public ArrayList<TreeItem> getTreeRootItem() {
        return treeRootItem;
    }

    public int getSubdivNum() {
        return subdivNum;
    }

    public boolean isFileLoaded() {
        return fileLoaded;
    }


}