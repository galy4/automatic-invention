package com.luxoft;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("this is first test class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    private static int lessonNumber;

    @AfterAll
    void tearDown(){
        lessonNumber = -1;
    }

    @BeforeAll
    void setUp(){
        lessonNumber = 2;
    }

    @BeforeEach
    void testEach(){
        System.out.println("1111111");
    }

    @AfterEach
    void testEach2(){
        System.out.println("2222222");
    }

    @Test
    @DisplayName("this is first test")
    @Order(5)
    void jhgjhgjg(){
        System.out.println("first Test - " + lessonNumber);
    }

    @Test
    @Disabled
    void secondTest(){
        System.out.println("second Test - " + lessonNumber);
    }

    @Test
    @Order(15)
    @EnabledOnOs(OS.LINUX)
    void thirdTest(){
        System.out.println("3 Test - " + lessonNumber);
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "PATH", matches = ".*snap/bin")
    void nextTest(){
        int result = 5/0;
        System.out.println("4 Test - " + lessonNumber);
    }

}
