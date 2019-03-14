package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SnackMachine {
    public static final int DEFAULT_QUANTITY = 3;
    private Money moneyInside,
            moneyInTransaction,
            change;

    private Map<SnackType, Snack> snackMap;

    private final Money[] acceptedUnits = {
            Money.ZERO,
            Money.QUARTER_DINAR,
            Money.HALF_DINAR,
            Money.DINAR,
            Money.FIVE_DINARS,
            Money.TEN_DINARS
    };

    public SnackMachine() {
        this.moneyInside = Money.ZERO;
        this.moneyInTransaction = Money.ZERO;
        this.change = Money.ZERO;
        this.snackMap = new HashMap<>();
        fillSnacks();
    }

    private void fillSnacks() {
        this.snackMap.clear();

        this.snackMap.put(
                SnackType.CHOCOLATE,
                new Snack(SnackType.CHOCOLATE, new Money(BigDecimal.valueOf(2)))
        );
        this.snackMap.put(
                SnackType.CHIPS,
                new Snack(SnackType.CHIPS, Money.DINAR)
        );
        this.snackMap.put(
                SnackType.CHEWING_GUM,
                new Snack(SnackType.CHEWING_GUM, Money.HALF_DINAR)
        );

    }


    public void insertMoney(Money money) throws IllegalArgumentException{
        // If ZERO OR NULL -> Fail
        if ((money == null) || (money.equals(Money.ZERO)))
            throw new IllegalArgumentException("Money can't be NULL nor Zero");

        boolean flag = false;
        for (Money m : acceptedUnits)
            if (money.equals(m))
                flag = true;
        // if not Defied -> Fail
        if (!flag) throw new IllegalArgumentException("Not Accepted money unit");
        else
        // Else Add to transaction
        moneyInTransaction = moneyInTransaction.add(money);
    }


    public Money moneyInside() {
        return this.moneyInside;
    }


    public Money moneyInTransaction() {
        return this.moneyInTransaction;
    }

    public Money buySnack(SnackType snackType) throws IllegalStateException{
        Snack snack = snackMap.get(snackType);
        Money snackPrice = snack.getPrice();

        if (snack.quantity() <= 0) throw new IllegalStateException("Out of stock");

        if(moneyInTransaction().isLessThan(snackPrice))
            throw new IllegalStateException("no sufficient balance");

        change = moneyInTransaction().subtract(snackPrice);
        moneyInTransaction = Money.ZERO;
        moneyInside = moneyInside().add(snackPrice);
        snackMap.get(snackType).drop();
        return this.change;

    }

    public Snack chewingGums() {
        return snackMap.get(SnackType.CHEWING_GUM);
    }

    public Snack chips() {
        return snackMap.get(SnackType.CHIPS);
    }

    public Snack chocolates() {
        return snackMap.get(SnackType.CHOCOLATE);

    }



}
