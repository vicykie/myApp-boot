package org.vicykie.myapp.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by vicykie on 2016/6/24.
 */
@Component
@ConfigurationProperties(prefix = "test")
public class Test {
    private String name;
    private String p;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
}
