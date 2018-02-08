package enterprise.minura.cyb3rodds.model;

import android.app.Application;

/**
 * Created by Utilizador on 05-11-2017.
 */

public class User extends Application {
String username, password, pass, email, firstname, lastname, datecreated, answer, question, country, profileimg, sex;
    int money, cyber, banned, deleted, id;
    boolean dialog;
    boolean charged;
    boolean chargedtrade;
    boolean rooled;

    public User(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCyber() {
        return cyber;
    }

    public void setCyber(int cyber) {
        this.cyber = cyber;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDialog() {
        return dialog;
    }

    public void setDialog(boolean dialog) {
        this.dialog = dialog;
    }

    public boolean isCharged() {
        return charged;
    }

    public void setCharged(boolean charged) {
        this.charged = charged;
    }

    public boolean isChargedtrade() {
        return chargedtrade;
    }

    public void setChargedtrade(boolean chargedtrade) {
        this.chargedtrade = chargedtrade;
    }

    public boolean isRooled() {
        return rooled;
    }

    public void setRooled(boolean rooled) {
        this.rooled = rooled;
    }

    public User(String username, String password, String email, String firstname, String lastname, String datecreated, String answer, String question, String country, String profileimg, String sex, int money, int cyber, int banned, int deleted, int id, boolean dialog, boolean charged, boolean chargedtrade, boolean rooled) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.datecreated = datecreated;
        this.answer = answer;
        this.question = question;
        this.country = country;
        this.profileimg = profileimg;
        this.sex = sex;
        this.money = money;
        this.cyber = cyber;
        this.banned = banned;
        this.deleted = deleted;
        this.id = id;
        this.dialog = dialog;
        this.charged = charged;
        this.chargedtrade = chargedtrade;
        this.rooled = rooled;
    }

    public User() {

}
}

