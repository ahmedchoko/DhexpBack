package com.wevioo.demande.servicesImpl;

import com.wevioo.demande.entities.Passifs;
import com.wevioo.demande.repository.PassifsRepository;
import com.wevioo.demande.services.PassifsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service

public class PassifsServiceImpl implements PassifsService {
    @Autowired
    PassifsRepository passifsRepository;
    @Override
    public Passifs createPassifs(Passifs PassifsRequest) {
        Passifs passifs = Passifs.builder()
                .capitalSocial(PassifsRequest.getCapitalSocial())
                .capitauxPropres(PassifsRequest.getCapitauxPropres())
                .passifsCour(PassifsRequest.getPassifsCour())
                .concoursBancaires(PassifsRequest.getConcoursBancaires())
                .emprunt(PassifsRequest.getEmprunt())
                .fourComptesRattaches(PassifsRequest.getFourComptesRattaches())
                .totalPassifs(PassifsRequest.getTotalPassifs())
                .reserve(PassifsRequest.getReserve())
                .provisions(PassifsRequest.getProvisions())
                .ResExercice(PassifsRequest.getResExercice())
                .passifsFinanciers(PassifsRequest.getPassifsFinanciers())
                .totalActifsNonCour(PassifsRequest.getTotalActifsNonCour())
                .resultatReporte(PassifsRequest.getResultatReporte())
                .totalCapitauxPropresEtPassifs(PassifsRequest.getTotalCapitauxPropresEtPassifs())
                .totalCapitauxPropresAvRes(PassifsRequest.getTotalCapitauxPropresAvRes())
                .totalPassifsCourants(PassifsRequest.getTotalPassifsCourants())
                .totalCapitauxPropres(PassifsRequest.getTotalCapitauxPropres())
                .build();
        return passifsRepository.save(passifs);
    }

    @Override
    public Passifs getPassifsById(Long id) {
        Passifs passifs = passifsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        return passifs;
    }

    @Override
    public Passifs updatePassifs(Long id, Passifs PassifsRequest) {
        Passifs passifs = passifsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        passifs.setCapitalSocial(PassifsRequest.getCapitalSocial());
        passifs.setCapitauxPropres(PassifsRequest.getCapitauxPropres());
        passifs.setPassifsCour(PassifsRequest.getPassifsCour());
        passifs.setConcoursBancaires(PassifsRequest.getConcoursBancaires());
        passifs.setEmprunt(PassifsRequest.getEmprunt());
        passifs.setFourComptesRattaches(PassifsRequest.getFourComptesRattaches());
        passifs.setTotalPassifs(PassifsRequest.getTotalPassifs());
        passifs.setReserve(PassifsRequest.getReserve());
        passifs.setProvisions(PassifsRequest.getProvisions());
        passifs.setResExercice(PassifsRequest.getResExercice());
        passifs.setPassifsFinanciers(PassifsRequest.getPassifsFinanciers());
        passifs.setTotalActifsNonCour(PassifsRequest.getTotalActifsNonCour());
        passifs.setResultatReporte(PassifsRequest.getResultatReporte());
        passifs.setTotalCapitauxPropresEtPassifs(PassifsRequest.getTotalCapitauxPropresEtPassifs());
        passifs.setTotalCapitauxPropresAvRes(PassifsRequest.getTotalCapitauxPropresAvRes());
        passifs.setTotalPassifsCourants(PassifsRequest.getTotalPassifsCourants());
        passifs.setTotalCapitauxPropres(PassifsRequest.getTotalCapitauxPropres());
        return passifsRepository.save(passifs);
    }

    @Override
    public Passifs deletePassifs(Long id) {
        Passifs passifs = passifsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        passifsRepository.deleteById(id);
        return passifs;
    }
}
