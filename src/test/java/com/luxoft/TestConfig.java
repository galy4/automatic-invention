package com.luxoft;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:app.properties")
public interface TestConfig extends Config {

    @Key("hire.result")
    String hire();

    @Key("source.data.age")
    @DefaultValue("60")
    int age();
}
