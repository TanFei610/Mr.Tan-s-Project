package com.microtest.exception_handle_project.entity;

public class ExceptionHandleList {

    @Override
    public String toString() {
        return "ExceptionHandleList{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", customer='" + customer + '\'' +
                ", projectNumber='" + projectNumber + '\'' +
                ", projectName='" + projectName + '\'' +
                ", stage='" + stage + '\'' +
                ", number=" + number +
                ", charge='" + charge + '\'' +
                ", exceptionRank=" + exceptionRank +
                ", exceptionClassify=" + exceptionClassify +
                ", exception='" + exception + '\'' +
                ", reason='" + reason + '\'' +
                ", solve='" + solve + '\'' +
                ", DRI='" + DRI + '\'' +
                ", state=" + state +
                ", remarks='" + remarks + '\'' +
                ", remarks='" + realPath + '\'' +
                '}';
    }

    public ExceptionHandleList(int id,
                               String date,
                               String customer,
                               String projectNumber,
                               String projectName,
                               String stage, int number,
                               String charge,
                               int exceptionRank,
                               int exceptionClassify,
                               String exception,
                               String reason,
                               String solve,
                               String DRI,
                               int state,
                               String remarks,
                               String realPath
    ) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.stage = stage;
        this.number = number;
        this.charge = charge;
        this.exceptionRank = exceptionRank;
        this.exceptionClassify = exceptionClassify;
        this.exception = exception;
        this.reason = reason;
        this.solve = solve;
        this.DRI = DRI;
        this.state = state;
        this.remarks = remarks;
        this.realPath = realPath;
    }

    public ExceptionHandleList() {

    }

    private int id;
    private String date;
    private String customer;
    private String projectNumber;
    private String projectName;
    private String stage;
    private int number;
    private String charge;
    private int exceptionRank;
    private int exceptionClassify;
    private String exception;
    private String reason;
    private String solve;
    private String DRI;
    private int state;
    private String remarks;
    private String realPath;

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public int getExceptionRank() {
        return exceptionRank;
    }

    public void setExceptionRank(int exceptionRank) {
        this.exceptionRank = exceptionRank;
    }

    public int getExceptionClassify() {
        return exceptionClassify;
    }

    public void setExceptionClassify(int exceptionClassify) {
        this.exceptionClassify = exceptionClassify;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    public String getDRI() {
        return DRI;
    }

    public void setDRI(String DRI) {
        this.DRI = DRI;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
