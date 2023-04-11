package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.services.ModaliteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/modalite")
public class ModaliteController {

    @Autowired
    private ModaliteService modaliteService;

    public ModaliteController() {

    }
    @Autowired
    private ModelMapper modelMapper;
   /* @GetMapping
    public ResponseEntity < ? > getAllModalite(@RequestParam(defaultValue = "") String firstNameFilter,
                                         @RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "3") int size) {
        Map< String, Object > jsonResponseMap = new LinkedHashMap< String, Object >();
        Page<Modalite> listofFond = modaliteService.getAllModalite(firstNameFilter,page,size);
        // System.out.println(listofFond);
        List <ModaliteDto> listofFondDto = new ArrayList< ModaliteDto >();
        if (!listofFond.isEmpty()) {
            for (Modalite fond: listofFond ) {
                listofFondDto.add(modelMapper.map(fond, ModaliteDto.class));
            }
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", listofFondDto);
            jsonResponseMap.put("pageable", listofFond);
            return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
        } else {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Data is not found");
            return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
        }
    }
*/
    @GetMapping
    public ResponseEntity<Page<Modalite>> getAllModalite(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size){

        try { Page<Modalite> modalites = modaliteService.getAllModalite(page, size);
            return ResponseEntity.ok().body(modalites) ;   }
        catch(NotFoundException exception) { return ResponseEntity.notFound().build();  }    }

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
