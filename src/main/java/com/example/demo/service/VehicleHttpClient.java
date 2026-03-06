package com.example.demo.service;

import com.example.demo.domain.Vehicle;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleHttpClient {

    @Autowired
    ObjectMapper objectMapper;

    public Vehicle fetchVehicle(String vehicleId) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/" + vehicleId + "?format=json";

            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode results = root.get("Results").get(0);

            Vehicle vehicle = new Vehicle();
            vehicle.id = vehicleId;
            vehicle.make = results.get("Make").asText();
            vehicle.model = results.get("Model").asText();
            vehicle.year = Integer.parseInt(results.get("ModelYear").asText());
            vehicle.color = "";
            vehicle.vin = vehicleId;
            vehicle.mileage = 0;

            System.out.println("Fetched vehicle: " + vehicle.id);

            return vehicle;
        } catch (Exception e) {
            System.out.println("Error fetching vehicle: " + e.getMessage());
            return null;
        }
    }
}
