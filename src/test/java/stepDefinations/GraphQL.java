package stepDefinations;

import static io.restassured.RestAssured.given;

public class GraphQL {

    public static void main(String[] args) {

        // Query
        String response = given().log().all().header("Content-Type", "application/json")
                .body("{\"query\":\"query ($episode: String!) {\\n  character(characterId: 8) {\\n    name\\n  }\\n  location(locationId: 22939) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: 1) {\\n    name\\n  }\\n  characters(filters: {name: \\\"Rahul\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters: {episode: $episode}) {\\n    result {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"episode\":\"hulu\"}}")
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().log().all().extract().response().asString();

        System.out.println(response);

        // Mutations
        String Mutationsresponse = given().log().all().header("Content-Type", "application/json")
                .body("{\"query\":\"mutation ($locationName: String!, $characterName: String!) {\\n  createLocation(location: {name: $locationName, type: \\\"Ram\\\", dimension: \\\"4555.6\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $characterName, type: \\\"Macho\\\", status: \\\"alive\\\", species: \\\"fantacy\\\", gender: \\\"male\\\", image: \\\"rathod\\\", originId: 22933, locationId: 22933}) {\\n    id\\n  }\\n  createEpisode(episode: {name: \\\"Om\\\", air_date: \\\"07/12/25\\\", episode: \\\"first\\\"}) {\\n    id\\n  }\\n  deleteLocations(locationIds: [22938, 22936]) {\\n    locationsDeleted\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"Shirdi\",\"characterName\":\"God\"}}")
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().log().all().extract().response().asString();

        System.out.println("Mutation Response: "+Mutationsresponse);
    }
}
