package com.wevioo.demande.services;

import com.wevioo.demande.entities.Passifs;

public interface PassifsService {

    Passifs createPassifs(Passifs PassifsRequest);

    Passifs getPassifsById(Long id);

    Passifs updatePassifs(Long id, Passifs PassifsRequest);

    Passifs deletePassifs(Long id);
}
