package mocks;

import model.Money;
import view.MoneyDisplay;


public class MockMoneyDisplay implements MoneyDisplay {
    @Override
    public void show(Money money) {
        System.out.println(money);
    }
}
