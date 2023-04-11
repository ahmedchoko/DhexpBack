package com.wevioo.parametrage.services;

import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Modalite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModaliteService {
    Page<Modalite> getAllModalite(int page, int size );

    Modalite createModalite(ModaliteDto modaliteRequest);

    Modalite getModaliteById(Long id);

    Modalite updateModalite(Long id, ModaliteDto modaliteRequest);

   Modalite deleteModalite(Long id);



}
