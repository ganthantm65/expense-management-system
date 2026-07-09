package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Expense;
import com.expense.ExpenseManagement.Model.TaxConfiguration;
import com.expense.ExpenseManagement.Repository.TaxConfigurationRepo;
import com.expense.ExpenseManagement.dto.ExpenseTaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxService {

    @Autowired
    private TaxConfigurationRepo taxRepo;

    public ExpenseTaxResponse calculateTax(Expense expense){

        TaxConfiguration tax = taxRepo.findByCategoryIgnoreCase(
                        expense.getCategory())
                .orElseThrow(() ->
                        new RuntimeException("Tax configuration not found"));

        BigDecimal amount = expense.getAmount();

        BigDecimal gst =
                amount.multiply(tax.getGstRate())
                        .divide(BigDecimal.valueOf(100));

        BigDecimal cgst =
                gst.divide(BigDecimal.valueOf(2));

        BigDecimal sgst =
                gst.divide(BigDecimal.valueOf(2));

        BigDecimal tds =
                amount.multiply(tax.getTdsRate())
                        .divide(BigDecimal.valueOf(100));

        BigDecimal deductible =
                amount.multiply(tax.getDeductibleRate())
                        .divide(BigDecimal.valueOf(100));

        ExpenseTaxResponse response =
                new ExpenseTaxResponse();

        response.setGstRate(tax.getGstRate());
        response.setGstAmount(gst);
        response.setCgst(cgst);
        response.setSgst(sgst);
        response.setIgst(BigDecimal.ZERO);
        response.setTdsRate(tax.getTdsRate());
        response.setTdsAmount(tds);
        response.setDeductibleAmount(deductible);
        response.setRemarks("Estimated tax calculation");

        return response;
    }

}
