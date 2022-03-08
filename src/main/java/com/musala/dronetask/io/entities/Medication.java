package com.musala.dronetask.io.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.dronetask.generic.OEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Medication extends OEntity {
    @NotNull(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$",message = "Name allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;
    @NotNull(message = "Code is required")
    @Pattern(regexp = "^[A-Z0-9_.]*$",message = "Code allowed only Upper Case letters, numbers, ‘_’")
    private String code;
    @NotNull(message = "Weight is required")
    private Float weight;
    private String image;
}
