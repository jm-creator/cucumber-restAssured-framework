package resourses;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(){

        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress("Av Alverdi 2000 & Av Marconi");
        place.setPhone_number("+54 362 4666666");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setName("JM-CREATOR");
        place.setLanguage("Spanish-ES");
        List<String> typesList = new ArrayList<String>();
        typesList.add("green-house");
        typesList.add("shop");
        place.setTypes(typesList);
        Location location = new Location();
        location.setLat(-27.46056);
        location.setLng(-58.98389);
        return place;
    }
}
