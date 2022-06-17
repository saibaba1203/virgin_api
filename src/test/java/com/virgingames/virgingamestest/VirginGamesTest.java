package com.virgingames.virgingamestest;

import com.virgingames.testbase.TestBase;
import com.virgingames.virgingamesinfo.VirginGamesSteps;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


@RunWith(SerenityRunner.class)
public class VirginGamesTest extends TestBase {

    static String defaultGameFrequency;
    static String timeStamp;
    static String startTime;

    @Steps
    VirginGamesSteps virginGamesSteps;


    // This test will verify defaultGameFrequency should always be '300000'
    @Title("Verifying That the value of defaultGameFrequency=300000")
    @Test
    public void test001() {
        ValidatableResponse response = virginGamesSteps.getAllGamesInfo()
                .statusCode(200);
        defaultGameFrequency = response.extract().body().path("bingoLobbyInfoResource.streams.findAll{it.defaultGameFrequency='300000'}.defaultGameFrequency").toString();
        Assert.assertTrue(defaultGameFrequency.contains("300000"));
        Assert.assertFalse(defaultGameFrequency.contains("null"));
        System.out.println("The defaultGameFrequency values are : " + defaultGameFrequency);
    }

    // This test will verify that  'startTime' should always be future timestamp
    @Title("Verifying that 'startTime' should always be future timestamp")
    @Test
    public void test002() {
        ValidatableResponse response = virginGamesSteps.getAllGamesInfo()
                .statusCode(200);
        timeStamp = response.extract().path("timestamp").toString();
        startTime = response.extract().path("bingoLobbyInfoResource.streams.startTime").toString();
        assertThat(startTime, greaterThan(timeStamp));
        System.out.println("The timestamp is : " + timeStamp);
        System.out.println("The startTimes are :" + startTime);
    }

}
