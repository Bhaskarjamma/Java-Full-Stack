//package com.myproject.codes;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class OpServiceTests {
//
//    @Autowired
//    private OpService opService;
//
//    @Test
//    public void testAddition() {
//        assertEquals(5, opService.artheOp('+', 2, 3));
//    }
//
//    @Test
//    public void testDivisionByZero() {
//        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
//            opService.artheOp('/', 5, 0);
//        });
//
//        assertTrue(exception.getMessage().contains("cannot divide by zero"));
//    }
//
//    @Test
//    public void testEvaluation() {
//        assertEquals(7, opService.EvalInfixEx("3 + 4"));
//    }
//}
