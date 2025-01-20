package software.ulpgc.view;

import software.ulpgc.control.Command;
import software.ulpgc.control.ExchangeMoneyCommand;
import software.ulpgc.io.fixers.FixerCurrencyLoader;
import software.ulpgc.io.fixers.FixerExchangeRateLoader;
import software.ulpgc.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;

    public static void main(String[] args) {
        SwingMain main = new SwingMain();
        List<Currency> currencies = new FixerCurrencyLoader().load();
        Command command = new ExchangeMoneyCommand(
                main.moneyDialog().define(currencies),
                main.currencyDialog().define(currencies),
                new FixerExchangeRateLoader(),
                main.moneyDisplay());
        main.addCommand("exchange money", command);
        SwingUtilities.invokeLater(() -> main.setVisible(true));
    }

    public SwingMain() throws HeadlessException {
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(createMoneyDisplay(), BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(createMoneyDialog());
        topPanel.add(createCurrencyDialog());

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(toolbar(), BorderLayout.SOUTH);
        setLookAndFeel();

        pack();
        this.setSize(1100, 200);
        this.setLocationRelativeTo(null);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Component createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(createMoneyDialog());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(createCurrencyDialog());
        return topPanel;
    }


    private Component toolbar() {
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> commands.get("exchange money").execute());

        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        toolbarPanel.add(button);

        return toolbarPanel;
    }

    private Component createMoneyDisplay() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        moneyDisplay = display;
        return display;
    }

    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        currencyDialog = dialog;
        return dialog;
    }

    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        moneyDialog = dialog;
        return dialog;
    }

    private void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    private MoneyDisplay moneyDisplay() {
        return moneyDisplay;
    }

    private CurrencyDialog currencyDialog() {
        return currencyDialog;
    }

    private MoneyDialog moneyDialog() {
        return moneyDialog;
    }
}
