package com.musala.dronetask.services;

import com.musala.dronetask.io.entities.Drone;
import com.musala.dronetask.io.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {

    private final DroneRepository repository;

    public DroneService(DroneRepository repository) {
        this.repository = repository;
    }

    public Drone saveDrone (Drone droneToSave){
        return repository.save(droneToSave);
    }

    public List<Drone> findAllDrones(){
        return (List<Drone>) repository.findAll();
    }
}
