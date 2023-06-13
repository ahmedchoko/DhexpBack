package com.wevioo.parametrage.servicesimpl;

import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.repository.DelegationRepository;
import com.wevioo.parametrage.services.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service pour la gestion des délégations.
 */
@Service
public class DelegationServiceImpl implements DelegationService {

    @Autowired
    private DelegationRepository delegationRepository;

    /**
     * Avoir la liste de toutes les délégations.
     *
     * @return Liste des délégations.
     */
    @Override
    public List<Delegation> getAllDelegations() {
        return delegationRepository.findAll();
    }
}
