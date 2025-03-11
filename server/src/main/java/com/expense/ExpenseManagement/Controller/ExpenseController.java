package com.expense.ExpenseManagement.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
    @RequestMapping("/expense")
    public String returnSomething(){
        return "Hello World";
    }
}
