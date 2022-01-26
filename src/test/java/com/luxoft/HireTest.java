package com.luxoft;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Random;
import java.util.stream.Stream;

import static com.luxoft.HasSpaceInside.hasSpaceInside;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class HireTest {
    private static Hire hire;

    @BeforeAll
    static void setUp(){
        hire = new Hire();
    }

    @RepeatedTest(value = 3)
    void rtest(){
        Assumptions.assumeFalse(OS.LINUX.isCurrentOs());
        int res = new Random().nextInt();
        Assertions.assertTrue(res>0);
    }

    @ParameterizedTest
    @ValueSource(ints = {15,29})
    @Tag("tag2")
    void htest(int age){
        Assertions.assertAll(
                () -> Assertions.assertEquals("half", hire.canHire(age)),
                () -> Assertions.assertEquals(7,7),
                () -> Assertions.assertEquals(15, age)
        );

    }

    @ParameterizedTest
    @CsvSource(value = {"15#half", "29#yes", "3#no"}, delimiter = '#')
    @Tag("tag2")
    void stest(int age, String result){
        Assumptions.assumingThat(OS.WINDOWS.isCurrentOs(),
                () -> Assertions.assertEquals(result, hire.canHire(age))
        );
        System.out.println("11111");
        assertThat(hire.canHire(age), equalTo(result));
        assertThat("space invaders", hasSpaceInside());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", delimiter = ';', numLinesToSkip = 2)
    @Tag("tag1")
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

    @Test
    void aeonTest(){
        TestConfig testConfig = ConfigFactory.create(TestConfig.class);
        assertThat(testConfig.hire(), equalTo(hire.canHire(testConfig.age())));
    }
}
