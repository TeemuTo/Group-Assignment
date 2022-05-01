package com.example.groupassignment;

import android.icu.text.CaseMap;
import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ShowXMLParser extends DefaultHandler {

    Show show;
    ArrayList<Show> shows = new ArrayList<>();
    final String TAG = "kokeilu";
    StringBuilder buffer = new StringBuilder();


    public ArrayList<Show> parse(InputStream inputStream) throws IOException, SAXException {
        //code to initiate parsing
        Xml.parse(inputStream, Xml.Encoding.UTF_8, this);

        return shows;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();


        Log.d(TAG, "startDocument: ");


    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.d(TAG, "endDocument: ");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);



        if (localName.equals("Title")){
            show = new Show();
            show.setTitle(attributes.getValue("Title"));
        }
        Log.d(TAG, "startElement: " +localName);
        buffer.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (localName.equals("Show")) {
            shows.add(show);
        }
        else if (localName.equals("Title")){

            show.setTitle(buffer.toString());
        }
        else if (localName.equals("LengthInMinutes")){

            show.setLengthInMinutes(buffer.toString());
        }
        else if (localName.equals("Genres")){

            show.setGenres(buffer.toString());
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        buffer.append(ch, start, length);
        Log.d(TAG, "characters: ");
    }
}
