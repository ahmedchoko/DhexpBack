package com.wevioo.parametrage.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.wevioo.parametrage.entities.Quotite;

@Repository
public interface QuotiteRepository extends JpaRepository<Quotite, Long>, JpaSpecificationExecutor<Quotite>   {

	
}