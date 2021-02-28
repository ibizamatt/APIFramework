package resources;

public class apiResourcePaths {

	String addAPI = "/maps/api/place/add/json";
	String getAPI = "/maps/api/place/get/json";
	String deleteAPI = "/maps/api/place/delete/json";

	String path;

	public String pathSelect(String apiPath) 
	{

		if (apiPath == "AddPlaceAPI") 
		{

			path = addAPI;

		}

		else if (apiPath == "DeletePlaceAPI")

		{
			path = deleteAPI;
		} 
		
		else if (apiPath == "GetPlaceAPI") 
		{
			path = getAPI;
		}
		return path;

	}
}