package org.vicykie.myapp.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.vicykie.myapp.json.Serializer2Ordinal;

/**
 * Created by vicykie on 2016/5/5.
 */
@JsonSerialize(using = Serializer2Ordinal.class)
public enum EntityStatus {
    ENABLE("启用"), DISABLED("禁用"), DELETED("删除");
    private String description;

    EntityStatus(String description) {
        this.description = description;
    }

    public static int enable() {
        return ENABLE.ordinal();
    }

    public static int disabled() {
        return DISABLED.ordinal();
    }

    public static int deleted() {
        return DELETED.ordinal();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
