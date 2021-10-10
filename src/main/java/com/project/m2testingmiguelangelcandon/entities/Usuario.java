package com.project.m2testingmiguelangelcandon.entities;

import javax.persistence.*;


@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;
    private String email;
    private String github;
    private String twitter;
    private Integer age;
    private Integer phone;

    public Usuario() {}

    public Usuario(Long id, String name, String username, String password,
                   String email, String github, String twitter, Integer age, Integer phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.github = github;
        this.twitter = twitter;
        this.age = age;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + "- Nombre: " + name +
                "\n\t username: " + username +
                " ; contraseña: " + password +
                "\n\t email: " + email +
                "\n\t github: " + github +
                "\n\t twitter: " + twitter +
                "\n\t Edad: " + age +
                " años; teléfono: " + phone;
    }
}
