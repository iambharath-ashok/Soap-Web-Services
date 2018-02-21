# JAX-WS

	- JAX-WS -> JAVA API for XML based web Service
	_ JAX-WS is a specification from Oracle
	- JAX-WS provides specification and API for developing SOAP based Web Services
		- Specification
			- Specification are set of rules defined by Oracle
			- Web Service Engine like CXF and GlassFish will follow the rules
			- CXF and GlassFish
		- API
			- API consists of Annotations
			- Developers will use the annotation on top of classes
			- We can implement both service provider and consumer using these annotations
			- All JAX-WS annotations are runtime annotations
		
## Core Annotations of JAX-WS

##### @javax.jws.WebService

	- Used to indicate the endpoint of service
	- Will used on top of class or interface
	
		@WebService("/order")
		public class Order{
		}
	
##### @javax.jws.WebMethod
	
	- Will used on top of webservice method
	- Web service method handles the incoming soap request	
	- EX:	
		@WebMethod	
		@WebResult(name="order") Order getOrder(@WebParam(name="orderId") Long orderId)

##### @javax.jws.WebFault
	
	- WebFault annotation will be used to send out as fault code in Soap response
	- WebFault needs to be put on top of class that extends the Exception class
	- Whenever an exception throws Soap Fault code will sent out along with response 

##### @javax.jws.soap.SOAPBinding
	
	- Used to define, how to process the incoming SOAP messages 
	- By default uses Document and Literal type binding
	- Can be specified with custom one
		- @SOAPBinding(style=Syle.RPC, use= Use.LITERAL) 
		
##### Wrappers

	- @javax.jws.ws.RequestWrapper 
	- @javax.jws.ws.ResponseWrapper
	- By default, JAX-WS wraps or maps incoming SOAP messages to Java objects vice versa
	- RequestWrapper and ResponseWrapper are used to provide custom impl for mapping the SOAP messages to Java Objects and vice versa
	
	
# JAXB

	- JAXB stands for Java Architecture for XML Binding
	- JAXB API maps the XML with Java Objects and vice versa
	- JAXB hides the complexity of XML parsing and mapping them to corresponding Java objects
	- Developer instead of interacting with XML, can interact with Java objects
	- JAXB is similar to Hibernate, JAXB is for XML like Hibernate is for SQL
	- Current Version of JAXB is 2.2
	- JAXB specification provides 3 tools
		- XJC 
			- Converts XML to Java Objects 
			- Works at Compile Time
		- SCHEMAGEN 
			- Converts Java Objects to XML 
			- Works at Compile Time
		- RUNTIME API -> JAVA
			- Java Objects to XML
	
	- RUNTIME API
		- Works at Runtime
		- Converts Java Objects to XML and XML to Java Objects 
		- Consists of Marshaling class, Unmarshaling class, Set of Annotations
		- It will be used for Code First Approach
			- Mark the classes with Annotations
			- Use Marshal class to marshal or serialize the Java Objects to XML
			- Unmarshal class to desirialize the Java Objects to XML

## Generating Java Classes from XSD using XJC

	- JAX-WS has added a specification from Java 1.6 
	- Can be found at /jdk/bin directory
	- Java classes will be generated using jaxb-maven plugin at compile time
	- Plugin details
		<plugin>
				<groupId>org.jvnet.jaxb2.maven2</groupId>
				<artifactId>maven-jaxb2-plugin</artifactId>
				<version>0.9.0</version>
				<executions>
					<execution>
						<goals>
							<goal>generate</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<schemaDirectory>${project.basedir}/src/main/xsd</schemaDirectory>
					<schemaIncludes>
						<include>Patient.xsd</include>
					</schemaIncludes>
					<bindingDirectory>${project.basedir}/src/main/xsd</bindingDirectory>
					<bindingIncludes>
						<include>global.xjb</include>
					</bindingIncludes>
					<generateDirectory>${project.basedir}/src/generated</generateDirectory>
				</configuration>
			</plugin>
	
		- 	global.xjb
			- XJB stands for XML java binding
			- XJB file is used to provide the custom parsing the details
	
	- Classes generated from XJC are annotated with JAXB annotations
	- JAXB annotations defines how the XML should be once generated from Java Stubs
	
	
## Marshaling and Unmarshaling using JAXB

	- JAXB API provides various set of Annotations
	- Unmarshaller and marshaller classes
	- Marshaling 
		- Process of converting Java Objects to XML
	- Unmarshaling 
		- Process of converting XML to Java Objects
		
##	Summary

	- JAX-WS
		- Specification from Oracle
		- Java Standard for implementing the SOAP based Web Services
		- Provides Specification and API
		
		- Specification
			- For Web Service stack implementations
			 	- Apache CXF, GlassFish, Weblogic, Jboss
		- API
			- For developers
				- Uses the Annotations
		
		- Implementation will be provided by Web Service Engines
	
	- JAXB
		- Standard from Oracle defines the  specification and API
		- JAXB is mainly used to serialize and deserialize the Java objects to xml and vice versa
		- Provides tools
			- XJC
			- SCHEMAGEN
			- RUNTIMEAPI  
		- JAXB provides the runtime API via annotations
			- @XMLRoot
			- @XMLElement
			- @XMLAttribute
		- Annotations are used the mark the Java Beans  
		- Classes with JAXB annotations can be serialize and deserialize
			
					 	
