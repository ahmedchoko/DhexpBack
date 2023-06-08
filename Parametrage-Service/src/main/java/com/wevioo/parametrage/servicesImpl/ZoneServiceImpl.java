package com.wevioo.parametrage.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.wevioo.parametrage.dto.ZoneDTO;
import com.wevioo.parametrage.entities.Gouvernorat;
import com.wevioo.parametrage.repository.DelegationRepository;
import com.wevioo.parametrage.repository.GouvernoratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.repository.ZoneRepository;
import com.wevioo.parametrage.services.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private DelegationRepository delegationRepository;
    @Autowired
    private GouvernoratRepository gouvernoratRepository;
    private String errMsg;
    void Vérif(ZoneDTO zoneRequest) throws Exception{
        if((zoneRequest.getNomZone() == null) || (zoneRequest.getNomZone().isEmpty())){
            errMsg = "empty nom field";
            throw new Exception(errMsg);
        }
        if((zoneRequest.getNomArabeZone() == null) || (zoneRequest.getNomArabeZone().isEmpty())){
            errMsg = "empty nom Arabe field";
            throw new Exception(errMsg);
        }
        if((zoneRequest.getCodeZone() == null) || (zoneRequest.getCodeZone().isEmpty())){
            errMsg = "empty code field";
            throw new Exception(errMsg);
        }
        if(zoneRequest.getGouvernorats().isEmpty() || (zoneRequest.getGouvernorats() == null) ){
            errMsg = "empty gouvernorats field";
            throw new Exception(errMsg);
        }
        if(zoneRequest.getDelegations().isEmpty() || (zoneRequest.getDelegations() == null) ){
            errMsg = "empty delegations field";
            throw new Exception(errMsg);
        }
        if((! zoneRepository.findByNomZone(zoneRequest.getNomZone()).isEmpty())
        || (! zoneRepository.findByNomArabeZone(zoneRequest.getNomArabeZone()).isEmpty())
        || (! zoneRepository.findByCodeZone(zoneRequest.getCodeZone()).isEmpty())){
            errMsg = "Zone existante";
            throw new Exception(errMsg);
        }

        if(! gouvernoratRepository.findAll().containsAll(zoneRequest.getGouvernorats())) {
            errMsg = "Gouvernorat(s) non existant(s)!";
            throw new Exception(errMsg);
        }
        if(! delegationRepository.findAll().containsAll(zoneRequest.getDelegations())) {
            errMsg = "Gouvernorat(s) non existant(s)!";
            throw new Exception(errMsg);
        }

        for (Zone zone : zoneRepository.findAll()) {
            if (zone.getGouvernorats().containsAll(zoneRequest.getGouvernorats())) {
                errMsg = "Zone dupliquée!";
                throw new Exception(errMsg);
            }
        }

    }
    /**
     * Retrieves all zones with pagination.
     *
     * @param page The page number
     * @param size The number of items per page
     * @return A page of Zone objects
     */
    @Override
    public Page<Zone> getAllZones(int page, int size) throws Exception{
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idZone"));
            Page<Zone> myDataPage = zoneRepository.findAll(pageable);
            return myDataPage;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Creates a new Zone.
     *
     * @param zoneRequest The ZoneDTO object containing the details of the Zone
     * @return The created Zone object
     */
    @Override
    public Zone createZone(ZoneDTO zoneRequest) throws Exception {
        Vérif(zoneRequest);
        try {
            Zone zone = Zone.builder()
                    .nomZone(zoneRequest.getNomZone())
                    .nomArabeZone(zoneRequest.getNomArabeZone())
                    .codeZone(zoneRequest.getCodeZone())
                    .gouvernorats(zoneRequest.getGouvernorats())
                    .delegations(zoneRequest.getDelegations())
                    .build();
            zoneRepository.save(zone);
            return zone;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Retrieves a Zone by its ID.
     *
     * @param id The ID of the Zone to retrieve
     * @return The Zone object
     * @throws NoSuchElementException if the Zone is not found
     */
    @Override
    public Zone getZoneById(Long id) throws Exception{
        try{
            Zone zone = zoneRepository.findById(id)
                    .orElseThrow(() -> new Exception("Zone inexistante!"));
            return zone;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    /**
     * Updates a Zone.
     *
     * @param id           The ID of the Zone to update
     * @param zoneRequest  The ZoneDTO object containing the updated details of the Zone
     * @return The updated Zone object
     * @throws NoSuchElementException if the Zone is not found
     */
    @Override
    public Zone updateZone(Long id, ZoneDTO zoneRequest) throws Exception {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new Exception("Zone inexistante!"));
        Vérif(zoneRequest);
        try {
            zone.setCodeZone(zoneRequest.getCodeZone());
            zone.setNomZone(zoneRequest.getNomZone());
            zone.setNomArabeZone(zoneRequest.getNomArabeZone());
            zone.setDelegations(zoneRequest.getDelegations());
            zone.setGouvernorats(zoneRequest.getGouvernorats());
            zoneRepository.save(zone);
            return zone;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Deletes a Zone by its ID.
     *
     * @param id The ID of the Zone to delete
     * @return The deleted Zone object
     * @throws NoSuchElementException if the Zone is not found
     */
    @Override
    public Zone deleteZone(Long id) throws Exception{
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new Exception("Zone inexistante!"));
        try {
            zoneRepository.deleteById(id);
            return zone;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Retrieves a list of zones that are not assigned a quota.
     *
     * @return A list of Zone objects without a quota
     */
    @Override
    public List<Zone> listZone() {
        List<Zone> zonesNonQuotite = new ArrayList<Zone>();
        for (Zone zone : zoneRepository.findAll()) {
            if (zone.getQuotitee() == null) {
                zonesNonQuotite.add(zone);
            }
        }
        return zonesNonQuotite;
    }

    /**
     * Retrieves a list of all zones without considering any assignment.
     *
     * @return A list of all Zone objects
     */
    @Override
    public List<Zone> listZoneWithoutAffectation() {
        List<Zone> zones = new ArrayList<Zone>();
        for (Zone zone : zoneRepository.findAll()) {
            zones.add(zone);
        }
        return zones;
    }
}
