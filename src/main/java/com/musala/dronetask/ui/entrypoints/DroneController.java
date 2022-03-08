package com.musala.dronetask.ui.entrypoints;

import com.musala.dronetask.constants.APIUrls;
import com.musala.dronetask.generic.ORestController;
import com.musala.dronetask.io.entities.Drone;
import com.musala.dronetask.services.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(APIUrls.Drone_EP)
public class DroneController extends ORestController<Drone> {

    private  DroneService service;
    public DroneController(DroneService inService) {
        super(inService);
        service = inService;
    }

    @GetMapping("/available/{date}")
    public @ResponseBody
    List<Drone> getDroneBatteryLevel(@PathVariable Date date){
        return service.getAllAvailableDrones(date);
    }

    @GetMapping("/battery/{droneId}")
    public @ResponseBody
    ResponseEntity<Float> getDroneBatteryLevel(@PathVariable Long droneId){
        return new ResponseEntity<Float>(getEntityById(droneId).getBattery(), HttpStatus.OK);
    }
}
