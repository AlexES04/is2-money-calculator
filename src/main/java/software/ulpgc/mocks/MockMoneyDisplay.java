package software.ulpgc.mocks;

import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDisplay;


public class MockMoneyDisplay implements MoneyDisplay {
    @Override
    public void show(Money money) {
        System.out.println(money);
    }
}
