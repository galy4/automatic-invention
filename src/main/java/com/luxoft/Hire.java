package com.luxoft;

public class Hire {

    public String canHire(int age){
        if(age<14)
            return "no";
        else if(age<=16)
            return "half";
        else
            return "yes";
    }
}
