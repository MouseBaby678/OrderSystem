package model;

public class Business {
    String username;
    String password;
    String restaurant_name;

    public Business(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
}
