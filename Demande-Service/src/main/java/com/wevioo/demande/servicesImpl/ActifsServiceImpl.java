package com.wevioo.demande.servicesImpl;

import com.wevioo.demande.entities.Actifs;
import com.wevioo.demande.repository.ActifsRepository;
import com.wevioo.demande.services.ActifsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ActifsServiceImpl implements ActifsService {
    @Autowired
    ActifsRepository actifsRepository;
    @Override
    public Actifs createActifs(Actifs ActifsRequest) {
        Actifs actifs = Actifs.builder()
                .amort(ActifsRequest.getAmort())
                .totalActifs(ActifsRequest.getTotalActifs())
                .clientsComlptesRattaches(ActifsRequest.getClientsComlptesRattaches())
                .autresActNonCourants(ActifsRequest.getAutresActNonCourants())
                .immoCorporelles(ActifsRequest.getImmoCorporelles())
                .stocks(ActifsRequest.getStocks())
                .immoFinancieres(ActifsRequest.getImmoFinancieres())
                .immoIncorporelles(ActifsRequest.getImmoIncorporelles())
                .placementsAutresActNonFin(ActifsRequest.getPlacementsAutresActNonFin())
                .totalActCourants(ActifsRequest.getTotalActCourants())
                .totalActNonCourants(ActifsRequest.getTotalActNonCourants())
                .liquiditesEtEquivalents(ActifsRequest.getLiquiditesEtEquivalents())
                .build();
      return   actifsRepository.save(actifs);
    }

    @Override
    public Actifs getActifsById(Long id) {
        Actifs actifs = actifsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        return actifs;
    }

    @Override
    public Actifs updateActifs(Long id, Actifs ActifsRequest) {
        Actifs actifs = actifsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        actifs.setAmort(ActifsRequest.getAmort()) ;
        actifs.setTotalActifs(ActifsRequest.getTotalActifs());
        actifs.setClientsComlptesRattaches(ActifsRequest.getClientsComlptesRattaches());
        actifs.setAutresActNonCourants(ActifsRequest.getAutresActNonCourants());
        actifs.setImmoCorporelles(ActifsRequest.getImmoCorporelles());
        actifs.setStocks(ActifsRequest.getStocks());
        actifs.setImmoFinancieres(ActifsRequest.getImmoFinancieres());
        actifs.setImmoIncorporelles(ActifsRequest.getImmoIncorporelles());
        actifs.setPlacementsAutresActNonFin(ActifsRequest.getPlacementsAutresActNonFin());
        actifs.setTotalActCourants(ActifsRequest.getTotalActCourants());
        actifs.setTotalActNonCourants(ActifsRequest.getTotalActNonCourants());
        actifs.setLiquiditesEtEquivalents(ActifsRequest.getLiquiditesEtEquivalents());
        return actifsRepository.save(actifs);
    }

    @Override
    public Actifs deleteActifs(Long id) {
        Actifs actifs = actifsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        actifsRepository.deleteById(id);
        return actifs;
    }
}
