package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dani on 2017-02-23.
 */
public class TransactionIdentifierDTO {

    private Long userId;
    private Long transactionId;
    private String username;
    private String password;
    private String date;
    private String description;
    private int sum;

    @JsonCreator
    public TransactionIdentifierDTO(@JsonProperty("transactionId") Long transactionId,@JsonProperty("userId") Long userId,@JsonProperty("date") String date, @JsonProperty("description") String description, @JsonProperty("sum") int sum, @JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.date = date;
        this.description = description;
        this.sum = sum;
        this.username = username;
        this.password = password;
    }

    public Long getTransactionId() { return transactionId; }

    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}


