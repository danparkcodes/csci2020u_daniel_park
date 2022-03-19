package com.example.midtermpart2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/** Represents XML parser for studentinfo.xml file
 */
public class XMLReader {
    private String name;
    private String studentID;
    private String email;
    private String softwareDescription;

    /** Parses all text data from studentinfo.xml file
     */
    public void parseData() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            try {
                Document document = builder.parse("src/main/resources/studentinfo.xml");
                // Find student id
                NodeList studentList = document.getElementsByTagName("student");
                Element studentElement = (Element) studentList.item(0);
                studentID = studentElement.getAttribute("id");
                // Find name
                NodeList nameList = document.getElementsByTagName("name");
                Element nameElement = (Element) nameList.item(0);
                name = nameElement.getTextContent();
                // Find email
                NodeList emailList = document.getElementsByTagName("email");
                Element emailElement = (Element) emailList.item(0);
                email = emailElement.getTextContent();
                // Find software description
                NodeList softwareDescList = document.getElementsByTagName("software-description");
                Element softwareDescElement = (Element) softwareDescList.item(0);
                softwareDescription = softwareDescElement.getTextContent();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getSoftwareDescription() {
        return softwareDescription;
    }
    public String getStudentID() {
        return studentID;
    }
}
