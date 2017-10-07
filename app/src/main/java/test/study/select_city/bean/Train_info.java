package test.study.select_city.bean;

import java.io.Serializable;

/**
 * Created by Mr.Chen on 2017/8/19.
 */
public class Train_info implements Serializable{
    private String trainNumber;
    private String departureDate;
    private String startStation;
    private String endStation;
    private String departureTime;
    private String arriveTime;
    private String trainSeat;

    public Train_info(String trainNumber, String departureDate, String startStation,
                      String endStation, String departureTime, String arriveTime) {
        //车次，出发日期，起点，终点，出发时间，到达时间，席位
        this.trainNumber = trainNumber;
        this.departureDate = departureDate;
        this.startStation = startStation;
        this.endStation = endStation;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getDepartureDate() {return departureDate;}

    public String getStartStation() {
        return startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setTrainNumber(String trainNumber) {this.trainNumber = trainNumber;}

    public void setArriveTime(String arriveTime) {this.arriveTime = arriveTime;}

    public void setDepartureTime(String departureTime) {this.departureTime = departureTime;}

    public void setEndStation(String endStation) {this.endStation = endStation;}

    public void setStartStation(String startStation) {this.startStation = startStation;}

    public void setDepartureDate(String departureDate) {this.departureDate = departureDate;}
}
