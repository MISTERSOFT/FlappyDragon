package me.sofianehamadi.flyingbird.models;

/**
 * Created by MISTERSOFT on 26/02/2017.
 */

/**
 * Represent the user information
 */
public class User {
    /**
     * User id
     */
    private Integer id;
    /**
     * Current total coin in the pocket
     */
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

    public User() {}

    /**
     * Add coin in the pocket
     * @param _money
     */
    public void sum(Integer _money) {
        money += _money;
    }

    /**
     * Remove coin in the pocket
     * @param _money
     */
    public void minus(Integer _money) {
        money -= _money;
    }
}
