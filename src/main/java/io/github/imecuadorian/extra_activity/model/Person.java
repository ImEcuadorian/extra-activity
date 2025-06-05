package io.github.imecuadorian.extra_activity.model;

import io.github.imecuadorian.library.Generic;

public class Person {

    private final Generic<String, String> data;

    public Person(String dni, String name, String email) {
        this.data = new Generic<>(dni, name, email);
    }

    public String getDNI() {
        return this.data.getT1();
    }

    public String getName() {
        return this.data.getT2();
    }

    public String getEmail() {
        return this.data.getS1();
    }

    @Override
    public String toString() {
        return getDNI() + ";" + getName() + ";" + getEmail();
    }


}
