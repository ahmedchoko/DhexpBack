package com.wevioo.parametrage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDeleg;
    private String nomDeleg;
    @JsonIgnore
    @ManyToOne
    private Gouvernorat gouvernorat;
}
