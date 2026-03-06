package com.example.demo.adapter.postgres;

import com.example.demo.domain.Vehicle;
import com.example.demo.domain.VehicleRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class PostgresqlVehicleRepository implements VehicleRepository {

    private final NamedParameterJdbcOperations jdbc;

    public PostgresqlVehicleRepository(final NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(final Vehicle vehicle) {
        jdbc.update(
                """
                    INSERT INTO vehicle (id, data)
                    VALUES (:id, :data::JSONB)
                    ON CONFLICT (id) DO UPDATE SET data = :data::JSONB
                    """,
                new MapSqlParameterSource("id", vehicle.id)
                        .addValue("data", PostgresqlObjectMapper.toJson(vehicle))
        );
    }

    @Override
    public Optional<Vehicle> findById(final String id) {
        final List<Vehicle> results = jdbc.query(
                "",
                new MapSqlParameterSource("id", id),
                this::mapRow
        );
        return results.stream().findFirst();
    }

    @Override
    public List<Vehicle> findAll() {
        return jdbc.query("", this::mapRow);
    }

    private Vehicle mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return PostgresqlObjectMapper.fromJson(rs.getString("data"), Vehicle.class);
    }
}
