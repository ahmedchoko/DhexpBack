package com.wevioo.parametrage.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wevioo.parametrage.dto.QuotiteDTO;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.services.QuotiteService;

@RestController
@RequestMapping("parametrage/api/v1/quotites")
public class QuotiteController {

    @Autowired
    private QuotiteService quotiteService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Add a new Quotite.
     *
     * @param quotite The Quotite object to be added.
     */
    @PostMapping()
    public void addQuotite(@RequestBody Quotite quotite) {
        try {
            quotiteService.addQuotite(quotite);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            throw new RuntimeException("Failed to add quotite: " + e.getMessage());
        }
    }


    /**
     * Get a Quotite by ID.
     *
     * @param id The ID of the Quotite to retrieve.
     * @return ResponseEntity containing the Quotite.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Quotite> getQuotiteById(@PathVariable Long id) {
        try {
            Quotite quotite = quotiteService.getQuotiteById(id);
            return ResponseEntity.ok(quotite);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Modify a Quotite.
     *
     * @param quotite The modified Quotite object.
     * @return ResponseEntity containing the ID of the modified Quotite.
     */
    @PutMapping()
    public ResponseEntity<Long> modifyQuotite(@RequestBody Quotite quotite) {
        try {
            Long modifiedQuotiteId = quotiteService.modifyQuotite(quotite);
            return ResponseEntity.status(HttpStatus.OK).body(modifiedQuotiteId);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Get a list of filtered Quotites.
     *
     * @param fondsearchTerm   The search term for Fond.
     * @param zonesearchTerm   The search term for Zone.
     * @param zonalsearchTerm  The search term for Zonal.
     * @param ritic            The search term for Ritic.
     * @param nouveauProm      The search term for NouveauProm.
     * @param creditLeasing    The search term for CreditLeasing.
     * @param page             The page number.
     * @param size             The page size.
     * @return ResponseEntity containing the list of filtered Quotites.
     * @throws ParseException if the search terms cannot be parsed.
     */
    @GetMapping()
    public ResponseEntity<?> getQuotites(
            @RequestParam(value = "fondsearchTerm", required = false) String fondsearchTerm,
            @RequestParam(value = "zonesearchTerm", required = false) String zonesearchTerm,
            @RequestParam(value = "zonalsearchTerm", required = false) String zonalsearchTerm,
            @RequestParam(value = "ritic", required = false) String ritic,
            @RequestParam(value = "nouveauProm", required = false) String nouveauProm,
            @RequestParam(value = "creditLeasing", required = false) String creditLeasing,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size) throws ParseException {
    	try { Map<String, Object> jsonResponseMap = new LinkedHashMap<>();

        Page<Quotite> listofQuotite = quotiteService.getAllQuotite(
                page, size, fondsearchTerm, zonesearchTerm, zonalsearchTerm, ritic, nouveauProm, creditLeasing);
        
        List<QuotiteDTO> listofQuotiteDto = new ArrayList<>();
        if (!listofQuotite.isEmpty()) {
            for (Quotite quotite : listofQuotite) {
                listofQuotiteDto.add(modelMapper.map(quotite, QuotiteDTO.class));
            }
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", listofQuotiteDto);
            jsonResponseMap.put("pagebale", listofQuotite);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } else {
            jsonResponseMap.put("status", 1);
            Page<Quotite> listofQuotite1 = quotiteService.getAllQuotite(page, size);
            for (Quotite quotite : listofQuotite1) {
                listofQuotiteDto.add(modelMapper.map(quotite, QuotiteDTO.class));
            }
            jsonResponseMap.put("data", listofQuotiteDto);
            jsonResponseMap.put("pagebale", listofQuotite1);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        }
    	}
    	catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
