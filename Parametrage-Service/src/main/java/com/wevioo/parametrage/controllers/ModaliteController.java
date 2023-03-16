package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.services.ModaliteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/modalite")
public class ModaliteController {


    private ModaliteService modaliteService;

    public ModaliteController(ModaliteService modaliteService) {
        super();
        this.modaliteService = modaliteService;
    }

    @GetMapping
    public ResponseEntity<List<Modalite>> getAllModalite(){
        try {
            List<Modalite> modalites = modaliteService.getAllModalite();
            return ResponseEntity.ok().body(modalites) ;
    }
        catch(NotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Modalite> createModalite(@RequestBody ModaliteDto modaliteRequest) {
        try {
            Modalite modalite = modaliteService.createModalite(modaliteRequest);
            return ResponseEntity.ok().body(modalite) ;
        }
        catch(NotFoundException exception) {
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modalite> getModaliteById(@PathVariable(name= "id") Long id){
        try {
            Modalite modalite = modaliteService.getModaliteById(id);
            return ResponseEntity.ok().body(modalite) ;
        }
        catch(NotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Modalite> updateModalite(@PathVariable(name= "id") Long id, @RequestBody ModaliteDto modaliteRequest) {
        try {
            Modalite modalite = modaliteService.updateModalite(id, modaliteRequest);
            return ResponseEntity.ok().body(modalite) ;
        }
        catch(NotFoundException exception){
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Modalite> deleteModalite(@PathVariable(name= "id") Long id) {
        try {
            Modalite modalite = modaliteService.deleteModalite(id);
            return ResponseEntity.ok().body(modalite);
        } catch (NotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
