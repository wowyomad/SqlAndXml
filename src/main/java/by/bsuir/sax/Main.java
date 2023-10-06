package by.bsuir.sax;

import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


public class Main {
    private static final String DEFAULT_PATH = "src/main/resources/books.xml";
    public static void main(String[] args) {
        Catalog catalog;
        String path = new File(args.length == 0 ? DEFAULT_PATH : args[0]).getAbsolutePath();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            BookXMLHandler handler = new BookXMLHandler();
            parser.parse(new File(path), handler);
            catalog = handler.getBooks();
            System.out.println(catalog);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }
}
