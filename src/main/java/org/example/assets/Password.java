package org.example.assets;

public class Password {
    private String password;
    private String note;
    public Password (String password, String note) {
        this.password = password;
        this.note = note;
    }

    public String getPassword() {
        return password;
    }

    public String getNote() {
        return note;
    }
}
