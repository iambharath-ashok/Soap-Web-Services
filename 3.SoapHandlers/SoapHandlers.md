# JAX-WS Handlers

## JAX-WS Handlers
-  Non-Functional requirements applied across the service producer, consumer and endpoints
-  Like Cross Cutting concerns
-  JAX-WS handlers are classes that implements the interfaces in the JAX-WS API
-  Handlers will be executed for both incoming and outgoing requests
-  Apache CXF will call these handlers once request comes from client and request goes out from the service provider
	-  Logic inside the handlers will be executed by webservice stack engine like CXF
-  Like Servlet Filters - Except that handlers can be applied both server and client side
-  Use cases
	-  	WS Handlers can be used to handle the custom authentication
	-	WS Cache
	-	WS Version support on soap
	
## Two Types of Handlers
-  SOAPHandlers
	- 	Have access to entire message like protocol info(HTTP Headers), SOAP headers, SOAP Body
	- 	Implemented by implementing interface SOAPHandler<SOAPMessgeContext>
	- 	SOAPMessageContext will wrap the whole SOAP Message
	- 	life cycle methods of SOAPHandler are
		- 	handleMessage
		- 	handleFault
		- 	getHeaders
		- 	close
	- 	Life cycle methods are invoked on incoming and outgoing request and response
	- 	handleFault will be executed only when exception occurs
	- 	close will be executed at the end of response 
		-  any cleaning code can put in the close method
-  LogicalHandlers
	- 	LogicalHandler has access only to Message Body or SoapMessage body
	- 	LogicalHandlers are implemented by implementing LogicalHandler<LogicalMessageContext>
	- 	Life Cycle methods
		- 	handleMessage
		- 	handleFault
		- 	close
		- 	It does have getHeader() as it's don't have access to soap headers
	- 	LogicalHandlers only deal with Payload

## Steps for Implementing SOAP Handlers
	-  Design the Handler Chain
		-  One handler can call the another one like Servlet filter
	-  Create the Handler
	-  Configure the Handler
	-  Run and Test
	
	
## Implementation of Soap Handlers

	Code Snippet:
	
		public class PaymentProcessorImpl implements PaymentProcessor {

			public PaymentProcessorResponse processPayment(PaymentProcessorRequest paymentProcessorRequest) {
				PaymentProcessorResponse paymentProcessorResponse = new PaymentProcessorResponse();
				// Business Logic or a call to a Business Logic Class Goes Here.
				paymentProcessorResponse.setResult(true);
				return paymentProcessorResponse;
			}

		}
		
		public class SiteHandler implements SOAPHandler<SOAPMessageContext> {

			@Override
			public boolean handleMessage(SOAPMessageContext context) {
				System.out.println("handleMessage");
				
				// This property will set by CXF at runtime
				// Returns True if the soap message is response
				boolean isResponse = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
				
				if(!isResponse) {
					
					SOAPMessage message = context.getMessage();
					try {
						SOAPHeader soapHeader = message.getSOAPHeader();
						Iterator childElements = soapHeader.getChildElements();
						for(;childElements.hasNext();) {
							Node node = (Node)childElements.next();
							String name = node.getLocalName();
							if(name!=null && name.equals("SiteName")) {
								System.out.println("Site Name: "+node.getValue());
							}
						}
					} catch (SOAPException e) {
						e.printStackTrace();
					}
					
				} else {
					System.out.println("Handling the Response before sending it");		
				}
				
				
				
				return false;
			}

			@Override
			public boolean handleFault(SOAPMessageContext context) {
				System.out.println("handleFault");
				return false;
			}

			@Override
			public void close(MessageContext context) {
				System.out.println("close");
			}

			@Override
			public Set<QName> getHeaders() {
				System.out.println("getHeaders");
				return null;
			}

		}
		
		
		@Configuration
		public class PaymentProcessorConfig {

			@Autowired
			private Bus bus;
			@Bean
			public Endpoint publishPaymentProcessorEndpoint() {
				EndpointImpl endpoint = new EndpointImpl(bus, new PaymentProcessorImpl());
				endpoint.publish("/processPayment");
				
				SOAPBinding binding = (SOAPBinding)endpoint.getBinding();
				binding.setHandlerChain(Arrays.asList(new SiteHandler()));
				
				return endpoint;
			}
		}


		
## Handler Flow
- 	Incoming Request
	- 	getHeaders() will be executed first
	- 	handleMessage() will gets executed
	- 	Webservices endpoint will gets executed (business logic)
	- 	if any error or exception at endpoint then handleFault() will gets executed
- 	Outgoing Response
	- 	getHeaders()
	- 	handleMessage()
	- 	close() -cleaning up the resources/wsdl/filename
	
-	getHeaders method in JAX-WS handler
- 	mustUnderstand is a attribute on the soap header that will force the service provider that header should be processed
- 	mustUnderstand can be either 0 or 1
	-  1 ---> must be processed by the webservice provider
		- code at server side must handle the header
	-  0 ---> not mandatory
-  If header is not processed by web-service provider then web-service engine will throw the error