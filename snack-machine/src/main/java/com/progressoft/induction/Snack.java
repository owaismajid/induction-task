package com.progressoft.induction;

public class Snack {
    private SnackType snackType;
    private int quantity;
    private Money price;

    public Snack(SnackType snackType, Money price) {
        this.snackType = snackType;
        this.price = price;
        setInitialQuantity();
    }


    public void setInitialQuantity(){
        this.quantity = SnackMachine.DEFAULT_QUANTITY;
    }
    public int quantity(){
        return this.quantity;
    }
    public void drop(){
        this.quantity--;
    }
    public Money getPrice() {
        return price;
    }
}
