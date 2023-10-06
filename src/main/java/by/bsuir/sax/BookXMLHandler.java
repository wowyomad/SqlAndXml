package by.bsuir.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.List;

public class BookXMLHandler extends DefaultHandler {
    Catalog myCatalog = null;
    Book book = null;
    String currentCharacters;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("catalog")) {
            myCatalog = new Catalog();
        } else if (qName.equalsIgnoreCase("book")) {
            book = new Book();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        System.out.println("in end element: " + qName);
        switch (qName) {
            case "catalog":
                System.out.println("Done with catalog");
                System.out.println(myCatalog.toString());
                break;
            case "book":
                System.out.println("in book end: " + book);
                myCatalog.push(book);
                break;
            case "author":
                book.setAuthor(currentCharacters);
                break;
            case "title":
                book.setTitle(currentCharacters);
                break;
            case "price":
                book.setPrice(Double.parseDouble(currentCharacters));
                break;
            case "genre":
                book.setGenre(currentCharacters);
                break;
            case "publish_date":
                book.setPublishDate(currentCharacters);
                break;
            case "description":
                book.setDescription(currentCharacters);
                break;

        }
    }

    @Override
    public void characters(char ch[], int start, int length ) {
        currentCharacters = new String(ch, start, length);
    }

    public Catalog getBooks() {
        return myCatalog;
    }

}
