package com.nightstalker.account;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Account.
 */
public class Account implements Serializable {
    private String email;
    private String password;
    private LocalDate dob;

    public Account(String email, String password, LocalDate dob)
    {
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(email, account.email) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
