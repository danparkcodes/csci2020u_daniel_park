package com.example.assignment_2;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.util.LinkedHashMap;

public class AirlineXMLCreator {

    // Take Airline CSV Reader and make into XML
    public void convertAirlineSafetyCSVtoXML() {

        CSVProcessor airlineCSVProcessor = new CSVProcessor("airline_safety.csv");
        String[] csvColumns = new CSVAirlineRowData().getAllColumns();
        LinkedHashMap<String, CSVAirlineRowData> csvAirlineSafetyRecords = airlineCSVProcessor.getAirlineSafetyRecords();

        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element summaryElement = doc.createElement("Summary");
            doc.appendChild(summaryElement);

            // <Stat> for each column in CSV file
            for (int column = 0; column < csvColumns.length; column++ ) {
                // Calculate column stat summaries
                    for (int rowNum = 0; rowNum < csvAirlineSafetyRecords.size(); rowNum++) {

                    }
            }
            // 3 loops for min, max, average each column
             /*   airline;
                availSeatKMPerWeek
                incidents85to99
                fatalAccidents85to99
                fatalities85to99
                incidents00to14
                fatalAccidents00to14
                fatalities00to14
                totalIncidents*/

            // Stat element
            Element stat = doc.createElement("Stat");
            summaryElement.appendChild(stat);

            // Name element
            Element airlineName = doc.createElement("Name");
            airlineName.appendChild(doc.createTextNode("First Airline"));
            stat.appendChild(airlineName);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/com/example/assignment_2/cars.xml"));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Use Airline CSV data calculate summary statistics and write to XML
    public void createAirlineSummaryStatisticsXML () throws ParserConfigurationException {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element summaryElement = doc.createElement("Summary");
            doc.appendChild(summaryElement);

            // Stat element
            Element stat = doc.createElement("Stat");
            summaryElement.appendChild(stat);

            // Name element
            Element airlineName = doc.createElement("Name");
            airlineName.appendChild(doc.createTextNode("First Airline"));
            stat.appendChild(airlineName);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/com/example/assignment_2/cars.xml"));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // The average number of incidents between 1985 – 1999 across all airlines.

    // The average number of incidents between 2000 – 2014 across all airlines.

    // Write data to xml document "airline_summary_statistic.xml"

}
