package com.luxoft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Random;
import java.util.stream.Stream;


public class HireTest {
    private static Hire hire;

    @BeforeAll
    static void setUp(){
        hire = new Hire();
    }

    @RepeatedTest(value = 3)
    void rtest(){
        int res = new Random().nextInt();
        Assertions.assertTrue(res>0);
    }

    @ParameterizedTest
    @ValueSource(ints = {15,29})
    void htest(int age){
        Assertions.assertEquals("half", hire.canHire(age));
    }

    @ParameterizedTest
    @CsvSource(value = {"15#half", "29#yes", "3#no"}, delimiter = '#')
    void stest(int age, String result){
        Assertions.assertEquals(result, hire.canHire(age));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", delimiter = ';', numLinesToSkip = 2)
    void rtest(int age, String result){
        Assertions.assertEquals(result, hire.canHire(age));
    }

    @ParameterizedTest
    @EnumSource(value = Age.class, names = {"TEEN", "ADULT"}, mode = EnumSource.Mode.EXCLUDE)
    void etest(Age data){
        Assertions.assertEquals(data.getResult(), hire.canHire(data.getAge()));
    }

    @ParameterizedTest
    @EnumSource(value = Age.class, names = {".*T", "C.*"}, mode = EnumSource.Mode.MATCH_ANY)
    void etest1(Age data){
        Assertions.assertEquals(data.getResult(), hire.canHire(data.getAge()));
    }

    @ParameterizedTest
    @MethodSource(value = "getData")
    void mtest(int age, String result){
        Assertions.assertEquals(result, hire.canHire(age));
    }

    public static Stream<Arguments> getData(){
        return Stream.of(
               Arguments.of(5,"no"),
               Arguments.of(14, "half"),
               Arguments.of(25, "yes")
        );
    }
}
