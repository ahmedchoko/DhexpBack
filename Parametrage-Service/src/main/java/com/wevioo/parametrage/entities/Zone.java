package com.wevioo.parametrage.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.parametrage.enums.Choix;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Zone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idZone;
	private String nomZone ;
	private String nomArabeZone ;
	@JsonIgnore()
	@ManyToOne()
	private Quotite quotitee ; 
}
