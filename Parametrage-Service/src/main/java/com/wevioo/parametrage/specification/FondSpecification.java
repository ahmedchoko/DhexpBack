package com.wevioo.parametrage.specification;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.enums.Fondstatut;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FondSpecification {

    public FondSpecification() {
        super();
    }

    /**
     * Creates a Specification for querying funds based on the provided search criteria.
     *
     * @param montantMin         The minimum amount
     * @param montantMax         The maximum amount
     * @param statut             The status
     * @param dateDemarrageFond  The start date of the fund
     * @param dateClotureFond    The end date of the fund
     * @return The Specification for querying funds
     * @throws ParseException If there is an error parsing the date string
     */
    public static Specification<Fond> getSpec(String montantMin, String montantMax, String statut, String dateDemarrageFond, String dateClotureFond) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd yyyy", Locale.ENGLISH);
        Date startDate = dateFormat.parse(dateDemarrageFond);
        Date endDate = dateFormat.parse(dateClotureFond);

        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (montantMin != null && !montantMin.isEmpty() && !montantMin.equals("Recherche par montantMin")) {
                predicates.add(criteriaBuilder.equal(root.get("montantMin"), montantMin));
            }

            if (montantMax != null && !montantMax.isEmpty() && !montantMax.equals("Recherche par montantMax")) {
                predicates.add(criteriaBuilder.equal(root.get("montantMax"), montantMax));
            }

            if (statut != null && !statut.isEmpty() && !statut.equals("Recherche par statut")) {
                predicates.add(criteriaBuilder.equal(root.get("statut"), Fondstatut.valueOf(statut.toUpperCase())));
            }

            if (dateDemarrageFond != null && !dateDemarrageFond.equals("Sat Mar 18 2023")) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("dateDemarrageFond"), startDate));
            }

            if (dateClotureFond != null && !dateClotureFond.equals("Sat Mar 18 2023")) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("dateClotureFond"), endDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
