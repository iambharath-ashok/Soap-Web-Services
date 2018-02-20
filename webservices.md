# WEB SERVICES 

## Java Web Service Standards
- JAX-WS - JAVA API for XML based Web Services

	JAX-WS is a Java standard for implementing SOAP based services
	  
- JAX-RS - JAVA API for RESTFul Services
	
	JAX-RS is a Java standard for implementing the RESTFul services
	
	
## SOA and Web Services

SOA - Service Oriented Architecture
- Collection of Architectural Principles and Design
- Loosely Coupled
- SOA will be maintained by W3C and OASIS
- What is Service by SOA standard

	Service is the implementation of Business Logic 
	That operates independent of state of other services

Note: We can replace the service without any modifications if the contract b/w the services remains unchanged

- SOAP : WSDL
- REST : WADL or Swagger

## Advantages of SOA

-  Platform Independent
-  Inter operability
-  Focused Development
-  Loosely Coupled - As long as Interface remains unchanged
-  Reusabilty
-  Scalabity
-  Availabilty
 
 
## XMl
- Stands for Xtensible Markup language
- Where and when -> XML is used for conf, data exchange and manipulation and presentation
- Why ->  XML is platform independent, Easy to understand
- XSDs are used to create a custom elements and attributes



## XSD
- Stands for XML Schema definition
- Its a blueprint or Grammar for XSD
- If XML follows the XSD then its called valid XML
- XSD defines the 
	
	- Elements 
	- Attributes
	- Order of occurrences
	- Number of occurrences
	- Data type of element
	- Namespaces and prefixes 
- XSD acts as the contract b/w diff XML soft applications to communicate each other
- Elements in XSD defined by W3C
 

## Namespaces
- Namespaces are used to uniquely identify the elements and attributes in the XML schema document
- While creating the XML Schema namespaces plays imp role
- targetNamespace is used to uniquely identify the elements
- targetNamespace is a attribute will be assigned with unique values
- EX: 
	<xmlns:amz="http://www.amazon.com">
	<xmlns:ebay="http://www.ebay.com"/>
- xmlns - is a targetNamespace
- while creating a XML document using XSD, we should use the namespace
- EX: order.xml
	<order xmlns:amz="http://www.amazon.com">
- Namespace is also used to use the elements from multiple XSDs
- Namespace helps to identify the from which XSDs those elements are from


## elementFormDefault
- Its a attribute in Schema Element
- Its do nothing with XSD doc
- Its used in XML doc that follows the XSD 
- Possible Values - qualified and unqualified
- qualified -> forces the XML doc elements to have namespace attached to it
- unqualified -> doesnot forces the XML doc elements to have namespace attached to it


## WebServices
- Web Services are like English to S/w industry
- Web Services enables S/w apps to communicate with each other across cross platform and 
developed with diff languages


## Pros and Cons of SOAP Web Services
##### Pros
- Platform Independent
	
		- HTTP -> Transport Independent
		- XML -> Data Independent 

- Application Tailoring/Customization
- Will result in new Revenue and profit Channels
- HTTP is friendly with firewalls
- Usually port 80 is not blocked in organizations 
- which will make very easy communicate within organizations or outside
- HTTP communicate with default port 80

##### Cons
- Ambiguous Web Services Standards

		- Due to people from diff tech, org, persective...

- Performance impact due to serialization and deserialization of soap messages
	
		- Takes high bandwidth compared to RESTFul services


## When to use SOAP Web Services
	- Formal Contract needs to be established to describe the service offered
	- Formal Contract via -> WSDL
	- WSDL provides details about message formats, excepted input and output etc 
	- When Non Functional requirements like
		- Transaction 
		- Security  
	- Reliable Asynchronous Processing -> comes along with soap based service 
	- Asynchronous Processing can be configured via Apache CXF engine
##### Summary 
	- When formal contract is required b/w service provider and service consumer
	- When non-functional requirements are required like security and transaction management



## SOAP

- SOAP - Simple Object Access Protocol
- SOAP is a specification from W3C

	 	- Specification is usually set of Rules
	 	- In SOAP, specification usually comes in form of XML
	 	- Elements in SOAP req/res(messages) are defined by W3C
	 	- In SOAP, XML elements are used to send the req/res
	 	- SOAP messages have Soap Envelop, Header, Body, Fault
	 		- SOAP Envelop
	 			- Its a root element
	 			- Encapsulates the Soap header and body
	 		- Soap Header
	 			- Contains meta data
	 			- Contains authentication info
	 		- Soap Body
	 			- Its the body that contain the actual payload
	 		- Soap Fault
	 			- Soap Fault is a child element of Soap body
	 			- Its used to send the error details
	 			- It contains the Soap fault Code
	 			- And reason of error			  
	 	
- Current version of SOAP is 1.2



## WSDL

- WSDL -> Web Service Definition Language
- WSDL is a contract b/w Service Provider and Service Consumer
- WSDL provides two things
	
	- What this web service Provides
	- How to consume the services
		- What request consumer needs to send 
		- What response consumer gets back

- Root element of WSDL is wsdl definitions
- There are two parts in WSDL
	
	- Abstract Portion -> What this Web Service provides
	- Physical Portion -> How to consume the service

- Abstract Portion
	
	- Types
	- Messages
	- Operation
	- Porttype 

- Physical Portion

	- Binding
	- Service
## 	Abstract Portion
##### Types

	- Types defines the data types of request and response
	- Types defines request and response elements 
	- It defines the request and response  formats
	- Types can use the XSDs to define the formats
	- Types contain entire schema for both request and reponse
	
##### Messages

	- Messages are analogous to return type and params in java
	- Messages makes use of WSDL Types elements		

###### Operations

	- Operations are analogous to methods in java
	- Operations makes use of messages to define the input params and return type

##### Port type

	- port type is container of all the operations in the wsdl together

## 	Physical Portion

##### Binding

	- Defines how to consume the service
	- It provides the link b/w physical(binding) and abstract(port) section of WSDL
	- It provide details about whats the WSDL documented style
	
	
##### Services
	
	- Services provide the URL to access the various service in WSDL
	- Defines how can consumer can consume the service

### Summary of SOAP

	- SOAP -> Simple Object Access Protocol
	- SOAP is W3C specification -> 1.2	
	- SOAP is XML based and all the elements in SOAP are W3C Specification
	- SOAP uses WSDL as the formal contract b/w the Service Provider and Service Consumer
	- Elements of SOAP based services are Soap Envelop, Header, Body, Fault 
	- SOAP uses HTTP and XML 
	- SOAP takes more bandwidth compared to REST
	
	- WSDL defines 
		- What -> services are provided by Service Provider
		- How -> to consume the services
	- WSDL has two parts 
		- Abstract Portion
			- Defines -> what are all the services provided
		- Physical Portion
			- Define -> how to use and access the Services
	
## SOAP Web Services Design and Implementation

	- Two Ways
		- Top Down -> WSDL First -> Contract First
		- Bottom Up -> Code first

##### WSDL First Approach

	- WSDL file will be created first
	- Steps 
		i. Create the WSDl file
		ii. Create the Java  Stubs using the tools like WSDL2JAVA
		iii. Implement the Web Services Endpoint
	- Advantages
		- Faster Integration b/w Service provider and consumer
		- Better Interoperability
		- Sign off the Client
		
##### Code First
	
	- Java code will be written first
	- Java classes will be annotated with JAX-WS specification annotations
	- WSDL will be generated from annotated classes using tools like JAVA2WSDL provided by Apache CXF				
	- Advantages 
		- Will fit for legacy apps - Legacy App Integration
		- Will be used if business logic is already available 
		- Just need to expose the business logic with service

### Which One to Use

	- WSDL First best and must use as much as possible if Service is being developed from scratch
	- Code First suits only if business logic is existed and for Legacy Apps

	
	


		 	 	  


			
			


		
	  	
	
		



	
	
 



 





















 
 		 


		
		
