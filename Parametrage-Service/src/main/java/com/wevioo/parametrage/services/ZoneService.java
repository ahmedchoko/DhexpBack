package com.wevioo.parametrage.services;

import java.util.List;


import com.wevioo.parametrage.dto.ZoneDTO;
import com.wevioo.parametrage.entities.Zone;
import org.springframework.data.domain.Page;

public interface ZoneService {
	Page<Zone> getAllZones(int page, int size );

	Zone createZone(ZoneDTO zoneRequest);

	Zone getZoneById(Long id);


    /**
     * Updates a Zone.
     *
     * @param id           The ID of the Zone to update
     * @param zoneRequest  The ZoneDTO object containing the updated details of the Zone
     * @return The updated Zone object
     * @throws  if the Zone is not found
     */
	Zone updateZone(Long id, ZoneDTO zoneRequest);

	Zone deleteZone(Long id);
	 public List<Zone> listZone();
	 public List<Zone> listZoneWithoutAffectation();
}
