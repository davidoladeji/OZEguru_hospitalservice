package com.davidoladeji.ozeguru.controller;

import com.davidoladeji.ozeguru.model.Staff;
import com.davidoladeji.ozeguru.security.AuthenticationProvider;
import com.davidoladeji.ozeguru.service.StaffService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
public class StafferControllerTest {


    @MockBean
    private StaffService staffService;

    @MockBean
    AuthenticationProvider provider;

    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String timenow = sdf.format(timestamp);

    Staff mockStaff = new Staff(2L, "David Max", "maxim", "password", "tyuio", timenow);

    private String token;
    private String invalidtoken;

    @Before
    public void setup() throws JsonProcessingException {
        String tokenRequestBody = "{\"username\" : \"daveola\", \"password\" : \"mydream\"}";
        RestAssured.port = 8080;
        token = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(tokenRequestBody)
                .when().post("/token")
                .andReturn().jsonPath().get("token");
        invalidtoken = "idhvhuff";
    }

    @Test
    public void updateStaffwithAuth() throws Exception {
        given()
                .header("Authorization", "Bearer "+token)
                .contentType(ContentType.JSON)
                .body(mockStaff)
                .when()
                .put("/api/v1/staff/update/2")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void updateStaffwithWrongAuth() throws Exception {
        given()
                .header("Authorization", "Bearer "+invalidtoken)
                .contentType(ContentType.JSON)
                .body(mockStaff)
                .when()
                .put("/api/v1/staff/update/2")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

}