package com.musala.dronetask.io.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.dronetask.generic.OEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class DroneTrip extends OEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private Drone drone;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Medication> medications;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat( shape = JsonFormat.Shape.STRING)
    private Date tripDate;


}
