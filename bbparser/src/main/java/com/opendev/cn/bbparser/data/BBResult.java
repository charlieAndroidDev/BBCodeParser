package com.opendev.cn.bbparser.data;

import android.support.annotation.Nullable;

/**
 * Created by root on 20/08/17.
 */

public class BBResult {

    private String data;
    private BbType componentType;
    @Nullable
    private String typeArgs;

    public BBResult(String data, BbType componentType, @Nullable String typeArgs) {
        this.data = data;
        this.componentType = componentType;
        this.typeArgs = (typeArgs == null ? null : typeArgs.substring(1, typeArgs.length()));
    }

    public BbType getComponentType() {
        return componentType;
    }

    public void setComponentType(BbType componentType) {
        this.componentType = componentType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Nullable
    public String getTypeArgs() {
        return typeArgs;
    }

    public void setTypeArgs(@Nullable String typeArgs) {
        this.typeArgs = typeArgs.substring(1, typeArgs.length());
    }
}
