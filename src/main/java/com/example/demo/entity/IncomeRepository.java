package com.example.demo.entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer>, JpaSpecificationExecutor<Income> {
    @Query(value = "SELECT * FROM income", nativeQuery = true)
    List<Income> findAllIncome();
}

