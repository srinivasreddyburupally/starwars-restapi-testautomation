package co.starwars.api.planets.client;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PlanetsApiClient
{
    private final String baseUrl = "http://swapi.co";
    private final String planetsApiPath = "/api/planets";

    public Response getAllPlanets()
    {
        final String endpoint = baseUrl + planetsApiPath;
        final Response response = RestAssured.given().get(endpoint);
        return response;
    }

    public Response getPlanetsById(final int id)
    {
        final String endpoint = baseUrl + planetsApiPath + "/" + id;
        final Response response = RestAssured.given().get(endpoint);
        return response;
    }

}
