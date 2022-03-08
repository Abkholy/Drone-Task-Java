package com.musala.dronetask.ui.entrypoints;

import com.musala.dronetask.constants.APIUrls;
import com.musala.dronetask.io.entities.Drone;
import com.musala.dronetask.services.DroneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIUrls.Drone_EP)
public class DroneEntryPoint {
    private final DroneService service;

    public DroneEntryPoint(DroneService service) {
        this.service = service;
    }

    @PostMapping()
    public Drone saveDrone(@RequestBody Drone droneToSave){
        return service.saveDrone(droneToSave);
    }

    @GetMapping()
    public List<Drone> findAllDrones(){
        return service.findAllDrones();
    }
}
