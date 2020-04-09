package db;

import annotation.Column;
import annotation.Table;

import java.lang.annotation.Target;

@Table("user")
public class User extends BaseDB {
//    @Column("id")
//    private int id;
    @Column("username")
    private String username;
    @Column("email")
    private String email;
    @Column("telephone")
    private String telephone;
    @Column("age")
    private Integer age;


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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
