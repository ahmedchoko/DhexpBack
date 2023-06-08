package com.wevioo.parametrage.services;

import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Modalite;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ModaliteService {

    void Vérif(ModaliteDto modaliteRequest) throws Exception;
    Page<Modalite> getAllModalite(int page, int size ) throws Exception;

    Modalite createModalite(ModaliteDto modaliteRequest) throws Exception;

    Modalite getModaliteById(Long id) throws Exception;

    Modalite updateModalite(Long id, ModaliteDto modaliteRequest) throws Exception;

   Modalite deleteModalite(Long id) throws Exception;

    List nobreModaliteParType();

}
