package com.example.myprojcet;

import java.io.Serializable;

public class Students implements Serializable {
    private long id;
    private String name;
    private String last_name;
    private String surname;
    private String date_of_birth;
    private String gr;

    public Students(long id, String name, String l_name, String s_name, String date, String gr) {
        this.id = id;
        this.name = name;
        this.last_name = l_name;
        this.surname = s_name;
        this.date_of_birth = date;
        this.gr = gr;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getGr() {
        return gr;
    }
}
