package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    placeValidation place = new placeValidation();

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        // write a code that will give you place id
        //Execute this code only when place id is null
        if(placeValidation.place_id==null) {
            place.add_place_payload_with("Om", "Hindi", "Mumbai");
            place.user_calls_with_http_request("AddPlaceAPI", "POST");
            place.verify_place_id_created_maps_to_using_get_place_api("Om", "GetPlaceAPI");
        }
    }
}
