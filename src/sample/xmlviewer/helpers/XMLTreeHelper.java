package sample.xmlviewer.helpers;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class XMLTreeHelper {

    private final String RESOURCE_PATH = "/sample/xmlviewer/resources/";

    private TreeItem<String> treeRootItem = null;
    private int subdivNum = 0;

    private boolean fileLoaded = false;


    public XMLTreeHelper(){


    }
    //Create classes for each element?

    //Create a separate list

    //Create a method for opening a file

    // This method creates an ArrayList of TreeItems (Products)

    private Document document (File file){

        try{

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            return doc;
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }



    public void loadData(File file){


        final Node rootIcon = new ImageView(
                new Image(getClass().getResourceAsStream(RESOURCE_PATH + "monitor.png"))
        );
        treeRootItem = new TreeItem<String>("Builder", rootIcon);

        int divCount = 0;
        int subDivCount = 0;
        Document doc = document(file);
        doc.getDocumentElement().normalize();

        //Divisions

        NodeList nList = doc.getElementsByTagName("Builder");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            divCount++;
            org.w3c.dom.Node nNode = nodeGetter(nList, temp);

            if (nNode.getNodeType() == nNode.ELEMENT_NODE) {
                int subdivNum = 0;
                String divisionName = elementText(nNode, "ReportingName");

                TreeItem<String> divisionItem = new TreeItem<String> (divisionName);
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

                        TreeItem<String> plansItem = new TreeItem<String> ("Plans");
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

        //dont forget counters
        System.out.println("Number of divisions = " + divCount);
        System.out.println("Number of subdivisions = " + subDivCount);

        subdivNum = subDivCount;

        fileLoaded = true;

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

    public TreeItem<String> getTreeRootItem() {
        return treeRootItem;
    }

    public int getSubdivNum() {
        return subdivNum;
    }

    public boolean isFileLoaded() {
        return fileLoaded;
    }


}
