package com.wevioo.parametrage.repository;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.StopLoss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StopLossRepository extends PagingAndSortingRepository<StopLoss, Long> {

}
