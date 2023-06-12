package model;

import java.math.BigDecimal;

public class Order {
    int o_id;
    int t_id;
    int c_id;
    String dish_name;
    BigDecimal cost_money;

    public Order(int o_id, int t_id, int c_id, String dish_name, BigDecimal cost_money) {
        this.o_id = o_id;
        this.t_id = t_id;
        this.c_id = c_id;
        this.dish_name = dish_name;
        this.cost_money = cost_money;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public BigDecimal getCost_money() {
        return cost_money;
    }

    public void setCost_money(BigDecimal cost_money) {
        this.cost_money = cost_money;
    }
}
