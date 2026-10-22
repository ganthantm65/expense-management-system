package com.expense.ExpenseManagement.Repository;

import com.expense.ExpenseManagement.Model.TaxConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxConfigurationRepo
        extends JpaRepository<TaxConfiguration,Long> {

    Optional<TaxConfiguration> findByCategoryIgnoreCase(String category);

}
