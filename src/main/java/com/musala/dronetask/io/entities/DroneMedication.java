package com.musala.dronetask.io.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.dronetask.generic.OEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class DroneMedication extends OEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private Drone drone;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Medication> medications;

    private Date tripDate;


}
