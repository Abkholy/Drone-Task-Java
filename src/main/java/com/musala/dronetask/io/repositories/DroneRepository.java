package com.musala.dronetask.io.repositories;

import com.musala.dronetask.io.entities.Drone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Long> {
}