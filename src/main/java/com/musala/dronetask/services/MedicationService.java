package com.musala.dronetask.services;

import com.musala.dronetask.generic.AbstractService;
import com.musala.dronetask.io.entities.Medication;
import com.musala.dronetask.io.repositories.MedicationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicationService extends AbstractService<Medication> {
    private  MedicationRepository repository;


    /**
     * Creates a service instance that will use the supplied repository for entity persistence.
     *
     * @param inRepository Entity repository.
     */
    public MedicationService(MedicationRepository inRepository) {
        super(inRepository);
        repository = inRepository;
    }
}
