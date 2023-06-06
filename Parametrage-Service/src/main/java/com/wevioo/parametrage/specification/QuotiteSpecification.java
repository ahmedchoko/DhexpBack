package com.wevioo.parametrage.specification;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.enums.Choix;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
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

            if (!fond.equals("Recherche par Fond") && !fond.isEmpty()) {
                Join<Quotite, Fond> fondJoin = root.join("fond");
                predicates.add(criteriaBuilder.equal(fondJoin.get("nomFond"), fond));
            }

            if (zone != null && !zone.equals("Recherche par Zone")) {
                Join<Quotite, Zone> zoneJoin = root.join("zone");
                predicates.add(criteriaBuilder.equal(zoneJoin.get("nomZone"), zone));
            }

            if (!zonal.equals("Recherche par Zonal") && !zonal.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("zonal"), Choix.valueOf(zonal.toUpperCase())));
            }

            if (!ritic.equals("Recherche par Ritic") && !ritic.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("ritic"), Choix.valueOf(ritic.toUpperCase())));
            }

            if (!nouveauProm.equals("Recherche par Nouveau Promoteur") && !nouveauProm.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("nouvPromo"), Choix.valueOf(nouveauProm.toUpperCase())));
            }

            if (!creditLeasing.equals("Recherche par credit Leasing") && !creditLeasing.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("creditLeas"), Choix.valueOf(creditLeasing.toUpperCase())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
