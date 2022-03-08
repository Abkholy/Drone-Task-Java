package com.musala.dronetask.io.repositories;

import com.musala.dronetask.enums.DroneState;
import com.musala.dronetask.io.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByState(DroneState state);
    List<Drone> findByBatteryIsLessThanEqual(Float battery);


    @Query("select d from Drone d left join DroneTrip trip where ?1 not between trip.tripStartDate and trip.tripEndDate ")
    List<Drone> getAllAvailableDrones(Date tripDate);
}