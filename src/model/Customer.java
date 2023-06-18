package model;

public class Customer {
    int c_id;
    int t_id;
    String phone_num;

    public Customer(int t_id, String phone_num) {
        this.t_id = t_id;
        this.phone_num = phone_num;
    }

    public Customer(int c_id, int t_id, String phone_num) {
        this.c_id = c_id;
        this.t_id = t_id;
        this.phone_num = phone_num;
    }

    public Customer() {

    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
}
