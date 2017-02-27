package me.sofianehamadi.flyingbird.models;

/**
 * Created by MISTERSOFT on 26/02/2017.
 */

public class Bird {
    private Integer id;
    /**
     * Cool bird name
     */
    private String name;
    /**
     * Bird price
     */
    private Integer price;
    /**
     * Bird type
     */
    private BirdTypeEnum birdType;
    /**
     * Ressource ID of the image to display in the shop
     */
    private String resourceName;
    /**
     * If the bird has been bought so we can equip it
     */
    private Boolean bought;
    /**
     * Is the bird is equiped by the player
     */
    private Boolean equiped;
    /**
     * Value that help to sort bird in the listview
     */
//    private int order;

    public Bird(Integer id, String name, Integer price, BirdTypeEnum birdType, String resourceName, Boolean bought, Boolean equiped) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.birdType = birdType;
        this.resourceName = resourceName;
        this.bought = bought;
        this.equiped = equiped;
    }

    public Bird() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String ressourceName) {
        this.resourceName = ressourceName;
    }

    public Boolean isBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

    public Boolean isEquiped() {
        return equiped;
    }

    public void setEquiped(Boolean equiped) {
        this.equiped = equiped;
    }

    public BirdTypeEnum getBirdType() {
        return birdType;
    }

    public void setBirdType(BirdTypeEnum birdType) {
        this.birdType = birdType;
    }

//    public int getOrder() {
//        return order;
//    }
//
//    public void setOrder(int order) {
//        this.order = order;
//    }
}
