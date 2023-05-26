package com.wevioo.parametrage.entities;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ParametrageEvent {
	private String message ; 
	private String status ;
	private List<Fond> fond ;
	private List<Partenaire> Partenaire ; 
	private List<StoplossPartenaire> stpparteanire;
}
