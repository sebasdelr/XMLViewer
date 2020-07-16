package sample.xmlviewer.helpers;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jdk.nashorn.internal.runtime.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

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
            System.out.println("db here");
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();

        }


        Document doc = null;

        System.out.println("step tep");
        try {
            System.out.println("trying");
            doc = db.parse(new FileInputStream(new File(String.valueOf(file))));
            System.out.println("nope");
        } catch (SAXException e) {
            System.out.println("some error to save your file");
            //e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("There seems to be an error with your file. Make sure you open a valid one.");

            alert.showAndWait();

            return null;

        } catch (IOException e) {
            //e.printStackTrace();
            return null;

        }

        catch (IllegalArgumentException e){
            System.out.println("blah");
        }


        //below method adds items to the product list

        //products = nodeList2Tree(doc);

        System.out.println("still getting here");

        return doc;

    }

    public void loadData(File file){

        System.out.println("file loading to document");

        Document doc = document(file);


        System.out.println("we got to here");

        if(doc != null) {

            treeRootItem = nodeList2Tree(doc);

            fileLoaded = true;

        }
        else {
            fileLoaded = false;
        }




    }

    public ArrayList<TreeItem> nodeList2Tree(org.w3c.dom.Node node){

        System.out.println("hello");

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
