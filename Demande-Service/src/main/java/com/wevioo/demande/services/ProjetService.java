package com.wevioo.demande.services;


import com.wevioo.demande.entities.Projet;

public interface ProjetService {

    Projet createProjet(Projet ProjetRequest);

    Projet getProjetById(Long id);

    Projet updateProjet(Long id, Projet ProjetRequest);

    Projet deleteProjet(Long id);
}
