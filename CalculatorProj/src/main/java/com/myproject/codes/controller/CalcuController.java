package com.myproject.codes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.codes.OpService;

@Controller
public class CalcuController {
    @Autowired
    private OpService opService;
    private StringBuilder expression = new StringBuilder();

    @GetMapping("/calculate")
    public String showCalculator(Model model) {
        model.addAttribute("expression", expression.toString());
        model.addAttribute("result", "");
        return "index.html";
    }

    @PostMapping("/calculate")
    public String calculator(@RequestParam(value = "digit", required = false) String digit,
                             @RequestParam(value = "operator", required = false) String operator,
                             @RequestParam(value = "equal", required = false) String equal,
                             @RequestParam(value = "clear", required = false) String clear,
                              Model model) {


        if (digit != null) {
            expression.append(digit);
        }

        if (operator != null) {
            expression.append(" ").append(operator).append(" ");
        }

        if (clear != null) {
            expression.setLength(0);
            model.addAttribute("expression", "");
            model.addAttribute("result", "");
        }

        if (equal != null) {
            try {
                String result = opService.EvaluateExpr(expression.toString());
                model.addAttribute("result", result);
                expression.setLength(0); // Clear the expression after calculation
            } catch (Exception e) {
                model.addAttribute("result", "Error");
            }
        }

        model.addAttribute("expression", expression.toString());
        return "index.html";
    }
}
