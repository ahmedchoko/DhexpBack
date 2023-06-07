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

import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.entities.Activite;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.servicesImpl.ParametrageProduce;

@RestController
@RequestMapping("parametrage/api/v1/fonds")
public class FondController {

    private final ParametrageProduce parametrageProducer;
    private final FondService fondService;
    private final ModelMapper modelMapper;

    public FondController(ParametrageProduce parametrageProducer, FondService fondService, ModelMapper modelMapper) {
        this.parametrageProducer = parametrageProducer;
        this.fondService = fondService;
        this.modelMapper = modelMapper;
    }

    /**
     * Ajouter un fond.
     *
     * @param fondDto Le fond à ajouter.
     * @return ResponseEntity contenant le résultat de l'opération.
     */
    @PostMapping
    public ResponseEntity<?> addFond(@RequestBody FondDTO fondDto) {
        try {
            Fond fond = modelMapper.map(fondDto, Fond.class);
            fondService.addFond(fond);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the fond.");
        }
    }


    /**
     * Avoir un fond par son Id.
     *
     * @param id L'identifiant du fond.
     * @return ResponseEntity contenant le fond correspondant à l'identifiant donné.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getFondById(@PathVariable Long id) {
        try {
            Fond fond = fondService.getFondById(id);
            if (fond != null) {
                FondDTO fondDto = modelMapper.map(fond, FondDTO.class);
                return ResponseEntity.ok(fondDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving the fond.");
        }
    }


    /**
     * Supprimer un fond par son Id.
     *
     * @param id L'identifiant du fond à supprimer.
     * @return ResponseEntity indiquant le résultat de l'opération de suppression.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFond(@PathVariable Long id) {
        try {
            fondService.deleteFond(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Modifier un fond par son Id.
     *
     * @param fondDto Le fond à modifier.
     * @return ResponseEntity indiquant le résultat de l'opération de modification.
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> modifyFond(@PathVariable(name = "id") Long id,@RequestBody FondDTO fondDto) {
        try {
            Fond fond = modelMapper.map(fondDto, Fond.class);
            fondService.modifyFond(fond);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while modifying the fond.");
        }
    }

    /**
     * Avoir la liste des fond.
     *
     * @return ResponseEntity indiquant la liste des fonds.
     */
    @GetMapping
    public ResponseEntity<List<Fond>> listFond() {
        try {
            List<Fond> fonds = fondService.listFond();
            return ResponseEntity.ok(fonds);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Avoir la liste des secteurs.
     *
     * @return Liste des secteurs.
     */
    @GetMapping("/secteurs")
    public ResponseEntity<List<Secteur>> getAllSecteur() {
        try {
            List<Secteur> secteurs = fondService.getAllSecteur();
            return ResponseEntity.ok(secteurs);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /**
     * Avoir la liste des activités.
     *
     * @return Liste des activités.
     */
    @GetMapping("/activites")
    public ResponseEntity<List<Activite>> getAllActivite() {
        try {
            List<Activite> activites = fondService.listActivites();
            return ResponseEntity.ok(activites);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /**
     * Avoir la liste des fonds non archivés.
     *
     * @return Liste des fonds non archivés.
     */
    @GetMapping("/nonArchives")
    public ResponseEntity<List<Fond>> getNonArchivedFonds() {
        try {
            List<Fond> fonds = fondService.getNonArchivedFonds();
            return ResponseEntity.ok(fonds);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /**
     * Avoir la somme de trésorerie du totalité des fonds par secteurs.
     *
     * @return Liste des résultats de la somme de trésorerie par secteurs.
     */
    @GetMapping("/tresorie")
    public ResponseEntity<List<?>> fondTresorieBySecteur() {
        try {
            List<?> result = fondService.fondTresorieBySecteur();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /**
     * Avoir le nombre total de fonds par statut.
     *
     * @return Liste des résultats du nombre total de fonds par statut.
     */
    @GetMapping("/status")
    public ResponseEntity<List<?>> fondCountByStatus() {
        try {
            List<?> result = fondService.fondCountByStatus();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /**
     * Avoir le nombre total de fonds.
     *
     * @return Le nombre total de fonds.
     */
    @GetMapping("/total")
    public ResponseEntity<Float> fondTotal() {
        try {
            float total = fondService.fondTotal();
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /**
     * Avoir la liste des fonds filtrés et paginés.
     *
     * @param MontantMaxsearchTerm    Terme de recherche pour le montant maximum.
     * @param DateDemarragesearchTerm Terme de recherche pour la date de démarrage.
     * @param DateCloturesearchTerm   Terme de recherche pour la date de clôture.
     * @param MontantMinsearchTerm    Terme de recherche pour le montant minimum.
     * @param StatutsearchTerm        Terme de recherche pour le statut.
     * @param page                    Numéro de page.
     * @param size                    Taille de la page.
     * @return ResponseEntity contenant les résultats des fonds filtrés et paginés.
     */
    @GetMapping("/list")
    public ResponseEntity<?> getFondsFiltered(
            @RequestParam(value = "MontantMaxsearchTerm", required = false) String MontantMaxsearchTerm,
            @RequestParam(value = "DateDemarragesearchTerm", defaultValue = "Sat Mar 18 2023", required = false) String DateDemarragesearchTerm,
            @RequestParam(value = "DateCloturesearchTerm", defaultValue = "Sat Mar 18 2023", required = false) String DateCloturesearchTerm,
            @RequestParam(value = "MontantMinsearchTerm", required = false) String MontantMinsearchTerm,
            @RequestParam(value = "StatutsearchTerm", required = false) String StatutsearchTerm,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<>();

        try {
            Page<Fond> listofFond = fondService.getAllFond(DateDemarragesearchTerm, DateCloturesearchTerm,
                    StatutsearchTerm, MontantMaxsearchTerm, MontantMinsearchTerm, page, size);

            List<FondDTO> listofFondDto = new ArrayList<>();
            for (Fond fond : listofFond) {
                listofFondDto.add(modelMapper.map(fond, FondDTO.class));
            }

            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", listofFondDto);
            jsonResponseMap.put("pageable", listofFond);
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
        } catch (ParseException e) {
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Invalid date format.");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "An error occurred.");
            return new ResponseEntity<>(jsonResponseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
