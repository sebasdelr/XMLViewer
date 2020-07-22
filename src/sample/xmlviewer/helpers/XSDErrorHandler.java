package sample.xmlviewer.helpers;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.LinkedList;
import java.util.List;

/**
 * custom error handler while validating xml against xsd
 */
public class XSDErrorHandler implements ErrorHandler {
    public static List exceptions = new LinkedList();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        //exceptions.add(handleMessage("Warning", exception));
        exceptions.add(exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        //exceptions.add(handleMessage("Error", exception));
        exceptions.add(exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        //exceptions.add(handleMessage("Fatal", exception));
        exceptions.add(exception);
        //System.out.println("happens");
    }


    public static List getExceptions() {
        return exceptions;
    }
}