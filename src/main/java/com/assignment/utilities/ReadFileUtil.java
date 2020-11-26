package com.assignment.utilities;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The Class ReadFileUtil.
 */
public class ReadFileUtil {

	/**
	 * CSV data provider.
	 * 
	 * @param testCaseId		test case id.
	 * @param separator			separator.
	 * @param filename			filename.
	 * @return string[][].
	 */
	public static String[][] CSVDataProvider(String testCaseId, String separator, String filename) {
        List<String[]> dataArr = new ArrayList<String[]>();
        BufferedReader br = null;
        String[] values = null;
        String line = "";
        String[][] strArray = null;
        try {
               File file = new File(filename);
               br = new BufferedReader(new FileReader(file));
               while ((line = br.readLine()) != null) {
                      if(!line.startsWith(",")) {
                            String[] text = line.split(",");
                            if (text[0].equals(testCaseId.trim())) {
                                   line = line.substring(line.indexOf(separator) + 1);
                                   values = line.split(separator);
                                   dataArr.add(values);
                                   strArray = dataArr.toArray(new String[0][0]);
                            }
                      }
               }
        } catch (FileNotFoundException ex) {
               System.out.println(ex.getMessage());
        } catch (IOException ex) {
        	System.out.println(ex.getMessage());
        } finally {
               if (br != null) {
                      try {
                            br.close();
                      } catch (IOException ex) {
                    	  System.out.println(ex.getMessage());
                      }
               }
        }
        return strArray;
  }

	/**
	 * Gets the xml value.
	 *
	 * @param variablename	variablename.
	 * @param XMLFile		XML file.
	 * @param module 		module.
	 * @return list.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException the parser configuration exception.
	 * @throws SAXException the SAX exception.
	 */
	public static List<String> getXmlValue(String variablename, String XMLFile, String module)
			throws IOException, ParserConfigurationException, SAXException {
		List<String> valueOfElement = new ArrayList<String>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(XMLFile));
		doc.getDocumentElement().normalize();
		if (doc.hasChildNodes()) {
			NodeList nodeList = doc.getChildNodes();
			for (int count = 0; count < nodeList.getLength(); count++) {
				Node tempNode = nodeList.item(count);
				if (tempNode.hasChildNodes()) {
					NodeList moduleList = tempNode.getChildNodes();
					for (int j = 0; j < moduleList.getLength(); j++) {
						Node moduleNode = moduleList.item(j);
						if (moduleNode.getNodeType() == Node.ELEMENT_NODE && moduleNode.getNodeName() == module) {
							if (moduleNode.hasChildNodes()) {
								NodeList childList = moduleNode.getChildNodes();
								for (int i = 0; i < childList.getLength(); i++) {
									Node childNode = childList.item(i);
									if (childNode.getNodeType() == Node.ELEMENT_NODE
											&& childNode.getNodeName().toString() == "ElementProperty") {
										if (childNode.hasAttributes()) {
											// get attributes names and values
											NamedNodeMap nodeMap = childNode.getAttributes();
											for (int k = 0; k < nodeMap.getLength(); k++) {

												Node node = nodeMap.item(k);
												if (node.getNodeName() == "NameOfElement") {
													if (node.getNodeValue().equals(variablename)) {
														valueOfElement.add(childNode.getTextContent());
														Node propertyType = nodeMap.getNamedItem("Type");
														valueOfElement.add(propertyType.getNodeValue());
														break;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return valueOfElement;
	}

	
}
