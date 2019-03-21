##	Java – Read XML DOM Parser

-	DOM Parser will read and parse the whole XML Document
-	DOM Parser will create a tree structure of XML Document in Memory
-	The parser traverses the input XML file and creates DOM objects corresponding to the nodes in XML file
-	These DOM objects are linked together in a tree like structure
-	We can traverse, update, delete, add elements to Document at runtime using DOM


###	Parsing with DOM Parser

	Code Snippet:
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document w3cDocument = builder.parse(new File( file ));

### DOM Vs SAX Parser in Java


-	DOM XML Parser in Java

	-	DOM parser is a tree-based API
	-	A tree-based API is centered around a tree structure 
	-	And therefore provides interfaces on components of a tree such as:
		-	Document interface,
		-	Node interface
		-	NodeList interface
		-	Element interface
		-	Attr interface and so on
		
-	SAX parser is a event-based API
-	Usually an event-based API provides interfaces on handlers
-	There are four handler interfaces, ContentHandler interface, DTDHandler interface, EntityResolver interface and ErrorHandler interface

### Difference between DOM and SAX XML Parser in Java
	
-	DOM (Document Object Model)

	-	Parses entire document
	-	Represents result as a tree
	-	Lets you search tree
	-	Lets you modify tree
	-	Good for reading data/configuration files

-	SAX

	-	Parses until you tell it to stop
	-	Fires event handlers for each:
	-	Start tag
	-	Tag body
	-	End tag
	-	Low level APIs
	-	Good for very large documents, especially if you only care about very small portions of the document
	
- Can SAX and DOM parsers be used at the same time?
	
	- yes
	
### Java String to XML – Parse String to XML DOM Example

	Code Snippet:
	
		String employee = "<employees>" +
                "   <employee id=\"101\">" +
                "        <name>Lokesh Gupta</name>" +
                "       <title>Author</title>" +
                "   </employee>" +
                "   <employee id=\"102\">" +
                "        <name>Brian Lara</name>" +
                "       <title>Cricketer</title>" +
                "   </employee>" +
                "</employees>";
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
		Document parse = documentBuilder.parse(new InputSource(new StringReader(employee)));
		
		System.out.println(parse.getDocumentElement().getAttributes());
		
###  Convert XML file to XML Document

		
	Code Snippet:
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
		Document parse = documentBuilder.parse(new File(employee.xml));
	

### Java XML to String – Write XML Object to File Example

-	Print XML to Console or Log File

	Code Snippet:
	
		private static void writeXmlDocumentToXmlFile(Document xmlDocument)
		{
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer;
			try {
				transformer = tf.newTransformer();
				 
				// Uncomment if you do not require XML declaration
				// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				 
				//A character stream that collects its output in a string buffer,
				//which can then be used to construct a string.
				StringWriter writer = new StringWriter();
		 
				//transform document to string
				transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
		 
				String xmlString = writer.getBuffer().toString();  
				System.out.println(xmlString);                      //Print to console or logs
			}
			catch (TransformerException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		
-	 Write XML to File
		
		Code Snippet:
		
			private static void writeXmlDocumentToXmlFile(Document xmlDocument, String fileName)
			{
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer;
				try {
					transformer = tf.newTransformer();
					 
					//Uncomment if you do not require XML declaration
					//transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
					 
					//Write XML to file
					FileOutputStream outStream = new FileOutputStream(new File(fileName));
			 
					transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));
				}
				catch (TransformerException e)
				{
					e.printStackTrace();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}


## XPATH

### What is XPATH

-	XPath is a syntax used to describe parts of an XML document
-	XPath uses language syntax much similar to what we already know
	-	The syntax is a mix of basic programming language expressions (wild cards such as $x*6) 
	-	And Unix-like path expressions (such as /inventory/author)
	
	
###  XPath Data Model

-	XPath views an XML document as a tree of nodes
-	This tree is very similar to a Document Object Model i.e. DOM tree, so if you’re familiar with the DOM, you will easily get some understanding of how to build basic XPath expressions	

	-	There are seven kinds of nodes in the XPath data model:

		-	The root node (Only one per document)
		-	Element nodes
		-	Attribute nodes
		-	Text nodes
		-	Comment nodes
		-	Processing instruction nodes
		-	Namespace nodes



### Java xpath example – Evaluate xpath on xml file	


-	Java evaluate xpath on xml file
	
	Code Snippet:
	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xml = db.parse(xmlFile);
 
        //Get XPath
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
         
        //Get first match
        String name = (String) xpath.evaluate("/employees/employee/firstName", xml, XPathConstants.STRING);
         
        System.out.println(name);   //Lokesh
         
        //Get all matches
        NodeList nodes = (NodeList) xpath.evaluate("/employees/employee/@id", xml, XPathConstants.NODESET);
         
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());   //1 2
        }



-	XPath example – Evaluate xpath on xml string

	
	Code Snippet:
	
		String xml = "<employees>"
                    + "<employee id=\"1\">"
                        + "<firstName>Lokesh</firstName>"
                        + "<lastName>Gupta</lastName>"
                        + "<department><id>101</id><name>IT</name></department>"
                    + "</employee>"
                   + "</employees>";
         
        InputSource inputXML = new InputSource( new StringReader( xml ) );
         
        XPath xPath = XPathFactory.newInstance().newXPath();
         
        String result = xPath.evaluate("/employees/employee/firstName", inputXML);
 
        System.out.println(result);
		
### Java XPath expression examples

-	Java xpath expression examples to extract information from an XML document by evaluating those expressions
-	Fetch information for matching attribute values, matching fields values, contains() expressions etc
-	XPath Query Examples:

-	Get all employee names

		/employees/employee/firstName/text()
		
-	Get all department names

		/employees/employee/department/name/text()
		
-	Get all employees in IT
		
		/employees/employee[department/name='IT']/firstName/text()
		
-	Get employee by id
		
		/employees/employee[@id=4]/firstName/text()

-	Get employees whose Id is greater than 6
		
		/employees/employee[@id>4]/firstName/text()

		
-	Get department for David

		/employees/employee[firstName = 'David']/department/name/text()

-	Get all employee ids

		/employees/employee/@id
		
-	Get all employee ids in HR department

		/employees/employee[department/name='HR']/@id
		
-	Get employee id of ‘Alex’

		/employees/employee[firstName='Alex']/@id
		
-	Get employee ids greater than 5

		/employees/employee/@id[. > 5]
		
-	Get employee whose id contains ‘1’

		/employees/employee[contains(@id,'1')]/firstName/text()
		
-			
		
	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<employees>
		<employee id="1">
			<firstName>Lokesh</firstName>
			<lastName>Gupta</lastName>
			<department>
				<id>101</id>
				<name>IT</name>
			</department>
		</employee>
		<employee id="2">
			<firstName>Brian</firstName>
			<lastName>Schultz</lastName>
			<department>
				<id>102</id>
				<name>HR</name>
			</department>
		</employee>
		<employee id="3">
			<firstName>Alex</firstName>
			<lastName>Kolenchisky</lastName>
			<department>
				<id>103</id>
				<name>FINANCE</name>
			</department>
		</employee>
		<employee id="4">
			<firstName>Amit</firstName>
			<lastName>Jain</lastName>
			<department>
				<id>104</id>
				<name>HR</name>
			</department>
		</employee>
		<employee id="5">
			<firstName>David</firstName>
			<lastName>Beckham</lastName>
			<department>
				<id>105</id>
				<name>DEVOPS</name>
			</department>
		</employee>
		<employee id="6">
			<firstName>Virat</firstName>
			<lastName>Kohli</lastName>
			<department>
				<id>106</id>
				<name>DEVOPS</name>
			</department>
		</employee>
		<employee id="7">
			<firstName>John</firstName>
			<lastName>Wick</lastName>
			<department>
				<id>107</id>
				<name>IT</name>
			</department>
		</employee>
		<employee id="8">
			<firstName>Mike</firstName>
			<lastName>Anderson</lastName>
			<department>
				<id>108</id>
				<name>HR</name>
			</department>
		</employee>
		<employee id="9">
			<firstName>Bob</firstName>
			<lastName>Sponge</lastName>
			<department>
				<id>109</id>
				<name>FINANCE</name>
			</department>
		</employee>
		<employee id="10">
			<firstName>Gary</firstName>
			<lastName>Kasporov</lastName>
			<department>
				<id>110</id>
				<name>IT</name>
			</department>
		</employee>
	</employees>

###	Java XPath – Check if node or attribute exists?


-	To verify if node or tag exists in XML content
-	We can execute an xpath expression against DOM document for that XML and count the matching nodes
-	matching nodes > zero – XML tag / attribute exists
-	matching nodes <= zero – XML tag / attribute does not exist







		
		