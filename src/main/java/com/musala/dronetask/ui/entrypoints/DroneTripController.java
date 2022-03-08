package com.musala.dronetask.ui.entrypoints;

import com.musala.dronetask.constants.APIUrls;
import com.musala.dronetask.io.entities.DroneTrip;
import com.musala.dronetask.io.entities.Medication;
import com.musala.dronetask.services.DroneTripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(APIUrls.DroneTrip_EP)
public class DroneTripController {
    private final DroneTripService service;

    public DroneTripController(DroneTripService service) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody
    DroneTrip addDroneTrip(@RequestBody DroneTrip droneTrip){
        return service.addDroneTrip(droneTrip);
    }

    @GetMapping("/checkAvailability/{droneId}/{date}")
    @ResponseBody
    ResponseEntity<Boolean> checkAvailability(@PathVariable Long droneId, @PathVariable Date date){
        return new ResponseEntity<>(service.isDroneAvailable(droneId,date), HttpStatus.OK);
    }
    @GetMapping("/medications/{droneId}/{date}")
    @ResponseBody
    List<Medication> getMedicationsForDroneByDate(@PathVariable Long droneId, @PathVariable Date date){
        return service.getMedicationsForDroneByDate(droneId,date);
    }

}
