package com.example.demo.domain;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    void save(Vehicle vehicle);

    Optional<Vehicle> findById(String id);

    List<Vehicle> findAll();
}
