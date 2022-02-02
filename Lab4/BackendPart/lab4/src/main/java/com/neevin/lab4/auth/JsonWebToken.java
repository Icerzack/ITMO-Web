package com.neevin.lab4.auth;

import java.util.Objects;

public class JsonWebToken {
    private String login;
    private String jwtToken;

    public JsonWebToken(String login, String jwtToken) {
        this.login = login;
        this.jwtToken = jwtToken;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "JsonWebToken{" +
                "login='" + login + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonWebToken that = (JsonWebToken) o;
        return Objects.equals(login, that.login) && Objects.equals(jwtToken, that.jwtToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, jwtToken);
    }
}
