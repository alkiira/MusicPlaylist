package org.example;

public class User {
    private int id;
    private final String name;
    private final String surname;
    private final String gender;

    public User(int id, String name, String surname, String gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

}
