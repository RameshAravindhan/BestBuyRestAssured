package com.crudTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestButExtractTest {

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

	@BeforeEach
	public void before() {
		System.out.println("BEFORE METHOD---------------------------------------------------------------");
		System.out.println("");
	}

	@AfterEach
	public void after() {
		System.out.println("");
		System.out.println("AFTER METHOD---------------------------------------------------------------");
		System.out.println("");
	}

	@Test
	public void extractDataSimple() {
		System.out.println("extractDataSimple");
		int statusCode = given().when().get().then().extract().statusCode();
		System.out.println(statusCode);
		String name = given().when().get().then().extract().path("data[0].name");
		System.out.println(name);
	}

	@Test
	public void getArrayListSize() {
		System.out.println("getArrayListSize");
		int size = given().when().get().then().extract().path("data.size()");
		System.out.println(size);
	}

	@Test
	public void getAllNames() {
		System.out.println("getAllNames");
		ArrayList<String> al = given().when().get().then().extract().path("data.name");
		al.stream().forEach(System.out::println);
	}

	@Test
	public void getAllWithName() {
		System.out.println("getAllWithName");
		List<HashMap<String, ?>> maps = given().when().get().then().extract().path("data.findAll{it.type=='BigBox'}");
		System.out.println(maps);

	}

	@Test
	public void getUsingCondition() {
		System.out.println("getUsingCondition");
		List<String> s = given().when().get().then().extract().path("data.findAll{it.type=='BigBox'}.city");
		System.out.println(s);
	}

	@Test
	public void getUsingPartialName() {
		System.out.println("getUsingPartialName");
		List<String> s = given().when().get().then().extract().path("data.findAll{it.type==~/Big.*/}.city");
		System.out.println(s);
	}

	@Test
	public void getUsingEndCondition() {
		System.out.println("getUsingEndCondition");
		List<String> s = given().when().get().then().extract().path("data.findAll{it.type==~/.*Box/}.city");
		System.out.println(s);
	}
}
