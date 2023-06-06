package com.wevioo.parametrage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Gouvernorat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idGouvernorat")
    private Long idGouv;
    //nom du gouvernorat
    @Column(name="nom_Gouvernorat", nullable = false, unique = true)
    private String nomGouv;
    //liste des délégations du gouvernorat

    @OneToMany(fetch = FetchType.EAGER)
    private List<Delegation> listeDelegations;
    //zone à laquelle appartient le gouvernorat

    @JsonIgnore
    @ManyToOne
    private Zone zone;
  
}
