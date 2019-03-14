package com.progressoft.induction;

import java.math.BigDecimal;

public class Money{
    public static final Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));
    public static final Money HALF_DINAR = new Money(BigDecimal.valueOf(0.5));
    public static final Money DINAR = new Money(BigDecimal.valueOf(1.0));
    public static final Money FIVE_DINARS = new Money(BigDecimal.valueOf(5.0));
    public static final Money TEN_DINARS = new Money(BigDecimal.valueOf(10.0));
    public static final Money ZERO = new Money(BigDecimal.valueOf(0.0));

    private BigDecimal value;

    Money(BigDecimal value){
        setValue(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    private void setValue(BigDecimal value) throws IllegalArgumentException{
        if(value.compareTo(BigDecimal.valueOf(0)) < 0)
            throw new IllegalArgumentException("Money Can't be negative");
        this.value = value;
    }

    public Money add(Money money){
        return new Money(this.value.add(money.getValue()));
    }


    public Money subtract(Money money) throws IllegalArgumentException{
        if (this.isLessThan(money))
            throw new IllegalArgumentException("insufficient");
       return new Money(this.value.subtract(money.getValue()));
    }

    public boolean isLessThan(Money money) {
        if (money == null) return false;
        return this.value.compareTo(money.getValue()) < 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Money)
            if (((Money) obj).getValue().equals(this.value))
                return  true;
        return super.equals(obj);
    }
}
