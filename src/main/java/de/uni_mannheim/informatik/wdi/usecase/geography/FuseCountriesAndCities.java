package de.uni_mannheim.informatik.wdi.usecase.geography;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FuseCountriesAndCities {

    public static void main(String[] args) throws Exception{
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document finalFile  = builder.newDocument();
        Document countries  = builder.parse(new File("usecase/geography/output/datafusion/CountriesWithCitiesList.xml"));
        Document cities     = builder.parse(new File("usecase/geography/output/datafusion/CitiesFusedWithoutCountryIdentifier.xml"));
        
        NodeList countriesList = countries.getElementsByTagName("country");
        
        
        
        Element countriesElement = finalFile.createElement("countries");
        
        
        for(int i = 0; i < countriesList.getLength(); i++){
        	System.out.println("Country: " + i);
            Node country = countriesList.item(i);
            NodeList countryData = country.getChildNodes();
            Element countryElement = finalFile.createElement("country");
            for(int j = 0; j < countryData.getLength(); j++){   
                Node data = countryData.item(j);
                if(data.getNodeName().equals("cities")){
                    Element citiesElement = finalFile.createElement("cities");
                    ArrayList<Node> cityList = new ArrayList<Node>();
                    NodeList citiesList = data.getChildNodes();
                    for(int k = 0; k < citiesList.getLength(); k++){
                        Node city = citiesList.item(k);
                        HashSet<String> contains = new HashSet<String>();
                        if(city.getChildNodes().item(1)!= null){
                            
                            Node cityWithContent = resolveCity(city.getChildNodes().item(1).getTextContent(), cities);
                            if(cityWithContent != null){
                                System.out.println("test");
                                if(!contains.contains(cityWithContent.getChildNodes().item(1).getTextContent())){
                                    contains.add(cityWithContent.getChildNodes().item(1).getTextContent());
                                    
                                    cityList.add(cityWithContent);  
                                }
                                 
                               
                            }
                            
                        }
                        
                        
                       
                        
                    }
                    
                    for(Node unique:cityList){
                        Node newNode = finalFile.importNode(unique, true);
                        citiesElement.appendChild(newNode);
                    }
                    Node newNode = finalFile.importNode(citiesElement, true);
                    countryElement.appendChild(newNode);
                    
                }else{
                    Node newNode = finalFile.importNode(data, true);
                    countryElement.appendChild(newNode);
                }
            }
            
//            Node newNode = finalFile.importNode(countryElement, true);
            countriesElement.appendChild(countryElement);
            
        }
        
        finalFile.appendChild(countriesElement);
        
        String filename = "usecase/geography/output/datafusion/CountriesInclFusedCities.xml";

        Source source = new DOMSource(finalFile);

        File file = new File(filename);
        Result result = new StreamResult(file);

        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);
        
        
    }
    
    public static Node resolveCity(String id, Document doc){
        NodeList cities = doc.getElementsByTagName("city");
        for(int i = 0; i < cities.getLength(); i++){
//            System.out.println("test");
            Node city = cities.item(i);
            String id2 = city.getChildNodes().item(1).getTextContent();
//            System.out.println(id2 + "   " + id);
            if(id2.contains(id)) return city;
        }
        return null;
    }
    
    public boolean containsCity(NodeList nl){
        
        
        return false;
    }
    
    

}
