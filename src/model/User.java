package model;

import java.io.Serializable;


public class User implements Serializable {
    private String username;
    private char[] password;

    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User){
            String objName = ((User) obj).getUsername();
            return this.username.equals(objName);
        }
        return false;
    }
}
