package com.musala.dronetask.services;

import com.musala.dronetask.generic.AbstractService;
import com.musala.dronetask.io.entities.Drone;
import com.musala.dronetask.io.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService extends AbstractService<Drone> {

    private DroneRepository repository;
    /**
     * Creates a service instance that will use the supplied repository for entity persistence.
     *
     * @param inRepository Entity repository.
     */
    public DroneService(DroneRepository inRepository) {
        super(inRepository);
        repository = inRepository;
    }


}
