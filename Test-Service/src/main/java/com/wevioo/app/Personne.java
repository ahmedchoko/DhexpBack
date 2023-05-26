package com.wevioo.app;

import com.wevioo.parametrage.entities.Zone;

import javax.persistence.*;

@Entity
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPersonne;
    private String name;
    @OneToOne
    private Zone zone;

}
