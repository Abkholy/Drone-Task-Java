package com.musala.dronetask.services;

import com.musala.dronetask.generic.AbstractService;
import com.musala.dronetask.io.entities.Drone;
import com.musala.dronetask.io.repositories.DroneRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@EnableAsync
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


    public List<Drone> getAllAvailableDrones(Date date){
        return repository.getAllAvailableDrones(date);
    }


    /**
     * check drones batteries every 30 second
     * @throws InterruptedException
     */
    @Async
    @Scheduled(cron = "0/30 * * * * *")
    public void checkBatteries() throws InterruptedException {
        List<Drone> dronesHasLowBatteries = repository.findByBatteryIsLessThanEqual(0.25F);
        System.err.println("Drones Has Low Batteries Count " + dronesHasLowBatteries.size() ) ;
        Thread.sleep(2000);
    }


}
