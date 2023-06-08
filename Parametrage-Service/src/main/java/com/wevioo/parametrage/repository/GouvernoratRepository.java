package com.wevioo.parametrage.repository;


import com.wevioo.parametrage.entities.Gouvernorat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface GouvernoratRepository extends JpaRepository<Gouvernorat, Long> {
List<Gouvernorat> findAll();
}