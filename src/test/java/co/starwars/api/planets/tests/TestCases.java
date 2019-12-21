package co.starwars.api.planets.tests;

import io.restassured.response.Response;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.starwars.api.planets.assertions.TestAssertions;
import co.starwars.api.planets.client.PlanetsApiClient;

public class TestCases
{
    private final static Logger logger = Logger.getLogger(TestCases.class);
    private PlanetsApiClient planetsApiClient;
    private TestAssertions testAssertions;
    private int count;

    @BeforeTest
    public void setUp()
    {
        DOMConfigurator.configure("log4j.xml");
        planetsApiClient = new PlanetsApiClient();
        testAssertions = new TestAssertions();
    }

    @Test
    public void getAllPlanetsApiTest()
    {
        logger.info("Validating get all Planets api");
        final Response response = planetsApiClient.getAllPlanets();
        testAssertions.assertResponseCode(response.getStatusCode(), 200);
        count = testAssertions.assertGetAllPlanetsApiResponse(response);
        logger.info("Get all Planets api validation was successful and planets\n" + response.getBody().asString());
    }

    @Test(dependsOnMethods = {"getAllPlanetsApiTest"})
    public void getPlanetsByIdApiTest()
    {
        logger.info("Validating get Planets by id api");
        for (int i = 1; i <= count; i++)
        {
            final Response response = planetsApiClient.getPlanetsById(i);
            testAssertions.assertResponseCode(response.getStatusCode(), 200);
            testAssertions.assertPlanetsById(response.jsonPath().get());
            logger.info("Validated Planet " + response.jsonPath().get());
        }
        logger.info("Get Planets by id api validation was successful and count=" + count);
    }
}
