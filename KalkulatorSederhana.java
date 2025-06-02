package kalkulatorsederhana;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KalkulatorSederhana extends JFrame implements ActionListener {
    private JTextField display;
    private double angka1 = 0, angka2 = 0, hasil = 0;
    private String operator = "";
    private boolean startNewInput = true;

    public KalkulatorSederhana() {
        setTitle("Kalkulator");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tampilan layar
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.BOLD, 36));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.NORTH);

        // Tombol
        String[] tombol = {
            "C", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "="
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));
        panel.setBackground(Color.WHITE);

        for (String text : tombol) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 24));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) {
            if (startNewInput) {
                display.setText(input);
                startNewInput = false;
            } else {
                display.setText(display.getText().equals("0") ? input : display.getText() + input);
            }
        } else if (input.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (input.equals("+/-")) {
            double value = Double.parseDouble(display.getText());
            display.setText(String.valueOf(-value));
        } else if (input.equals("C")) {
            display.setText("0");
            angka1 = angka2 = hasil = 0;
            operator = "";
            startNewInput = true;
        } else if (input.equals("%")) {
            double value = Double.parseDouble(display.getText());
            display.setText(String.valueOf(value / 100));
        } else if (input.equals("=")) {
            angka2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+": hasil = angka1 + angka2; break;
                case "-": hasil = angka1 - angka2; break;
                case "*": hasil = angka1 * angka2; break;
                case "/": hasil = angka2 != 0 ? angka1 / angka2 : 0; break;
            }
            display.setText(String.valueOf(hasil));
            operator = "";
            startNewInput = true;
        } else if (input.matches("[+\\-*/]")) {
            angka1 = Double.parseDouble(display.getText());
            operator = input;
            startNewInput = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KalkulatorSederhana::new);
    }
}
