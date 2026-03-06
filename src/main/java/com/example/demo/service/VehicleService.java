package com.example.demo.service;

import com.example.demo.domain.Vehicle;
import com.example.demo.domain.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class VehicleService {

    @Autowired
    VehicleHttpClient vehicleHttpClient;

    @Autowired
    VehicleRepository vehicleRepository;

    public ArrayList<Vehicle> processedVehicles = new ArrayList<>();

    public synchronized Vehicle fetchAndSaveVehicle(String vehicleId) {
        try {
            Vehicle vehicle = vehicleHttpClient.fetchVehicle(vehicleId);

            if (vehicle.vin.length() > 0) {
                vehicleRepository.save(vehicle);
                processedVehicles.add(vehicle);
                System.out.println("Saved vehicle with id " + vehicle.id + " to database");
            }

            return vehicle;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Vehicle> getProcessedVehicles() {
        return processedVehicles;
    }
}
