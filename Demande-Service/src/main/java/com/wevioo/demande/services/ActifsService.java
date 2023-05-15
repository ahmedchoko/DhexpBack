package com.wevioo.demande.services;

import com.wevioo.demande.entities.Actifs;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ActifsService {
    Actifs createActifs(Actifs ActifsRequest);

    Actifs getActifsById(Long id);

    Actifs updateActifs(Long id, Actifs ActifsRequest);

    Actifs deleteActifs(Long id);
}
