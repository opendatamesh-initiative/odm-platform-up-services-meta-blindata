package org.opendatamesh.platform.up.metaservice.blindata.entities;

import java.util.Objects;

public class AdditionalProperty {
    private String name;
    private String value;

    public AdditionalProperty() {
    }

    public AdditionalProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "('" + name + "'='" + value + "')";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdditionalProperty)) return false;
        AdditionalProperty that = (AdditionalProperty) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getValue());
    }
}

