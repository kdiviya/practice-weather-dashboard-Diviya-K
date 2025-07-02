package com.example.WeatherApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(Main.class, args);
		Scanner scanner = new Scanner(System.in);
		RestTemplate restTemplate =  new RestTemplate();
		String apiKey = "8bd20cb213b2248e0336fe5b331d737e";

		while (true) {
			System.out.println("Weather Dashboard ");
			System.out.println("1. Current Weather ");
			System.out.println("2. Weather Summary");
			System.out.println("3. Exit");
			System.out.println("Enter your choice: ");
			String choice = scanner.nextLine();

			if (choice.equals("1")) {
				System.out.println("Enter your city name");
				String cityName = scanner.nextLine();
				String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=metric";
				ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(response.getBody());
				System.out.println("Current Weather in " + root.path("name").asText() + " is " + root.path("weather").get(0).path("description").asText());
			}
			else if (choice.equals("2")) {
				System.out.println("Weather Summary:");
				System.out.println("Enter your city name");
				String cityName = scanner.nextLine();
				String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=metric";
				ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(response.getBody());
				System.out.println("Temperature: " + root.path("main").path("temp") + "°C");
				System.out.println("Description: " + root.path("weather").get(0).path("description").asText());
				System.out.println("Humidity: " + root.path("main").path("humidity"));

			}
			else {
				System.out.println("Thank you!!!");
				break;
			}
		}
	}
}

