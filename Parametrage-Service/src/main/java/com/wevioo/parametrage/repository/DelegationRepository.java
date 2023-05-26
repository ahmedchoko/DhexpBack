package com.wevioo.parametrage.repository;


import com.wevioo.parametrage.entities.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DelegationRepository extends JpaRepository<Delegation, Long> {

}
