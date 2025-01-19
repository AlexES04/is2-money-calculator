package software.ulpgc.view;

import software.ulpgc.model.Currency;
import software.ulpgc.model.Money;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private CurrencyDialog currencyDialog;

    public SwingMoneyDialog() {
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        add(createAmountPanel(), BorderLayout.WEST);
        add(createCurrencyPanel(), BorderLayout.EAST);
    }

    private JPanel createAmountPanel() {
        JPanel amountPanel = new JPanel(new FlowLayout());
        amountPanel.add(new JLabel("Amount: "));
        amountPanel.add(createAmountField());
        return amountPanel;
    }

    private JTextField createAmountField() {
        JTextField textField = new JTextField(10);
        this.amountField = textField;
        return textField;
    }

    private JPanel createCurrencyPanel() {
        JPanel currencyPanel = new JPanel(new FlowLayout());
        currencyPanel.add(new JLabel("Currency: "));
        currencyPanel.add((Component) createCurrencyDialog());
        return currencyPanel;
    }

    private CurrencyDialog createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    @Override
    public MoneyDialog define(List<Currency> currencies) {
        currencyDialog.define(currencies);
        return this;
    }

    @Override
    public Money get() {
        return new Money(toLong(amountField.getText()), currencyDialog.get());
    }

    private long toLong(String text) {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
