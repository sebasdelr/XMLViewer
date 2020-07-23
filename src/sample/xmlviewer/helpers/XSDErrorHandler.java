package sample.xmlviewer.helpers;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.LinkedList;
import java.util.List;


public class XSDErrorHandler implements ErrorHandler {
    public static List exceptions = new LinkedList();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        exceptions.add(exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        exceptions.add(exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        exceptions.add(exception);
    }


    public static List getExceptions() {
        return exceptions;
    }
}