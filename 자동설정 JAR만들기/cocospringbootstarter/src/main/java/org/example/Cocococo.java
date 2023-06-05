package org.example;

public class Cocococo {
    String name;
    String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cocococo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
