package sample;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidator {




    public XMLValidator() {



    }

    public static boolean validateXMLSchema(String xsdFile, String xmlFile) {

        try {
            SchemaFactory schemafactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemafactory.newSchema(new File(xsdFile));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
            System.out.println("Ran");

        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}