package com.wevioo.demande.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.demande.entities.Credit;
import com.wevioo.demande.entities.Demande;

public interface CreditRepository  extends JpaRepository<Credit, Long>
{

}
