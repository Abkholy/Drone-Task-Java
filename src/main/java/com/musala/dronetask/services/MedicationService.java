package com.musala.dronetask.services;

import com.musala.dronetask.io.repositories.MedicationRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {
    private final MedicationRepository repository;

    public MedicationService(MedicationRepository repository) {
        this.repository = repository;
    }
}
