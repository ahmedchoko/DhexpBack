package com.wevioo.parametrage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDeleg")
    private Long idDeleg;
    //code de la délégation
    @Column(name="codeDelegation", nullable = false, unique = true)
    //nom de la délégation
    private String codeDeleg;
    @Column(name="nomDelegation", nullable = false, unique = true)
    private String nomDeleg;
    //gouvernorat de la délégation
    @JsonIgnore
    @ManyToOne
    @Column(name="nomGouvernorat", nullable = false, unique = false)
    private Gouvernorat gouvernorat;


    
}
