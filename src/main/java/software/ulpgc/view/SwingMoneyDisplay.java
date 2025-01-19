package software.ulpgc.view;

import software.ulpgc.model.Money;

import javax.swing.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {
    @Override
    public void show(Money money) {
        setText(money.toString());
    }
}
