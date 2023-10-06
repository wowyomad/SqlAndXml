package by.bsuir.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookXMLHandler extends DefaultHandler {
    Catalog books = null;
    Book book = null;
    StringBuilder content;

    public BookXMLHandler() {
        books = new Catalog();
        content = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
         if (qName.equalsIgnoreCase("book")) {
            book = new Book();
        }
        content.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        switch (qName) {
            case "catalog":
                break;
            case "book":
                books.push(book);
                break;
            case "author":
                book.setAuthor(content.toString());
                break;
            case "title":
                book.setTitle(content.toString());
                break;
            case "price":
                book.setPrice(Double.parseDouble(content.toString()));
                break;
            case "genre":
                book.setGenre(content.toString());
                break;
            case "publish_date":
                book.setPublishDate(content.toString());
                break;
            case "description":
                book.setDescription(content.toString());
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length ) {
        content.append(ch, start, length);
    }

    public Catalog getBooks() {
        return books;
    }

}
