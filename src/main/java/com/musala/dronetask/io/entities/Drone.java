package com.musala.dronetask.io.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.dronetask.enums.DroneModel;
import com.musala.dronetask.enums.DroneState;
import com.musala.dronetask.generic.OEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Drone extends OEntity {

    @NotNull(message = "Serial Number is mandatory")
    @Size(min=2, max=100,message = "Serial Number Must Greater Than 5 characters and less than 100 character")
    @Column(length = 100)
    private String serialNumber;

    @NotNull(message = "Drone Model is mandatory")
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @NotNull(message = "Drone Model is mandatory")
    @Max(value = 500,message = "Weight Limit must lower than 500")
    @Column()
    private Float weightLimit;

    @NotNull(message = "battery percentage is mandatory")
    @Min(value = 0)
    @Max(value = 1)
    @Audited()
    @Column()
    private Float battery;
    @Enumerated(EnumType.STRING)
    private DroneState state = DroneState.IDLE;
}
