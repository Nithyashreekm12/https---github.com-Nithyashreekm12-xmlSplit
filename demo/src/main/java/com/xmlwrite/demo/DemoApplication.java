package com.xmlwrite.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] s) throws Exception 
	{
		// String prefix = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "\n";
		// String suffix = "\n</Employees>\n";
		int count = 0;

		try {
			int i = 0;
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLStreamReader xsr = xmlInputFactory.createXMLStreamReader(new FileReader("C:/Users/nithyashree.muddared/xmlSTAXParser/demo/details/input.xml"));
			xsr.nextTag(); // Advance to statements element

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();

			File file = new File("C:/Users/nithyashree.muddared/xmlSTAXParser/demo/details/"+ "split" + i + ".xml");
			FileOutputStream fos = new FileOutputStream(file, true);
			
			
			//appendStuff("<Employees>",file);
             
			while (xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {
				count++;
				t.transform(new StAXSource(xsr), new StreamResult(fos));
				//appendStuff("<Employees>",file);
				if(count == 1) {
		            i++;
					//appendStuff("</Employees>",file);
					//file = new File("C:/Users/nithyashree.muddared/xmlSTAXParser/demo/details/"+ "split" + i + ".xml");
					fos = new FileOutputStream(file, true);
					fos.close();
					appendStuff("<Employees>",file);
				}
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	private static void appendStuff(String content, File file) throws IOException {
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		//FileWriter fwr = new FileWriter(file.getAbsoluteFile(),false);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
	}
}
