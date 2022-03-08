package com.musala.dronetask.io.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.dronetask.enums.DroneModel;
import com.musala.dronetask.enums.DroneState;
import com.musala.dronetask.generic.OEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Drone extends OEntity {
    @Column(length = 100)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Column()
    private Float weightLimit;
    @Audited()
    @Column()
    private Float battery;
    @Enumerated(EnumType.STRING)
    private DroneState state = DroneState.IDLE;
}
