package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	
	public void beforeScenario() throws IOException
	{
		StepDefinition m=new StepDefinition();
		
		if(StepDefinition.place_id==null) 
		{
			
		m.add_place_payload_with("Roberts", "Italian", "Beach");
		m.user_calls_with_http_request("addPlaceAPI", "POST");
		m.verify_place_id_maps_to_using("Roberts", "getPlaceAPI");
		
		}
	}

}
