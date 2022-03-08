package com.musala.dronetask.io.repositories;

import com.musala.dronetask.io.entities.DroneTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneTripRepository extends JpaRepository<DroneTrip, Long> {
}