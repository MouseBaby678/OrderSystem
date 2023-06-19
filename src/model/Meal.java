package model;

import java.math.BigDecimal;

public class Meal {
    int m_id;
    String meal_name;
    BigDecimal price;

    public Meal(String meal_name, BigDecimal price) {
        this.meal_name = meal_name;
        this.price = price;
    }

    public Meal() {

    }

    public Meal(String meal_name) {
        this.meal_name = meal_name;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMealName() {
        return meal_name;
    }
}
