package com.journaldev.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class PersonController {
	
	@RequestMapping("/")
	public String welcome() throws Exception {
		transform();
		return "Welcome to Spring Boot REST...";
	}
	
	public void transform() throws Exception {
		ClassLoader classLoader = this.getClass().getClassLoader();
		Source             xslt        = new StreamSource(new File(classLoader.getResource("transform.xslt").getFile()));
		Source             text        = new StreamSource(new File(classLoader.getResource("input.xml").getFile()));
		TransformerFactory factory     = TransformerFactory.newInstance();
		Transformer        transformer = factory.newTransformer(xslt);
		
		transformer.transform(text, new StreamResult(new File("C:/Output/output.html")));
    }
	
}
