package test.study.select_city.bean;

/**
 * Created by Mr.Chen on 2017/8/28.
 */
public class Ticket {
    private String TrainNum;
    private String TrainTime;
    private String TrainStation;
    private String TrainPassenger;

    public Ticket(String trainNum, String trainTime, String trainStation, String trainPassenger) {
        TrainNum = trainNum;
        TrainTime = trainTime;
        TrainStation = trainStation;
        TrainPassenger = trainPassenger;
    }

    public String getTrainNum() {
        return TrainNum;
    }

    public String getTrainTime() {
        return TrainTime;
    }

    public String getTrainStation() {
        return TrainStation;
    }

    public String getTrainPassenger() {
        return TrainPassenger;
    }
}
