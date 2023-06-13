package com.wevioo.parametrage.specification;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.enums.Choix;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class QuotiteSpecification {

    public QuotiteSpecification() {
        super();
    }

    /**
     * Creates a Specification for querying quotites based on the provided search criteria.
     *
     * @param fond          The fund name
     * @param zone          The zone name
     * @param zonal         The zonal choice
     * @param ritic         The ritic choice
     * @param nouveauProm   The nouveauProm choice
     * @param creditLeasing The creditLeasing choice
     * @return The Specification for querying quotites
     * @throws ParseException If there is an error parsing the date string
     */
    public static Specification<Quotite> getSpec(String fond, String zone, String zonal, String ritic, String nouveauProm, String creditLeasing) throws ParseException {
    	return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (fond != null && !fond.equals("Recherche par Fond") && !fond.isEmpty()) {
                Join<Quotite, Fond> fondJoin = root.join("fond", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(fondJoin.get("nomFond"), fond));
            }

            if (zone != null && !zone.equals("Recherche par Zone") && !zone.isEmpty()) {
                System.out.println("bb"+Choix.valueOf(zone.toUpperCase()));  	
                Join<Quotite, Zone> zoneJoin = root.join("zone", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(zoneJoin.get("nomZone"), zone));
            }

            if (zonal != null && !zonal.equals("Recherche par Zonal") && !zonal.isEmpty()) {
                System.out.println("aa"+criteriaBuilder.equal(root.get("zonal"), Choix.valueOf(zonal)));  	
                predicates.add(criteriaBuilder.equal(root.get("zonal"), Choix.valueOf(zonal)));
            }

            if (ritic != null && !ritic.equals("Recherche par Ritic") && !ritic.isEmpty()) {   
                System.out.println("fff"+Choix.valueOf(ritic));  	
                predicates.add(criteriaBuilder.equal(root.get("ritic"), Choix.valueOf(ritic)));
            }
            if (nouveauProm != null && !nouveauProm.equals("Recherche par Nouveau Promoteur") && !nouveauProm.isEmpty()) {
                System.out.println("cc"+Choix.valueOf(nouveauProm));  	
                predicates.add(criteriaBuilder.equal(root.get("nouvPromo"), Choix.valueOf(nouveauProm)));
            }

            if (creditLeasing !=null && !creditLeasing.equals("Recherche par credit Leasing") && !creditLeasing.isEmpty()) {
                System.out.println("dd"+Choix.valueOf(creditLeasing));  	
                predicates.add(criteriaBuilder.equal(root.get("creditLeas"), Choix.valueOf(creditLeasing)));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
