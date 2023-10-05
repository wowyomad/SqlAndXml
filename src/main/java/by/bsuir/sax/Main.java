package by.bsuir.sax;

import by.bsuir.sax.SAXException;

import javax.sound.midi.Soundbank;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;


public class Main {
    private static String DEFAULT_PATH = "default.xml";
    public static void main(String[] args) {
        String path = new File(args.length == 0 ? DEFAULT_PATH : args[0]).getAbsolutePath();

    }
}
