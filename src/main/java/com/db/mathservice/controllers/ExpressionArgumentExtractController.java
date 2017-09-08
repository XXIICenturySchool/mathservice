package com.db.mathservice.controllers;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.parsertokens.Token;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExpressionArgumentExtractController {
    @RequestMapping("/parse_expression")
    public List<String> parseException(@RequestParam(value = "template") String expressionString) {

        expressionString = expressionString.replaceAll("<", "(").replaceAll(">", ")");

        List<Token> tokens = new Expression(expressionString).getCopyOfInitialTokens();

        return tokens.stream()
                .filter(t -> t.keyWord.isEmpty())
                .filter(t -> t.looksLike.equals("argument"))
                .map(t -> t.tokenStr)
                .distinct()
                .collect(Collectors.toList());
    }
}
