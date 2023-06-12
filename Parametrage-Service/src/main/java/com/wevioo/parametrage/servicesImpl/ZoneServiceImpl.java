package com.wevioo.parametrage.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.dto.ZoneDTO;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.repository.ZoneRepository;
import com.wevioo.parametrage.services.ZoneService;


@Service
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;

    /**
     * Retrieves all zones with pagination.
     *
     * @param page The page number
     * @param size The number of items per page
     * @return A page of Zone objects
     */
    @Override
    public Page<Zone> getAllZones(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idZone"));
        Page<Zone> myDataPage = zoneRepository.findAll(pageable);
        return myDataPage;
    }

    /**
     * Creates a new Zone.
     *
     * @param zoneRequest The ZoneDTO object containing the details of the Zone
     * @return The created Zone object
     */
    @Override
    public Zone createZone(ZoneDTO zoneRequest) {
        Zone zone = Zone.builder()
                .nomZone(zoneRequest.getNomZone())
                .nomArabeZone(zoneRequest.getNomArabeZone())
                .gouvernorats(zoneRequest.getGouvernorats())
                .delegations(zoneRequest.getDelegations())
                .build();
        zoneRepository.save(zone);
        return zone;
    }

    /**
     * Retrieves a Zone by its ID.
     *
     * @param id The ID of the Zone to retrieve
     * @return The Zone object
     * @throws NoSuchElementException if the Zone is not found
     */
    @Override
    public Zone getZoneById(Long id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id " + id + " not found"));
        return zone;
    }
/**
 * {@inheritDoc}
 */
    @Override
    public Zone updateZone(Long id, ZoneDTO zoneRequest) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id " + id + " not found"));
        zone.setNomZone(zoneRequest.getNomZone());
        zone.setNomArabeZone(zoneRequest.getNomArabeZone());
        zone.setDelegations(zoneRequest.getDelegations());
        zone.setGouvernorats(zoneRequest.getGouvernorats());
        zoneRepository.save(zone);
        return zone;
    }

    /**
     * Deletes a Zone by its ID.
     *
     * @param id The ID of the Zone to delete
     * @return The deleted Zone object
     * @throws NoSuchElementException if the Zone is not found
     */
    @Override
    public Zone deleteZone(Long id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id " + id + " not found"));
        zoneRepository.deleteById(id);
        return zone;
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
