package me.sofianehamadi.flyingbird.models;

/**
 * Created by MISTERSOFT on 26/02/2017.
 */

public class User {
    private Integer id;
    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public User(Integer id, Integer money) {
        this.id = id;
        this.money = money;
    }

    public User() {}
}
