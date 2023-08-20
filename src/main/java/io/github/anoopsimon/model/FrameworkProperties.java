package io.github.anoopsimon.model;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:framework.properties" })
public  interface FrameworkProperties extends Config {

    @Key("web.app.url")
    String webAppUrl();

    String browser();
}
