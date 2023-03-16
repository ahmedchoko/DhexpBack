package com.wevioo.parametrage.services;

import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Modalite;

import java.util.List;

public interface ModaliteService {
    List<Modalite> getAllModalite();

    Modalite createModalite(ModaliteDto modaliteRequest);

    Modalite getModaliteById(Long id);

    Modalite updateModalite(Long id, ModaliteDto modaliteRequest);

   Modalite deleteModalite(Long id);



}
