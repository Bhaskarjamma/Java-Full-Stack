package com.myproject.codes;

import java.util.Stack;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

@Service
public class OpService {
	public double artheOp(char operator,double a,double b) {
		switch(operator) {
		case '+':return b+a;
		case '-':return b-a;
		case '*':return b*a;
		case '/':
			if(b==0)
				throw new UnsupportedOperationException("cannot divide by zero");
			return b/a;
		
		}
		return 0;
	}
	public boolean hasPrecedence(char op1,char op2) {
		if(op2=='('||op2==')')
			return false;
		if((op1=='*'||op1=='/') && (op2 == '+'||op2 == '-'))
			return false;
		return true;
	}
	public Double EvalInfixEx(String expression) {
		expression = expression.replaceAll("\\s", "");
		Stack<Double> values = new Stack<>();
		Stack<Character> operators = new Stack<>();
		int i=0;
		while(i<expression.length()) {
			
			char ch = expression.charAt(i);
			if(Character.isDigit(ch)||expression.charAt(i)=='.') {
				StringBuilder sb = new StringBuilder();
				while(i<expression.length()&& (Character.isDigit(expression.charAt(i))||expression.charAt(i)=='.')) {
					sb.append(expression.charAt(i));
					i++;
			}
			values.push(Double.parseDouble(sb.toString()));
		}
		else if(ch=='+'||ch=='-'||ch=='*'||ch=='/') {
			while(!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
				values.push(artheOp(operators.pop(),values.pop(),values.pop()));
			}
			operators.push(ch);
			i++;
		}
		else if(ch=='(') {
				operators.push(ch);
				i++;
		}
		else if(ch==')') {
				while(operators.peek()!='(') {
					values.push(artheOp(operators.pop(), values.pop(), values.pop()));
				}
				operators.pop();
				i++;
			}
		}
		while(!operators.isEmpty()) {
			values.push(artheOp(operators.pop(), values.pop(), values.pop()));
		}
		return values.pop();
	}
	public String EvaluateExpr(String expression) {
		try {
			return String.valueOf(EvalInfixEx(expression));
		} catch (Exception e) {
			throw new RuntimeException("Invalid expression");
		}
	}
}
