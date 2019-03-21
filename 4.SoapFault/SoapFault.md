# SOAP FAULT

- 	Webservice apart from handling the request and responses should handle the errors when something goes wrong
- 	SOAP Standard provides a element to represent the error and sent them in response body to client
- 	We dont needs to create the fault code --> we can do that
	- 	We just needs to throw the exception 
	- 	Webservice stack or engines will convert execption into fault element as the response body
	- 	At client side also we needs to handle the fault code and convert them back to exception
- 	When an exception occurs then webservice engine will send the fault in the soap message body
- 	SOAP:Fault has a child element of SOAP:BODY

- 	SOAP:Fault has 4 child elements
	- 	faultCode
		-	faultCode can 4 Values 
		
	- 	faultString
		-	Message that explains the error
		
	- 	faultActor
		-	Rarely used
	
	- 	detail
		-	provides more info on the exception or on error
	
- 	FaultCodes
	- 	SOAP-ENV:VersionMismatch
		- 	Client Soap envelop name-space is not match with what server is expecting
 
	- 	SOAP-ENV:MustUnderstand
		- 	mustUnderstand is automatically used when mustUnderstand attribute is set on the header
		- 	if header is not processed to mustUnderstand set to 1 --> then fault code will be 
		- 	EX
			- 	If mustUnderstand attribute is set to 1 on the soap header
			- 	And soap header having security element as child 
				- 	WS security is the child element of soap header element
			- 	And sever is not able to understand the WS security element then it will send the response with mustUnderstand
				fault code	
	
	- 	SOAP-ENV:Client
		- 	Client fault code is sent if client is sending the message thats not valid or not well-formed
	
	- 	SOAP-ENV:Server 
		- 	If any error occurs at server side
		
		
## 	Examples of Soap Fault


   <soapenv:Envelope xmlns:soap="http://soap.ws.bharath.com/" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header/>
	   <soapenv:Body>
			<soap:Fault>
				<faultcode>soap:server</faultcode>
				<faultstring>Invalid Card Number</faultctring>
			</soap:Fault>
	   </soapenv:Body>
   </soapenv:Envelope>

		
		