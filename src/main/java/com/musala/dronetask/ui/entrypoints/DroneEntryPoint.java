package com.musala.dronetask.ui.entrypoints;

import com.musala.dronetask.constants.APIUrls;
import com.musala.dronetask.generic.ORestController;
import com.musala.dronetask.io.entities.Drone;
import com.musala.dronetask.services.DroneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIUrls.Drone_EP)
public class DroneEntryPoint extends ORestController<Drone> {

    public DroneEntryPoint(DroneService inService) {
        super(inService);
    }
}
