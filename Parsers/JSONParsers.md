# Xml and JSON Parsing


## XML Parsers

-	JAXB
-	SAX 
-	DOM

##	JSON Parsers

###	Moxy API
	
	- 	Moxy implements JAXB API	
	-	Using Moxy we can parse Java Objects to JSON String and Versa
	
	
###	Jackson API
	-	Jackson Api can be used to parse Json String to Java Object and Vice Versa
	-	ObjectMapper 
		-	readValue()
		-	writeValueAsString()
	
	-	Custom Serializer
	-	Serializing and De-serializing from and to Map
	-	Ignore Unknown JSON Fields
		-	DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
	-	Fail on Null JSON Values for Primitive Types
		-	DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES
		
	-	objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);	
		
	-	Custom Deserializer and Custom Serializer

		-	Sometimes we might want to read a JSON string into a Java object in a way that is different from how the Jackson ObjectMapper does this by default
		-	We can add a custom deserializer to the ObjectMapper which can perform the deserialization as we want it done
		
	- 	Jackson JSON Tree Model

		-	Jackson has a built-in tree model which can be used to represent a JSON object
		-	Jackson's tree model is useful if we don't know how the JSON we will receive looks, 
		-	The Jackson Tree Model is also useful if you need to manipulate the JSON before using or forwarding it
		-	The Jackson tree model is represented by the JsonNode class
		-	We can use the Jackson ObjectMapper to parse JSON into a JsonNode tree model
			-	 Just like you would have done with your own class
	
			Code Snippet:
			
				String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

				ObjectMapper objectMapper = new ObjectMapper();

				try {

					JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);

				} catch (IOException e) {
					e.printStackTrace();
				}
		
		-	The ObjectMapper class also has a special readTree() method which always returns a JsonNode
			-	Here is an example of parsing JSON into a JsonNode with the ObjectMapper readTree() method:
			
			Code Snippet:

				String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

				ObjectMapper objectMapper = new ObjectMapper();

				try {

					JsonNode jsonNode = objectMapper.readTree(carJson);

				} catch (IOException e) {
					e.printStackTrace();
				}
		
	-	The Jackson JsonNode Class
		-	The JsonNode class lets us to navigate the JSON as a Java object in a quite flexible and dynamic way
		
			Code Snippet:
			
				String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
					"  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
					"  \"nestedObject\" : { \"field\" : \"value\" } }";

					ObjectMapper objectMapper = new ObjectMapper();


					try {

						JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);

						JsonNode brandNode = jsonNode.get("brand");
						String brand = brandNode.asText();
						System.out.println("brand = " + brand);

						JsonNode doorsNode = jsonNode.get("doors");
						int doors = doorsNode.asInt();
						System.out.println("doors = " + doors);

						JsonNode array = jsonNode.get("owners");
						JsonNode jsonNode = array.get(0);
						String john = jsonNode.asText();
						System.out.println("john  = " + john);

						JsonNode child = jsonNode.get("nestedObject");
						JsonNode childField = child.get("field");
						String field = childField.asText();
						System.out.println("field = " + field);

					} catch (IOException e) {
						e.printStackTrace();
					}
			
		
	-	Jackson Annotations

		Read + Write Annotations
			@JsonIgnore
			@JsonIgnoreProperties
			@JsonIgnoreType
			@JsonAutoDetect
			@JsonInclude
		
### GSON API

-	GSON is Google's JSON parser and generator for Java
-	Google developed GSON for internal use but open sourced it later
-	GSON it reasonably easy to use, but in my opinion not as elegant as Jackson


-	Dependency

	<dependency>
    	<groupId>com.google.code.gson</groupId>
    	<artifactId>gson</artifactId>
    	<version>2.6.2</version>
	</dependency>

-	Gson has three types of API:

	Data binding API
	Tree model API
	Streaming API

-	Create Gson Object

	Gson gson = new Gson();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	Gson gson = new GsonBuilder().setPrettyPrinting().setExclusionStrategies(new MyCustomExclusionStrategy()).create();
	Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

-	toJson()
		1. Map
		
			Map<Integer, String> colours = new HashMap<>();
			colours.put(1, "blue");
			colours.put(2, "yellow");
			colours.put(3, "green");
			
			Gson gson = new Gson();
			
			String output = gson.toJson(colours);
		
		2. Custom Class
			try (PrintStream prs = new PrintStream(System.out, true, 
					"UTF8")) {
				
				Gson gson = new GsonBuilder()
						.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
						.create();
				
				User user = new User("Peter", "Flemming");
				gson.toJson(user, prs);
			}
		
		
-	fromJson()

		Gson gson = new Gson();
        User user = gson.fromJson(json_string, User.class);
	



### Org.jsonNode

		<dependency>
			<groupId>org.json</groupId>
			<artifactId>json</artifactId>
			<version>20180130</version>
		</dependency>

-	JSONObject

	-	Creating JSON Directly from JSONObject

			JSONObject jo = new JSONObject();
			jo.put("name", "jon doe");
			jo.put("age", "22");
			jo.put("city", "chicago");
			jo.toString();
			
			{"city":"chicago","name":"jon doe","age":"22"}

	-	 Creating JSON from Map
			
			Map<String, String> map = new HashMap<>();
			map.put("name", "jon doe");
			map.put("age", "22");
			map.put("city", "chicago");
			JSONObject jo = new JSONObject(map);
	
	-	Creating JSONObject from JSON String
	
			JSONObject jo = new JSONObject("{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}");
			
	- 	Serialize Java Object to JSON
			
			DemoBean demo = new DemoBean();
			demo.setId(1);
			demo.setName("lorem ipsum");
			demo.setActive(true);
				
			{"name":"lorem ipsum","active":true,"id":1}	

-	JSONArray

		JSONArray ja = new JSONArray();
		ja.put(Boolean.TRUE);
		ja.put("lorem ipsum");
		 
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("age", "22");
		jo.put("city", "chicago");
		 
		ja.put(jo);
		
		[
			true,
			"lorem ipsum",
			{
				"city": "chicago",
				"name": "jon doe",
				"age": "22"
			}
		]

	-	Creating JSONArray Directly from JSON String
	
		JSONArray ja = new JSONArray("[true, \"lorem ipsum\", 215]");
		
	-	Creating JSONArray Directly from a Collection or an Array
	
		List<String> list = new ArrayList<>();
		list.add("California");
		list.add("Texas");
		list.add("Hawaii");
		list.add("Alaska");
		 
		JSONArray ja = new JSONArray(list);
		
		["California","Texas","Hawaii","Alaska"]	










		