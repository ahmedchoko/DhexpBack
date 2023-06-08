package com.wevioo.parametrage.repository;


import com.wevioo.parametrage.entities.StopLoss;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface StopLossRepository extends PagingAndSortingRepository<StopLoss, Long> {
List<StopLoss> findByNomSL(String nom);
List<StopLoss> findByDateValiditeSL(Date date);
List<StopLoss> findByDateFinSL(Date date);

}
