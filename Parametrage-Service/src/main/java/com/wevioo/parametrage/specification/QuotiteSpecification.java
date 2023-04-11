package com.wevioo.parametrage.specification;

import java.text.ParseException;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.enums.Choix;

public class QuotiteSpecification {

	public QuotiteSpecification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static Specification <Quotite> getSpec(String fond ,String zone, String zonal , String ritic,String nouveauProm,String creditLeasing) throws ParseException{ 
	System.out.println(fond);
		return ((root,query,criteriaBuilder)->{
		List<Predicate> predicates = new ArrayList<>();
		 if (!fond.equals("Recherche par Fond") && !fond.isEmpty()) {
			  Join<Quotite, Fond> fondJoin = root.join("fonds");
			  predicates.add( criteriaBuilder.equal(fondJoin.get("nomFond"), fond));
	     }
		 if (!zone.equals(" ") && !zone.isEmpty()) {
			  Join<Quotite, Zone> zoneJoin = root.join("zones");
			  predicates.add( criteriaBuilder.equal(zoneJoin.get("nomZone"), zone));
	     }
		 if (!zonal.equals("Recherche par Zonal") && !zonal.isEmpty()) {
	         predicates.add(criteriaBuilder.equal(root.get("zonal"),  Choix.valueOf(zonal.toUpperCase())));
	     }
	     if (!ritic.equals("Recherche par Ritic") && !ritic.isEmpty()) {
	    	 System.out.println("f");
	         predicates.add(criteriaBuilder.equal(root.get("ritic"), Choix.valueOf(ritic.toUpperCase())));
	     }
	     if (!nouveauProm.equals("Recherche par Nouveau Promoteur") && !nouveauProm.isEmpty()) {
	         predicates.add(criteriaBuilder.equal(root.get("nouvPromo"),Choix.valueOf(nouveauProm.toUpperCase())));
	     }
	     if (!creditLeasing.equals("Recherche par credit Leasing") && !creditLeasing.isEmpty()) {
	         predicates.add(criteriaBuilder.equal(root.get("creditLeas"),Choix.valueOf(creditLeasing.toUpperCase())));
	     }
	     return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		});
	}
}
