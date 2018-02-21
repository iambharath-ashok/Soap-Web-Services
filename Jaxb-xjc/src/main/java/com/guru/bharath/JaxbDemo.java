/**
 * 
 */
package com.guru.bharath;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.example.patient.Gender;
import org.example.patient.Patient;

/**
 * @author AB40286
 *
 */
public class JaxbDemo {

	public static void main(String[] args) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);

			/*
			 * Marshalling is a process of transforming the Java objects into XML
			 *
			 **/
			Marshaller marshaller = jaxbContext.createMarshaller();
			Patient patient = new Patient();
			patient.setAge(348);
			patient.setGender(Gender.M);

			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(patient, stringWriter);
			System.out.println(stringWriter);

			/*
			 * Unmarshalling is a process of transforming the XML to Java Objects
			 *
			 **/
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Patient unmarshalledPatient = (Patient) unmarshaller.unmarshal(
					new StringReader(stringWriter.toString()));

			System.out.println(unmarshalledPatient.getAge());

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
