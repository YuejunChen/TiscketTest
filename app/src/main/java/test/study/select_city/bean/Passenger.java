package test.study.select_city.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Mr.Chen on 2017/8/27.
 */
public class Passenger extends DataSupport {
    private int id;
    private String passenger_name;
    private String passenger_num;

    public Passenger(String passenger_name, String passenger_num) {
        this.passenger_name = passenger_name;
        this.passenger_num = passenger_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getPassenger_num() {
        return passenger_num;
    }

    public void setPassenger_num(String passenger_num) {
        this.passenger_num = passenger_num;
    }
}
