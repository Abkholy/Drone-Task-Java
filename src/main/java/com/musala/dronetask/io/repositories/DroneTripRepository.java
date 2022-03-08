package com.musala.dronetask.io.repositories;

import com.musala.dronetask.io.entities.DroneTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DroneTripRepository extends JpaRepository<DroneTrip, Long> {

    @Query("select (count(d) > 0) from DroneTrip d where d.drone.id = ?1 and  (?2 between d.tripStartDate and d.tripEndDate)")
    boolean TripExistsByDroneAndDateBetween(Long droneId, Date tripDate);

    @Query("select d from DroneTrip d where d.drone.id = ?1 and  (?2 between d.tripStartDate and d.tripEndDate)")
    Optional<DroneTrip> getTripByDroneAndDate(Long droneId, Date tripDate);

}