package com.project.api_store_java.persistence.repository;

import com.project.api_store_java.persistence.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
}
