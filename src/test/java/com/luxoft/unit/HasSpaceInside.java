package com.luxoft.unit;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.SubstringMatcher;

public class HasSpaceInside extends SubstringMatcher {


    protected HasSpaceInside(String substring) {
        super(substring);
    }

    @Factory
    public static Matcher<String> hasSpaceInside(){
        return new HasSpaceInside("space");
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return s.contains(substring);
    }

    @Override
    protected String relationship() {
        return null;
    }
}
