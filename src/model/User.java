package model;

import enums.Gender;

import java.util.HashSet;
import java.util.List;

public class User {
    public User(String name, Gender gender, Integer age, Location location) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.likes = new HashSet<>();
    }

    String name;
    Gender gender;
    Integer age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

    Location location;
    HashSet<User> likes;

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public Location getLocation() {
        return location;
    }


    public HashSet<User> getLikes() {
        return likes;
    }

    public void addLikes(User user) {
        this.likes.add(user);
    }
}
