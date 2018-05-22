package com.teaching.android.miprimeraapp.BaseDatos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {

    //Aquí está para formar la entity que es la base de nuestra base de datos
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String username;


    @ColumnInfo(name = "email")
    private String email ;

    @ColumnInfo(name = "password")
    private String password ;

    @ColumnInfo(name = "age")
    private String age ;

    @ColumnInfo(name = "gender")
    private String gender ;

    //Aquí está todos los gettes y los setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
