package com.example.demo.adapter.rest;

import com.example.demo.domain.Vehicle;
import com.example.demo.domain.VehicleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/vehicles/{id}")
    public Vehicle getVehicle(@PathVariable final String id) {
        // TODO: implement - fetch vehicle from repository by id and return it
        return null;
    }
}
