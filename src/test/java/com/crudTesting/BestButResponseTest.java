package com.crudTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class BestButResponseTest {

	static String JsonResponse;

	public static void print(String val) {
		System.out.println(val);

	}

	@BeforeAll
	public static void getResponse() {
		baseURI = "http://localhost";
		port = 3030;
		JsonResponse = given().when().get("/products").asString();
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
	public void getRootResponse() {
		Map<String, ?> json = JsonPath.read(JsonResponse, "$");
		print(json.toString());
	}

	@Test
	public void getTotal() {

		int total = JsonPath.read(JsonResponse, "$.total");
		System.out.println("Total =" + total);

	}

	@Test
	public void getDataElements() {
		List<Map<String, ?>> dataList = JsonPath.read(JsonResponse, "$.data");
		dataList.stream().forEach(System.out::println);
	}

	@Test
	public void getFirstData() {
		Map<String, ?> First = JsonPath.read(JsonResponse, "$['data'][1]");
		print(First.toString());
	}

	@Test
	public void getlastData() {
		Map<String, ?> Last = JsonPath.read(JsonResponse, "$.data[-1]");
		print(Last.toString());
	}

	@Test
	public void getNameList() {
		List<String> list = JsonPath.read(JsonResponse, "$.data[*].name");
		print(list.toString());

	}

	@Test
	public void extractUsingCondition() {
		List<String> s = JsonPath.read(JsonResponse, "$.data[?(@.price<5)].name");
		s.stream().forEach(System.out::println);
	}
}
