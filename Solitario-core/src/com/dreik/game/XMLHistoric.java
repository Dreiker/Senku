package com.dreik.game;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class XMLHistoric {

	private Document document;
	private Element root;
	
	public XMLHistoric() {

		try {
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();
			
			root = document.createElement("Match");
			document.appendChild(root);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMove(int x, int y) {
		Element move = document.createElement("Move");
		root.appendChild(move);
		
		Element posX = document.createElement("X");
		posX.setTextContent(String.valueOf(x));
		move.appendChild(posX);
		
		Element posY  = document.createElement("Y");
		posY.setTextContent(String.valueOf(y));
		move.appendChild(posY);
	}
	
	public void addBack(int x, int y) {
		Element move = document.createElement("Back");
		root.appendChild(move);
		
		Element posX = document.createElement("X");
		posX.setTextContent(String.valueOf(x));
		move.appendChild(posX);
		
		Element posY  = document.createElement("Y");
		posY.setTextContent(String.valueOf(y));
		move.appendChild(posY);
		
	}
}
