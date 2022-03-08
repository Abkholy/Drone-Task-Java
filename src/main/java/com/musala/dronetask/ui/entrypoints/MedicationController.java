package com.musala.dronetask.ui.entrypoints;

import com.musala.dronetask.constants.APIUrls;
import com.musala.dronetask.generic.ORestController;
import com.musala.dronetask.io.entities.Medication;
import com.musala.dronetask.services.MedicationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIUrls.Medication_EP)
public class MedicationController extends ORestController<Medication> {
    public MedicationController(MedicationService inService) {
        super(inService);
    }
}
