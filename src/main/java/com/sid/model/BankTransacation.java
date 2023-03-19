package com.sid.model;

public class BankTransacation {
    private int id;
    private String name;
    private String type;

    private String date;

    private double amount;

    public BankTransacation() {
    }

    public BankTransacation(int id, String name, String type, String date, double amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
