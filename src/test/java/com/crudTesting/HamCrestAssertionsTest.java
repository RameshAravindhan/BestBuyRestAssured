package com.crudTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HamCrestAssertionsTest {

	static String JsonResponse;

	public static void print(String val) {
		System.out.println(val);
	}

	@BeforeAll
	public static void getResponse() {
		baseURI = "http://localhost";
		port = 3030;
		basePath = "/stores";
	}

	/*
	 * @BeforeEach public void before() { System.out.
	 * println("BEFORE METHOD---------------------------------------------------------------"
	 * ); System.out.println(""); }
	 * 
	 * @AfterEach public void after() { System.out.println(""); System.out.
	 * println("AFTER METHOD---------------------------------------------------------------"
	 * ); System.out.println(""); }
	 */

	@Test
	public void simpleAssertTest() {
		int total = given().when().get().then().extract().path("total");
		assertEquals(1561, total);
	}

	@Test
	public void simpleAssertHamCrestTest() {
		given().when().get().then().body("total", equalTo(1561));
	}

	@Test
	public void equalsIgnoreCaseTest() {
		given().when().get().then().body("data[1].type", equalToIgnoringCase("bigbox"));

	}

	@Test
	public void hasItemTest() {
		given().when().get().then().body("data.name", hasItem("Inver Grove Heights"));
	}

	@Test
	public void multiAssertTest() {
		given().when().get().then().body("data.findAll{it.type=='BigBox'}", hasItems(hasEntry("name", "Minnetonka")))
				.body("data[0]", hasKey("name"));
	}

	@Test
	public void multiSizeAssertTest() {

		given().when().get().then().body("data.size()", equalTo(10));
		given().when().get().then().body("data.size()", greaterThan(5));
	}

	@Test
	public void softAssertTest() {
		given().when().get().then().body("data.size()", greaterThan(5), "data.size()", equalTo(10), "data.size()",
				greaterThan(4));
	}
}
