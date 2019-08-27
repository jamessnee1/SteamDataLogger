package helper;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class XMLHelper {

    //Function to convert XML String to Document
    public static Document convertXMLToDocument(String xml){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try{
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            return doc;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    //Function to write XML String to file
    public static void writeXMLToFile(String xml){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            //Write to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("games.xml"));
            transformer.transform(source, result);
        }
        catch(TransformerConfigurationException t){
            System.err.println(t.getMessage());
        }
        catch(TransformerException t){
            System.err.println(t.getMessage());
        }
        catch(ParserConfigurationException p){
            System.err.println(p.getMessage());
        }
        catch(SAXException s){
            System.err.println(s.getMessage());
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }


    }
}
