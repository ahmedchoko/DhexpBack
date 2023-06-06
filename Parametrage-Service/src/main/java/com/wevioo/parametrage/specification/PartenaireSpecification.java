package com.wevioo.parametrage.specification;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.enums.Fondstatut;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PartenaireSpecification {

    public PartenaireSpecification() {
        super();
    }

    /**
     * Creates a Specification for querying partners based on the provided search criteria.
     *
     * @param fond        The fund name
     * @param modalite    The modality name
     * @param montantMin  The minimum amount
     * @param montantMax  The maximum amount
     * @param statut      The status
     * @return The Specification for querying partners
     * @throws ParseException If there is an error parsing the date string
     */
    public static Specification<Partenaire> getPartenaireWithSpec(String fond, String modalite, String montantMin, String montantMax, String statut) throws ParseException {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (montantMin != null && !montantMin.isEmpty() && !montantMin.equals("Recherche par montantMin")) {
                Join<Partenaire, Convention> conventionJoin = root.join("conventions", JoinType.INNER);
                Join<Convention, Modalite> modaliteJoin = conventionJoin.join("modalite", JoinType.INNER);
                Join<Modalite, Fond> fondJoin = modaliteJoin.join("fond", JoinType.INNER);
                query.distinct(true);
                predicates.add(criteriaBuilder.equal(fondJoin.get("montantMin"), montantMin));
            }

            if (modalite != null && !modalite.isEmpty() && !modalite.equals("Recherche par modalite")) {
                Join<Partenaire, Convention> conventionJoin = root.join("conventions", JoinType.INNER);
                Join<Convention, Modalite> modaliteJoin = conventionJoin.join("modalite", JoinType.INNER);
                query.distinct(true);
                predicates.add(criteriaBuilder.equal(modaliteJoin.get("nomCompletModalite"), modalite));
            }

            if (montantMax != null && !montantMax.isEmpty() && !montantMax.equals("Recherche par montantMax")) {
                Join<Partenaire, Convention> conventionJoin = root.join("conventions", JoinType.INNER);
                Join<Convention, Modalite> modaliteJoin = conventionJoin.join("modalite", JoinType.INNER);
                Join<Modalite, Fond> fondJoin = modaliteJoin.join("fond", JoinType.INNER);
                query.distinct(true);
                predicates.add(criteriaBuilder.equal(fondJoin.get("montantMax"), montantMax));
            }

            if (statut != null && !statut.isEmpty() && !statut.equals("Recherche par statut")) {
                predicates.add(criteriaBuilder.equal(root.get("statut"), Fondstatut.valueOf(statut.toUpperCase())));
            }

            if (!fond.equals("Recherche par fond") && !fond.isEmpty()) {
                Join<Partenaire, Convention> conventionJoin = root.join("conventions", JoinType.INNER);
                Join<Convention, Modalite> modaliteJoin = conventionJoin.join("modalite", JoinType.INNER);
                Join<Modalite, Fond> fondJoin = modaliteJoin.join("fond", JoinType.INNER);
                query.distinct(true);
                predicates.add(criteriaBuilder.equal(fondJoin.get("nomFond"), fond));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
