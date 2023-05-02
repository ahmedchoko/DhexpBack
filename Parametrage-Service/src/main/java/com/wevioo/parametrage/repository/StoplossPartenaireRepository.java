package com.wevioo.parametrage.repository;

import com.wevioo.parametrage.common.StoplossPartenaireKey;
import com.wevioo.parametrage.entities.StopLoss;
import com.wevioo.parametrage.entities.StoplossPartenaire;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StoplossPartenaireRepository extends PagingAndSortingRepository<StoplossPartenaire, StoplossPartenaireKey> {
}
