package com.wevioo.parametrage.repository;

import com.wevioo.parametrage.common.StoplossPartenaireKey;

import com.wevioo.parametrage.entities.StoplossPartenaire;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoplossPartenaireRepository extends JpaRepository<StoplossPartenaire, StoplossPartenaireKey> {

}
