package view;

import model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private JComboBox<Currency> currencySelector;

    public SwingCurrencyDialog() {
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(createCurrencySelector());
    }

    private JComboBox<Currency> createCurrencySelector() {
        JComboBox<Currency> selector = new JComboBox<>();
        this.currencySelector = selector;
        return selector;
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        currencySelector.setModel(new DefaultComboBoxModel<>(currencies.toArray(new Currency[0])));
        return this;
    }

    @Override
    public Currency get() {
        return currencySelector.getItemAt(currencySelector.getSelectedIndex());
    }
}
