package co.starwars.api.planets.assertions;

import java.util.List;
import java.util.Map;

import io.restassured.response.Response;

import org.apache.log4j.Logger;
import org.testng.Assert;

import co.starwars.api.planets.tests.TestCases;

public class TestAssertions
{
    private final static Logger logger = Logger.getLogger(TestCases.class);

    public void assertResponseCode(final int actual, final int expected)
    {
        Assert.assertEquals(actual, expected);
    }

    public int assertGetAllPlanetsApiResponse(final Response response)
    {
        final int count = response.jsonPath().getJsonObject("count");
        Assert.assertTrue(count >= 0, "Count value is incorrect");
        Assert.assertNotNull(response.jsonPath().getJsonObject("next"), "Next value is null");
        Assert.assertTrue(response.jsonPath().getJsonObject("next").toString().startsWith("https://swapi.co/api/planets/?page="), "Next value doesn't start with https://swapi.co/api/planets/?page=");
        Assert.assertNull(response.jsonPath().getJsonObject("previous"), "Previous value is not null");
        Assert.assertNotNull(response.jsonPath().getJsonObject("results"), "Results value is null");
        logger.info("Validating results field");
        final List<Map<String, String>> resultsList = response.jsonPath().getList("results");
        for (Map<String, String> map : resultsList)
        {
            assertPlanetsById(map);
        }
        return count;
    }

    public void assertPlanetsById(final Map<String, String> map)
    {
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertNotNull(map.get("name"));
        Assert.assertTrue(map.containsKey("rotation_period"));
        Assert.assertNotNull(map.get("rotation_period"));
        Assert.assertTrue(map.containsKey("orbital_period"));
        Assert.assertNotNull(map.get("orbital_period"));
        Assert.assertTrue(map.containsKey("diameter"));
        Assert.assertNotNull(map.get("diameter"));
        Assert.assertTrue(map.containsKey("climate"));
        Assert.assertNotNull(map.get("climate"));
        Assert.assertTrue(map.containsKey("gravity"));
        Assert.assertNotNull(map.get("gravity"));
        Assert.assertTrue(map.containsKey("terrain"));
        Assert.assertNotNull(map.get("terrain"));
        Assert.assertTrue(map.containsKey("surface_water"));
        Assert.assertNotNull(map.get("surface_water"));
        Assert.assertTrue(map.containsKey("population"));
        Assert.assertNotNull(map.get("population"));
        Assert.assertTrue(map.containsKey("residents"));
        Assert.assertNotNull(map.get("residents"));
        Assert.assertTrue(map.containsKey("films"));
        Assert.assertNotNull(map.get("films"));
        Assert.assertTrue(map.containsKey("created"));
        Assert.assertNotNull(map.get("created"));
        Assert.assertTrue(map.containsKey("edited"));
        Assert.assertNotNull(map.get("edited"));
        Assert.assertTrue(map.containsKey("url"));
        Assert.assertNotNull(map.get("url"));
    }
}
