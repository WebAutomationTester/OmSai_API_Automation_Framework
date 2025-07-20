package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacepayload(String name, String language, String address) {
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress(address);
        ap.setLanguage(language);
        ap.setName(name);
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setWebsite("http://google.com");

        List<String> ls = new ArrayList<>() ;
        ls.add("shoe park");
        ls.add("shop");
        ap.setTypes(ls);

        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        ap.setLocation(loc);
        return ap;
    }

    public String deletePlacePayload(String place_id) {
        return "{\r\n \"place_id\":\""+place_id+"\"\r\n}";
    }
}
