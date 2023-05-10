package com.wevioo.parametrage.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.enums.Fondstatut;

public class FondSpecification {

    public FondSpecification() {
 		super();
 	
 	}
public static Specification <Fond> getSpec(String montantMin , String montantMax , String statut,String dateDemarrageFond ,String dateClotureFond) throws ParseException{
  	SimpleDateFormat f = new SimpleDateFormat( "E MMM dd yyyy",Locale.ENGLISH);
		  Date date1re = f.parse( dateDemarrageFond);	  
    SimpleDateFormat f1 = new SimpleDateFormat( "E MMM dd yyyy",Locale.ENGLISH);
       Date date2res = f1.parse( dateClotureFond);	 
	return ((root,query,criteriaBuilder)->{
	List<Predicate> predicates = new ArrayList<>();
	 if (montantMin != null && !montantMin.isEmpty() && !montantMin.equals("Recherche par montantMin")) {
         predicates.add(criteriaBuilder.equal(root.get("montantMin"), montantMin));
     }
	 if (montantMax != null && !montantMax.isEmpty() && !montantMax.equals("Recherche par montantMax")) {
         predicates.add(criteriaBuilder.equal(root.get("montantMax"), montantMax));
     }
     if (statut != null && !statut.isEmpty() && !statut.equals("Recherche par statut")) {
    	 System.out.println("d5al ");
         predicates.add(criteriaBuilder.equal(root.get("statut"), Fondstatut.valueOf(statut.toUpperCase())));
     }
     if (dateDemarrageFond != null && !dateDemarrageFond.equals("Sat Mar 18 2023")) {
   
         predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("dateDemarrageFond"),date1re));
     }
     if (dateClotureFond != null && !dateClotureFond.equals("Sat Mar 18 2023")) {
    	 predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date> get("dateClotureFond"),date2res));
     }
     return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	});
}
/*
public static Specification <Fond> getSpecArch() throws ParseException{
 
	return ((root,query,criteriaBuilder)->{
	List<Predicate> predicates = new ArrayList<>();
     predicates.add(criteriaBuilder.equal(root.get("archive"),false));
     return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	});*/
}

