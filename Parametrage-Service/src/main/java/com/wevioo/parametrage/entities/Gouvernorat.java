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
    private Long idGouv;
    private String nomGouv;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Delegation> listeDelegations;
    @JsonIgnore
    @ManyToOne
    private Zone zone;
  
}
