package com.wevioo.parametrage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.parametrage.entities.Activite;


@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {


}