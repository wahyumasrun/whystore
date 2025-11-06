/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

/**
 *
 * @author MA ATTAUHID
 */
public class Session {
    private static String username, email, fullname, status;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        Session.fullname = fullname;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Session.status = status;
    }

    
}
