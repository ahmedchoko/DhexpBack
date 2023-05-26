package com.wevioo.parametrage.services;

import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.dto.StoplossPartenaireDto;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.StoplossPartenaire;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ModaliteService {
    Page<Modalite> getAllModalite(int page, int size );

    Modalite createModalite(ModaliteDto modaliteRequest);

    Modalite getModaliteById(Long id);

    Modalite updateModalite(Long id, ModaliteDto modaliteRequest);

   Modalite deleteModalite(Long id);

    List nobreModaliteParType();

}
