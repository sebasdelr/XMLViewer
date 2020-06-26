package sample.xmlviewer.helpers;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class XMLValidator {

    public static List exceptions = new LinkedList();
    private String results = "";
    private String xmlFilename;
    private int errorNum;
    private boolean flag = false;



    public XMLValidator(){




    }

    public void initValidator(String schemaFilename, String xmlFilename) throws Exception{

       // String schemaFilename = "sample/xmlviewer/resources/test.xsd";
        //String xmlFilename = "sample/xmlviewer/resources/note.xml";


        Document document = this.loadXml(xmlFilename);

        try {

            this.validate(document, schemaFilename);


            System.out.println("Validation is successful");


        } catch (SAXException e) {

            System.out.println("Message: " + e.getMessage());


        }



    }


    public void validate(Document document, String schemaFile)
            throws SAXException, IOException {

        XSDErrorHandler xsdErrorHandler = new XSDErrorHandler();

        SchemaFactory factory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI);


//        Schema schema = factory.newSchema(
//                new StreamSource(getInputStream(schemaFile)));

        Schema schema = factory.newSchema(new File(schemaFile));

        Validator validator = schema.newValidator();
        validator.setErrorHandler(xsdErrorHandler);

        validator.validate(new DOMSource(document));

        for(int i = 0; i < xsdErrorHandler.getExceptions().size(); i++){

            SAXParseException tempException = (SAXParseException) xsdErrorHandler.getExceptions().get(i);
            int lineNumber = tempException.getLineNumber();
            int columnNumber = tempException.getColumnNumber();
            String message = tempException.getMessage();

            System.out.println("[ Err ] line nr: " + lineNumber + " column nr: " + columnNumber + " message: " + message);
            this.results+="[ Err ] line nr: " + lineNumber + " column nr: " + columnNumber + " message: " + message + "\n";

        }




        if(xsdErrorHandler.getExceptions().size() == 0){
            this.results = "Validation is successful";
            this.flag = true;
        }

        errorNum = xsdErrorHandler.getExceptions().size();
    }

    private DocumentBuilder createDocumentBuilder()
            throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory
                = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        return builderFactory.newDocumentBuilder();
    }

    private Document loadXml(String xmlToValidate) throws Exception {

        File file = new File(xmlToValidate);
        DocumentBuilder builder = createDocumentBuilder();
        return builder.parse(file);
    }

//    private InputStream getInputStream(String filename) {
//        System.out.println(getClass().getClassLoader().getResourceAsStream(filename));
//        return getClass().getClassLoader().getResourceAsStream(filename);
//    }

    public String getResults() {
        return results;
    }

    public int getErrorNum() {
        return errorNum;
    }

    public boolean isFlag() {
        return flag;
    }
}