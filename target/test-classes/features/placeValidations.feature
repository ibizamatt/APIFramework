Feature: Validate Place API's 
@AddPlace @Regression 
Scenario Outline: Verify Place Added using addPlaceAPI 

	Given AddPlace payload with "<name>" "<language>" "<address>" 
	When User calls "addPlaceAPI" with "post" http request 
	Then API call sucess with status code 200 
	And "status" in response body is "OK" 
	And verify place_id maps to "<name>" using "getPlaceAPI" 
	
	Examples: 
		|name 		|language 	|address			|
		|MattsHouse	|English	|World trade center|
		|JimsHouse	|French		|French trade center|
		|KellysHouse|Spanish	|Madrid trade center|
		|PamsHouse	|Latin		|Roman trade center|
		|ValsHouse	|Fijian		|Suva trade center|
		
		@DeletePlace @Regression 
		Scenario: Verify deletePlaceAPI functionality 
			Given DeletePlace payload 
			When User calls "deletePlaceAPI" with "post" http request 
			Then API call sucess with status code 200 
			And "status" in response body is "OK" 
			
			
	