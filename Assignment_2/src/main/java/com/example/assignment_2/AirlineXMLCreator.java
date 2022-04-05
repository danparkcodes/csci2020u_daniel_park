package com.example.assignment_2;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Set;

public class AirlineXMLCreator {
    private CSVProcessor airlineCSVProcessor;
    private LinkedHashMap<String,CSVColumnSummaryData> csvColumnSummaryData;
    final String resourcePath = "src/main/resources/com/example/assignment_2/";

    AirlineXMLCreator() throws FileNotFoundException {
        this.airlineCSVProcessor = new CSVProcessor("airline_safety.csv");
        airlineCSVProcessor.parseCSVData();
        this.csvColumnSummaryData = airlineCSVProcessor.getCsvColumnSummaryData();
    }

    public void convertAirlineSafetyCSVtoXML(String filename) throws FileNotFoundException {
        String[] csvColumns = new CSVAirlineRowData().getAllColumns();
        LinkedHashMap<String, CSVAirlineRowData> csvAirlineSafetyRecords = airlineCSVProcessor.getAirlineSafetyRecords();

        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element airlineSafetyElement = doc.createElement("Airline_Safety");
            doc.appendChild(airlineSafetyElement);

            // Create tag for each column summary
            Set<String> keys = csvAirlineSafetyRecords.keySet();
            for (String key: keys) {
                // Airline element
                Element airlineElement = doc.createElement("Airline");
                Attr attrType = doc.createAttribute("name");
                attrType.setValue(csvAirlineSafetyRecords.get(key).getAirline());
                airlineElement.setAttributeNode(attrType);
                airlineSafetyElement.appendChild(airlineElement);
                // avail_seat element
                Element availSeatKmPerWeekElement = doc.createElement("AvailSeatKmPerWeek");
                availSeatKmPerWeekElement.appendChild(doc.createTextNode(
                        Long.toString(csvAirlineSafetyRecords.get(key).getAvailSeatKMPerWeek())));
                airlineElement.appendChild(availSeatKmPerWeekElement);
                // incidents 85 element
                Element incidents85to99Element = doc.createElement("Incidents85to99");
                incidents85to99Element.appendChild(doc.createTextNode(
                        Long.toString(csvAirlineSafetyRecords.get(key).getIncidents85to99())));
                airlineElement.appendChild(incidents85to99Element);
                // fatal accidents 85 element
                Element fatalAccident85to99Element = doc.createElement("FatalAccident85to99");
                fatalAccident85to99Element.appendChild(doc.createTextNode(
                        Long.toString(csvAirlineSafetyRecords.get(key).getFatalAccidents85to99())));
                airlineElement.appendChild(fatalAccident85to99Element);
                // fatalities 85 element
                Element fatalities85to99Element = doc.createElement("Fatalities85to99");
                fatalities85to99Element.appendChild(doc.createTextNode(
                        Long.toString(csvAirlineSafetyRecords.get(key).getFatalities85to99())));
                airlineElement.appendChild(fatalities85to99Element);
                // incident 00 element
                Element incidents00to14Element = doc.createElement("Incidents00to14");
                incidents00to14Element.appendChild(doc.createTextNode(
                        Long.toString(csvAirlineSafetyRecords.get(key).getIncidents00to14())));
                airlineElement.appendChild(incidents00to14Element);
                // fatal accidents 00 element
                Element fatalAccidents00to14Element = doc.createElement("FatalAccidents00to14");
                fatalAccidents00to14Element.appendChild(doc.createTextNode(
                        Long.toString(csvAirlineSafetyRecords.get(key).getFatalAccidents00to14())));
                airlineElement.appendChild(fatalAccidents00to14Element);
                // fatalities 00 element
                Element fatalities00to14Element = doc.createElement("Fatalities00to14");
                fatalities00to14Element.appendChild(doc.createTextNode(
                        Long.toString(csvAirlineSafetyRecords.get(key).getFatalities00to14())));
                airlineElement.appendChild(fatalities00to14Element);
                // total incidents element
                Element totalIncidentsElement = doc.createElement("TotalIncidents");
                totalIncidentsElement.appendChild(doc.createTextNode(
                        Long.toString(csvAirlineSafetyRecords.get(key).getTotalIncidents())));
                airlineElement.appendChild(totalIncidentsElement);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(resourcePath + filename));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAirlineSummaryStatisticsXML (String filename) throws ParserConfigurationException {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element summaryElement = doc.createElement("Summary");
            doc.appendChild(summaryElement);
            Set<String> keys = csvColumnSummaryData.keySet();
            for (String key: keys) {
                // Stat element
                Element stat = doc.createElement("Stat");
                summaryElement.appendChild(stat);

                // Name element
                Element nameElement = doc.createElement("Name");
                nameElement.appendChild(doc.createTextNode(csvColumnSummaryData.get(key).getcolumnName()));
                stat.appendChild(nameElement);

                // Min Element
                Element minElement = doc.createElement("Min");
                minElement.appendChild(doc.createTextNode(Long.toString(csvColumnSummaryData.get(key).getStatMin())));
                stat.appendChild(minElement);

                // Max Element
                Element maxElement = doc.createElement("Max");
                maxElement.appendChild(doc.createTextNode(new DecimalFormat("#")
                        .format((csvColumnSummaryData.get(key).getStatMax()))));
                stat.appendChild(maxElement);

                // Avg Element
                Element avgElement = doc.createElement("Avg");
                avgElement.appendChild(doc.createTextNode(new DecimalFormat("#0.0#")
                        .format((csvColumnSummaryData.get(key).getStatAverage()))));
                stat.appendChild(avgElement);
            }

            // Incident 85-99
            // Stat Element
            Element stat = doc.createElement("Stat");
            summaryElement.appendChild(stat);

            // Name Element
            Element nameElement = doc.createElement("Name");
            nameElement.appendChild(doc.createTextNode("averageIncidents85to99"));
            stat.appendChild(nameElement);

            // Average Element
            Element avgElement = doc.createElement("Avg");
            avgElement.appendChild(doc.createTextNode(new DecimalFormat("#0.0#")
                    .format((csvColumnSummaryData.get("incidents85to99").getStatAverage()))));
            stat.appendChild(avgElement);

            // Incidents 00-14
            // Stat Element
            Element stat2 = doc.createElement("Stat");
            summaryElement.appendChild(stat2);

            // Name Element
            Element nameElement2 = doc.createElement("Name");
            nameElement2.appendChild(doc.createTextNode("averageIncidents00to14"));
            stat2.appendChild(nameElement2);

            // Average Element
            Element avgElement2 = doc.createElement("Avg");
            avgElement2.appendChild(doc.createTextNode(new DecimalFormat("#0.0#")
                    .format((csvColumnSummaryData.get("incidents00to14").getStatAverage()))));
            stat2.appendChild(avgElement2);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(resourcePath + filename));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
