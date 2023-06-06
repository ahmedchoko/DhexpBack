package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.dto.PartenaireDTO;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.enums.TypePatenaire;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.services.PartenaireService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("parametrage/api/v1/partenaires")
public class PartenaireController {

    @Autowired
    private PartenaireService partenaireService;

    @Autowired
    private PartenaireRepository partenaireRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get a Partenaire by its ID.
     *
     * @param id The ID of the Partenaire.
     * @return ResponseEntity containing the retrieved Partenaire.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPartenaireById(@PathVariable Long id) {
        Partenaire partenaire = partenaireService.getPartenaireById(id);
        if (partenaire != null) {
            PartenaireDTO partenaireDto = modelMapper.map(partenaire, PartenaireDTO.class);
            return ResponseEntity.ok(partenaireDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new Partenaire.
     *
     * @param partenaire The Partenaire object containing the details of the Partenaire to be created.
     * @return ResponseEntity containing the ID of the created Partenaire.
     */
    @PostMapping()
    public ResponseEntity<Long> createPartenaire(@RequestBody Partenaire partenaire) {
        Long partenaireId = partenaireService.addPartenaire(partenaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(partenaireId);
    }

    /**
     * Update a Partenaire by its ID.
     *
     * @param id         The ID of the Partenaire to be updated.
     * @param partenaire The updated Partenaire object.
     * @return ResponseEntity containing the ID of the updated Partenaire.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePartenaire(@PathVariable Long id, @RequestBody Partenaire partenaire) {
        Long partenaireId = partenaireService.modifyPartenaire(partenaire);
        return ResponseEntity.status(HttpStatus.OK).body(partenaireId);
    }

    /**
     * Add a Partenaire with a list of Conventions.
     *
     * @param conventions The list of Conventions to be associated with the Partenaire.
     * @return ResponseEntity containing the ID of the created Partenaire.
     */
    @PostMapping("/conventions")
    public ResponseEntity<Long> addPartenaireWithConvention(@RequestBody List<Convention> conventions) {
        Convention convention = partenaireService.addPartenairewithcvt(conventions);
        return ResponseEntity.status(HttpStatus.CREATED).body(convention.getPartenaire().getIdPartenaire());
    }

    /**
     * Update a Partenaire with a list of Conventions.
     *
     * @param conventions The list of Conventions to be associated with the Partenaire.
     * @return ResponseEntity containing the ID of the updated Partenaire.
     */
    @PutMapping("/conventions")
    public ResponseEntity<Long> modifyPartenaireWithConvention(@RequestBody List<Convention> conventions) {
        Long partenaireId = partenaireService.modifyPartenairewithcvt(conventions);
        return ResponseEntity.status(HttpStatus.OK).body(partenaireId);
    }

    /**
     * Update a Convention.
     *
     * @param idConvention    The ID of the Convention to be updated.
     * @param critere          The new value for the critere.
     * @param dateBlocage      The new value for the dateBlocage.
     * @return ResponseEntity containing the ID of the updated Convention.
     * @throws ParseException if the dateBlocage cannot be parsed.
     */
    @PutMapping()
    public ResponseEntity<Long> modifyConvention(
            @RequestParam(defaultValue = "IdConvention") Long idConvention,
            @RequestParam(defaultValue = "critere") String critere,
            @RequestParam(defaultValue = "dateBlocage") String dateBlocage) throws ParseException {
        partenaireService.modifyConvnetion(idConvention, critere, dateBlocage);
        return ResponseEntity.ok(idConvention);
    }


    /**
     * Get the count of Partenaires by type.
     *
     * @return ResponseEntity containing the count of Partenaires by type.
     */
    @GetMapping("/getPartenaireParType")
    public ResponseEntity<List> getPartenaireParType() {
        List partenaireCountByType = partenaireRepository.getNobrePartenaireParType();
        return ResponseEntity.ok(partenaireCountByType);
    }

    /**
     * Get the total count of Partenaires.
     *
     * @return ResponseEntity containing the total count of Partenaires.
     */
    @GetMapping("/nombreTotalPartenaire")
    public ResponseEntity<Integer> getNombreTotalPartenaire() {
        int totalPartenaires = partenaireService.nombreTotalPartenaire();
        return ResponseEntity.ok(totalPartenaires);
    }

    /**
     * Get a list of Partenaires based on the provided filters.
     *
     * @param searchFond            The search term for Fond.
     * @param searchModalite        The search term for Modalite.
     * @param MontantMaxsearchTerm  The search term for the maximum Montant.
     * @param MontantMinsearchTerm  The search term for the minimum Montant.
     * @param StatutsearchTerm      The search term for Statut.
     * @param page                  The page number.
     * @param size                  The page size.
     * @return ResponseEntity containing the list of filtered Partenaires.
     * @throws ParseException if the search terms cannot be parsed.
     */
    @GetMapping()
    public ResponseEntity<?> getPartenaires(
            @RequestParam(value = "searchFond", required = false) String searchFond,
            @RequestParam(value = "searchModalite", required = false) String searchModalite,
            @RequestParam(value = "MontantMaxsearchTerm", required = false) String MontantMaxsearchTerm,
            @RequestParam(value = "MontantMinsearchTerm", required = false) String MontantMinsearchTerm,
            @RequestParam(value = "StatutsearchTerm", required = false) String StatutsearchTerm,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) throws ParseException {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<>();

        Page<Partenaire> listofPartenaire = partenaireService.getAllPartenaire(
                searchFond, searchModalite, MontantMinsearchTerm, MontantMaxsearchTerm,
                StatutsearchTerm, page, size);

        List<PartenaireDTO> listofPartenaireDTO = new ArrayList<>();
        if (!listofPartenaire.isEmpty()) {
            for (Partenaire partenaire : listofPartenaire) {
                listofPartenaireDTO.add(modelMapper.map(partenaire, PartenaireDTO.class));
            }
            jsonResponseMap.put("status", HttpStatus.OK.value());
            jsonResponseMap.put("data", listofPartenaireDTO);
            jsonResponseMap.put("pageable", listofPartenaire);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } else {
            jsonResponseMap.put("status", HttpStatus.OK.value());
            jsonResponseMap.put("data", listofPartenaireDTO);
            jsonResponseMap.put("pageable", listofPartenaire);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        }
    }
}
