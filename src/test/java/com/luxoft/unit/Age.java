package com.luxoft.unit;

import lombok.Getter;

@Getter
public enum Age {

    CHILD(5, "no"),
    TEEN(15, "half"),
    ADULT(20, "yes");

    private int age;
    private String result;

    Age(int age, String result) {
        this.age = age;
        this.result = result;
    }
}
