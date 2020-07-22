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

    private String results = "";
    private int errorNum;
    private boolean flag = false;



    public XMLValidator(){

    }

    public void initValidator(String schemaFilename, String xmlFilename) throws Exception{


        File file = new File(xmlFilename);

        try {

            this.validate(file, schemaFilename);
            System.out.println("Validation is successful");

        } catch (SAXException e) {

            System.out.println("Message: " + e.getMessage());

        }

    }


    public void validate(File file, String schemaFile)

            throws SAXException, IOException {

        XSDErrorHandler xsdErrorHandler = new XSDErrorHandler();

        SchemaFactory factory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI);


        Schema schema = factory.newSchema(new File(schemaFile));

        Validator validator = schema.newValidator();
        validator.setErrorHandler(xsdErrorHandler);

        validator.validate(new StreamSource(file));

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

    public String getResults() {
        return results;
    }


}