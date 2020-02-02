package sample;

import java.io.File;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.event.EventHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLViewer extends Application
{
    // Create the TreeView
    private final TreeView treeView = new TreeView();
    // Create the TextArea
    private final TextArea textArea = new TextArea();
    // Create the TextField
    private TextField textField = new TextField();

    private final Node rootIcon = new ImageView(
            new Image(getClass().getResourceAsStream("monitor.png"))
    );

    public static void main(String[] args)
    {
        Application.launch(args);
    }



    @Override
    public void start(Stage stage)
    {





        TreeItem<String> treeRootItem = new TreeItem<String>("Builder", rootIcon);
        // Add children to the root

//        for (int i = 1; i < 6; i++) {
//            TreeItem<String> item = new TreeItem<String> ("Message" + i);
//            rootItem.getChildren().add(item);
//        }


        try {
            int divCount = 0;
            int subDivCount = 0;
            File inputFile = new File("xml.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Builder");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                divCount++;
                org.w3c.dom.Node nNode = nodeGetter(nList, temp);

                if (nNode.getNodeType() == nNode.ELEMENT_NODE) {
                    int subdivNum = 0;
                    String divisionName = elementText(nNode, "ReportingName");

                    System.out.println("Division Name : "
                            + divisionName);

                    TreeItem<String> divisionItem = new TreeItem<String> (divisionName, new ImageView(
                            new Image(getClass().getResourceAsStream("carpeta.png"))
                    ));
                    treeRootItem.getChildren().add(divisionItem);

                    NodeList children = nNode.getChildNodes();


                    for (int temp1 = 0; temp1 < children.getLength(); temp1++) {
                        org.w3c.dom.Node current = nodeGetter(children, temp1);

                        if(current.getNodeName().equals("Subdivision"))
                        {


                            String subdivisionName = elementText(current, "SubdivisionName");

                            TreeItem<String> subdivisionItem = new TreeItem<String> (subdivisionName);
                            divisionItem.getChildren().add(subdivisionItem);

                            System.out.println("\t Subdivision Name: " + subdivisionName);
                            subdivNum++;
                            subDivCount++;

                            NodeList plans = current.getChildNodes();

                            for (int temp5 = 0; temp5 < plans.getLength(); temp5++) {
                                org.w3c.dom.Node currentO = nodeGetter(plans, temp5);



                                if(currentO.getNodeName().equals("SalesOffice"))
                                {

                                    TreeItem<String> officeAddresItem = new TreeItem<String> ("Office Address");
                                    subdivisionItem.getChildren().add(officeAddresItem);

                                    String salesStreet = elementText(current, "Street1");
                                    String salesCity = elementText(current, "City");
                                    String salesState = elementText(current, "State");



                                    TreeItem<String> officeStreetItem = new TreeItem<String> (salesStreet + ", " + salesCity + ", " + salesState);


                                    officeAddresItem.getChildren().add(officeStreetItem);


                                    System.out.println("\t\t Sales Office Address Street: " + salesStreet);
                                    System.out.println("\t\t Sales Office Address City: " + salesCity);
                                    System.out.println("\t\t Sales Office Address State: " + salesState);


                                }



                            }

                            System.out.println("\t\t PLANS:");

                            TreeItem<String> plansItem = new TreeItem<String> ("Plans", new ImageView(
                                    new Image(getClass().getResourceAsStream("house.png"))
                            ));
                            subdivisionItem.getChildren().add(plansItem);



                            for (int temp2 = 0; temp2 < plans.getLength(); temp2++) {
                                org.w3c.dom.Node currents = nodeGetter(plans, temp2);

                                if(currents.getNodeName().equals("Plan"))
                                {



                                    String planName = elementText(currents, "PlanName");
                                    String planNumber = elementText(currents, "PlanNumber");

                                    TreeItem<String> plansDetail = new TreeItem<String> (planName + " : " + planNumber);
                                    plansItem.getChildren().add(plansDetail);

                                    System.out.println("\t\t Plan Name: " + planName);
                                    System.out.println("\t\t Plan Number: " + planNumber);

//                                    subdivNum++;
//                                    subDivCount++;

                                    NodeList specs = currents.getChildNodes();

                                    for (int temp3 = 0; temp3 < specs.getLength(); temp3++) {
                                        org.w3c.dom.Node currentp = nodeGetter(specs, temp3);

                                        if(currentp.getNodeName().equals("Spec"))
                                        {

                                            System.out.println("\t\t\t --------------------");

                                            String specNumber = elementText(currentp, "SpecNumber");

                                            TreeItem<String> specDetail = new TreeItem<String> (specNumber);
                                            plansDetail.getChildren().add(specDetail);


                                            System.out.println("\t\t\t Spec Number: " + specNumber);

                                            //look for method to do these immediate address nodes

                                            TreeItem<String> specAddress = new TreeItem<String> ("Address");
                                            specDetail.getChildren().add(specAddress);

                                            NodeList specsAddress = currentp.getChildNodes();

                                            for (int temp4 = 0; temp4 < specsAddress.getLength(); temp4++) {
                                                org.w3c.dom.Node currentsad = nodeGetter(specsAddress, temp4);



                                                if(currentsad.getNodeName().equals("SpecAddress"))
                                                {


                                                    String specAddressStreet = elementText(currentsad, "SpecStreet1");
                                                    String specAddressCity = elementText(currentsad, "SpecCity");
                                                    String specAddressState = elementText(currentsad, "SpecState");
                                                    String specAddressZip = elementText(currentsad, "SpecZIP");


                                                    System.out.println("\t\t\t\t Spec Address Street: " + specAddressStreet);
                                                    System.out.println("\t\t\t\t Spec Address City: " + specAddressCity);
                                                    System.out.println("\t\t\t\t Spec Address State: " + specAddressState);
                                                    System.out.println("\t\t\t\t Spec Address Zip: " + specAddressZip);

                                                    TreeItem<String> specStreetItem = new TreeItem<String> (specAddressStreet + ", " + specAddressCity + ", " + specAddressState + ", " + specAddressZip);
                                                    specAddress.getChildren().add(specStreetItem);

                                                }



                                            }



                                            System.out.println("\t\t\t Spec Price: " +
                                                    elementText(currentp, "SpecPrice")

                                            );
                                            System.out.println("\t\t\t Spec Square Feet: " +
                                                    elementText(currentp, "SpecSqft")

                                            );
                                            System.out.println("\t\t\t Spec Baths: " +
                                                    elementText(currentp, "SpecBaths")

                                            );
                                            System.out.println("\t\t\t Spec Bedrooms: " +
                                                    elementText(currentp, "SpecBedrooms")

                                            );
//                                    subdivNum++;
//                                    subDivCount++;
                                        }



                                    }
                                }


                            }
                            //System.out.println("\t Number of plans in " + divisionName + ": " + subdivNum);
                        }


                    }
                    System.out.println("\t Number of subdivisions in " + divisionName + ": " + subdivNum);

                }


            }
            System.out.println("Number of divisions = " + divCount);
            System.out.println("Number of subdivisions = " + subDivCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        treeView.setEditable(false);
        // Set a cell factory to use TextFieldTreeCell
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());
        // Select the root node
        treeView.getSelectionModel().selectFirst();
        // Create the root node and adds event handler to it
        TreeItem rootItem = new TreeItem("Builder");




        rootItem.getChildren().addAll(treeRootItem);
        // Set the Root Node
        treeView.setRoot(treeRootItem);




        rootItem.setExpanded(false);


        EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
            handleMouseClicked(event);
        };

        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);



        VBox rightPane = getRightPane();

        // Create the HBox
        HBox root = new HBox();
        // Set the horizontal space between each child in the HBox
        root.setSpacing(20);
        // Add the TreeView to the HBox
        root.getChildren().addAll(treeView,rightPane);

        // Create the Scene
        Scene scene = new Scene(root,600,500);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title for the Scene
        stage.setTitle("TreeView Example 4");
        // Display the stage
        stage.show();
    }

    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if ((node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null))) {
            TreeItem selectedItem = (TreeItem) treeView.getSelectionModel().getSelectedItem();
            String name = (String) selectedItem.getValue();

            ObservableList<TreeItem> tempItems = selectedItem.getChildren();


            int level = treeView.getTreeItemLevel(selectedItem);
            if(level == 3){
                String temp = "";
                for (int x = 0; x < tempItems.size(); x++ ) {
                    temp = temp.concat(tempItems.get(x).getValue() + "\n");


                }

                //address view vs plan spec view
                writeMessage(name);
                writeNodes(temp);
            }

        }
    }

    // This method creates a VBox and it´s components and returns it to the calling Method
    private VBox getRightPane()
    {

        // Set the preferred number of text rows
        textArea.setPrefRowCount(15);
        // Set the preferred number of text columns
        textArea.setPrefColumnCount(20);

        // Create the HBox
        HBox hbox = new HBox();
        // Add Children to the HBox
        hbox.getChildren().addAll(new Label("Item:"), textField);

        hbox.setSpacing(10);


        // Create the VBox
        VBox vbox = new VBox();
        // Add children to the VBox
        vbox.getChildren().addAll(new Label("Select an item to add to or remove."),hbox,
                new Label("Message Log:"), textArea);
        // Set the vertical space between each child in the VBox
        vbox.setSpacing(20);


        return vbox;
    }




    // Helper Methods for the Event Handlers


    // Method for Logging
    private void writeMessage(String msg)
    {
        this.textField.setText(msg);
    }

    private void writeNodes(String msg){
        this.textArea.setText(msg);
    }

    public static String elementText(org.w3c.dom.Node node, String string){
        return ((Element) node).getElementsByTagName(string)
                .item(0)
                .getTextContent();
    }

    public static org.w3c.dom.Node nodeGetter (NodeList nodeList, int index){

        org.w3c.dom.Node node = nodeList.item(index) ;
        return node;
    }
}

