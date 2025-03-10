package com.expense.ExpenseManagement.ExpenseController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
    @RequestMapping("/expense")
    public String returnSomething(){
        return "Hello World";
    }
}
