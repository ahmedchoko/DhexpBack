package com.wevioo.parametrage.specification;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.enums.Fondstatut;

public class PartenaireSpecification {

	public PartenaireSpecification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static Specification <Partenaire> getPartenairewithSpec(String fond ,String modalite ,String montantMin , String montantMax,String statut) throws ParseException{
		return ((root,query,criteriaBuilder)->{
		List<Predicate> predicates = new ArrayList<>();
		 if (montantMin != null && !montantMin.isEmpty() && !montantMin.equals("Recherche par montantMin")) {
			
			 Join<Partenaire, Convention> ConventionJoin = root.join("conventions",JoinType.INNER);
			  Join<Convention, Modalite> ModaliteJoin = ConventionJoin.join("modalite",JoinType.INNER);
			  Join<Modalite, Fond> FondJoin = ModaliteJoin.join("fond",JoinType.INNER);
			  query.distinct(true);
	         predicates.add(criteriaBuilder.equal(FondJoin.get("montantMin"), montantMin));
	     }
		 if (modalite != null && !modalite.isEmpty() && !modalite.equals("Recherche par modalite")) {
			
			 Join<Partenaire, Convention> ConventionJoin = root.join("conventions",JoinType.INNER);
			  Join<Convention, Modalite> ModaliteJoin = ConventionJoin.join("modalite",JoinType.INNER);
			  query.distinct(true); 
			  predicates.add(criteriaBuilder.equal(ModaliteJoin.get("nomCompletModalite"), modalite));
	     }
		 if (montantMax != null && !montantMax.isEmpty() && !montantMax.equals("Recherche par montantMax")) {
			  Join<Partenaire, Convention> ConventionJoin = root.join("conventions",JoinType.INNER);
			  Join<Convention, Modalite> ModaliteJoin = ConventionJoin.join("modalite",JoinType.INNER);
			  Join<Modalite, Fond> FondJoin = ModaliteJoin.join("fond",JoinType.INNER);
			  query.distinct(true);
	         predicates.add(criteriaBuilder.equal(FondJoin.get("montantMax"), montantMax));
	     }
	     if (statut != null && !statut.isEmpty() && !statut.equals("Recherche par statut")) {
	    	 System.out.println("d5al ");
	         predicates.add(criteriaBuilder.equal(root.get("statut"), Fondstatut.valueOf(statut.toUpperCase())));
	     }
	     if (!fond.equals("Recherche par fond") && !fond.isEmpty()) {
			  Join<Partenaire, Convention> ConventionJoin = root.join("conventions",JoinType.INNER);
			  Join<Convention, Modalite> ModaliteJoin = ConventionJoin.join("modalite",JoinType.INNER);
			  Join<Modalite, Fond> FondJoin = ModaliteJoin.join("fond",JoinType.INNER);
			  query.distinct(true);
			  predicates.add( criteriaBuilder.equal(FondJoin.get("nomFond"), fond));
	     }
	     return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		});
	}
}
