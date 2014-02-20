/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 17, 2014
 * File Name      : User.java
 */

package frb.name.mongo.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 17, 2014
 */
@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String midName;
    private String email;
    private String address;
    private String phoneNumber;
    private String sex;

    /**
     * Constructor
     */
    public User() {
    }

    /**
     * Constructor
     *
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param midName
     * @param email
     * @param address
     * @param phoneNumber
     * @param sex
     */
    public User(String username, String password, String firstName, String lastName, String midName, String email, String address, String phoneNumber, String sex) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
