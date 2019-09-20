package com.example.invo.myplanttest.domain;

import java.util.List;

/**
 * Created by INvo on 2019-07-08.
 */
public class PlantResponse {
    private String log_id;
    private List<Result> result;
    private Result DetectorResult;

    public Result getDetectorResult() {
        return DetectorResult;
    }

    public void setDetectorResult(Result detectorResult) {
        DetectorResult = detectorResult;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public List<com.example.invo.myplanttest.domain.Result> getResult() {
        return result;
    }

    public void setResult(List<com.example.invo.myplanttest.domain.Result> result) {
        this.result = result;
    }
}
