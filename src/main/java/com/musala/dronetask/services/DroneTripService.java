package com.musala.dronetask.services;

import com.musala.dronetask.exceptions.CouldNotCreateRecordException;
import com.musala.dronetask.io.entities.Drone;
import com.musala.dronetask.io.entities.DroneTrip;
import com.musala.dronetask.io.entities.Medication;
import com.musala.dronetask.io.repositories.DroneRepository;
import com.musala.dronetask.io.repositories.DroneTripRepository;
import com.musala.dronetask.io.repositories.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroneTripService {
    private final DroneTripRepository droneTripRepository;
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;


    public DroneTripService(DroneTripRepository droneTripRepository, DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneTripRepository = droneTripRepository;
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    public Boolean isDroneAvailable(Long droneId, Date tripDate){
        return droneTripRepository.TripExistsByDroneAndDateBetween(droneId,tripDate);
    }

    public List<Medication> getMedicationsForDroneByDate(Long droneId, Date tripDate){
        Optional<DroneTrip> existsTrip = droneTripRepository.getTripByDroneAndDate(droneId, tripDate);
        if(existsTrip.isPresent()){
            return existsTrip.get().getMedications();
        }
        return new ArrayList<>();
    }

    public DroneTrip addDroneTrip(DroneTrip droneTrip){
        Drone drone = droneRepository.getById(droneTrip.getDrone().getId());
//        if (! isDroneAvailable(droneTrip.getDrone().getId(),droneTrip.getTripStartDate())){
//            throw new CouldNotCreateRecordException("Drone Is Not Available");
//        }
        List<Medication> medications = medicationRepository.findAllById(droneTrip.getMedications().stream().map(Medication::getId).collect(Collectors.toList()));
        if (drone.getWeightLimit() < medications.stream().mapToDouble(Medication::getWeight).sum()){
            throw new CouldNotCreateRecordException("Weight is Higher than deron weight limit");
        }
        if (drone.getBattery()<0.25){
            throw new CouldNotCreateRecordException("please ensure the bettery is greater than 25%");
        }
        return droneTripRepository.save(droneTrip);
    }
}
