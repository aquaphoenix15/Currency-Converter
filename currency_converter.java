import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class currency_converter extends JFrame {

    private JTextField amountTextField;
    private JLabel resultLabel;
    private JRadioButton eurRadioButton, gbpRadioButton, jpyRadioButton, audRadioButton;

    // Exchange rates (as of January 2022)
    private double usdToEurRate = 0.92;
    private double usdToGbpRate = 0.79;
    private double usdToJpyRate = 146.18;
    private double usdToAudRate = 1.51;  // Replace with the actual exchange rate

    public currency_converter() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel to hold components
        JPanel mainPanel = new JPanel(new GridLayout(5, 1));

        // Amount input
        JPanel amountPanel = new JPanel();
        amountPanel.add(new JLabel("Enter the amount in USD: "));
        amountTextField = new JTextField(10);
        amountPanel.add(amountTextField);
        mainPanel.add(amountPanel);

        // Radio buttons for currency selection
        JPanel radioPanel = new JPanel(new GridLayout(1, 4));
        eurRadioButton = new JRadioButton("EUR");
        gbpRadioButton = new JRadioButton("GBP");
        jpyRadioButton = new JRadioButton("JPY");
        audRadioButton = new JRadioButton("AUD");

        ButtonGroup currencyGroup = new ButtonGroup();
        currencyGroup.add(eurRadioButton);
        currencyGroup.add(gbpRadioButton);
        currencyGroup.add(jpyRadioButton);
        currencyGroup.add(audRadioButton);

        radioPanel.add(eurRadioButton);
        radioPanel.add(gbpRadioButton);
        radioPanel.add(jpyRadioButton);
        radioPanel.add(audRadioButton);

        mainPanel.add(radioPanel);

        // Convert button
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());
        mainPanel.add(convertButton);

        // Result display
        resultLabel = new JLabel("Converted amounts will be displayed here");
        mainPanel.add(resultLabel);

        // Add the main panel to the frame
        add(mainPanel);

        setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double amountInUSD;
            try {
                amountInUSD = Double.parseDouble(amountTextField.getText());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number");
                return;
            }

            double convertedAmount;
            String convertedCurrency;
            if (eurRadioButton.isSelected()) {
                convertedAmount = amountInUSD * usdToEurRate;
                convertedCurrency = "EUR";
            } else if (gbpRadioButton.isSelected()) {
                convertedAmount = amountInUSD * usdToGbpRate;
                convertedCurrency = "GBP";
            } else if (jpyRadioButton.isSelected()) {
                convertedAmount = amountInUSD * usdToJpyRate;
                convertedCurrency = "JPY";
            } else {
                convertedAmount = amountInUSD * usdToAudRate;
                convertedCurrency = "AUD";
            }

            resultLabel.setText(String.format("%.2f USD is approximately %.2f %s", amountInUSD, convertedAmount, convertedCurrency));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new currency_converter());
    }
}
