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
public static Specification <Fond> getSpec(String test1 , String test2 , String test3,String test4,String test5) throws ParseException{
  	SimpleDateFormat f = new SimpleDateFormat( "E MMM dd yyyy",Locale.ENGLISH);
		  Date date1re = f.parse( test4);	  
    SimpleDateFormat f1 = new SimpleDateFormat( "E MMM dd yyyy",Locale.ENGLISH);
       Date date2res = f1.parse( test5);	 
	return ((root,query,criteriaBuilder)->{
	List<Predicate> predicates = new ArrayList<>();
	 if (test1 != null && !test1.isEmpty()) {
         predicates.add(criteriaBuilder.equal(root.get("montantMin"), test1));
     }
	 if (test2 != null && !test2.isEmpty()) {
         predicates.add(criteriaBuilder.equal(root.get("montantMax"), test2));
     }
     if (test3 != null && !test3.isEmpty() && !test3.equals("Recherche par statut")) {
    	 System.out.println("d5al ");
         predicates.add(criteriaBuilder.equal(root.get("statut"), Fondstatut.valueOf(test3.toUpperCase())));
     }
     if (test4 != null && !test4.equals("Sat Mar 18 2023")) {
   
         predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("dateDemarrageFond"),date1re));
     }
     if (test5 != null && !test5.equals("Sat Mar 18 2023")) {
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

