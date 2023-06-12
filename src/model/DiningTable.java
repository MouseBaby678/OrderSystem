package model;

public class DiningTable {
    int t_id;
    int diners_num;
    int status;

    public DiningTable(int t_id, int diners_num, int status) {
        this.t_id = t_id;
        this.diners_num = diners_num;
        this.status = status;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getDiners_num() {
        return diners_num;
    }

    public void setDiners_num(int diners_num) {
        this.diners_num = diners_num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
