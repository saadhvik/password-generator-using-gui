import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
    public static void main(String[] args) {
        PasswordGeneratorGUI gui = new PasswordGeneratorGUI();
        gui.setVisible(true);
    }
}

class PasswordGeneratorGUI extends JFrame {
    private JLabel passwordLabel;
    private JButton generateButton;
    private JButton copyButton;
    private JPanel settingsPanel;
    private JTextField lengthField;
    private JTextField complexityField;
    private Random random;

    public PasswordGeneratorGUI() {
        setTitle("Password Generator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        passwordLabel = new JLabel("Click Generate to get a password.");
        generateButton = new JButton("Generate New");
        copyButton = new JButton("Copy to Clipboard");
        settingsPanel = new JPanel();
        lengthField = new JTextField(10);
        complexityField = new JTextField(10);
        random = new Random();

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = Integer.parseInt(lengthField.getText());
                int complexity = Integer.parseInt(complexityField.getText());

                String password = generatePassword(length, complexity);
                passwordLabel.setText("Generated Password: " + password);
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordLabel.getText().substring(19);
                copyToClipboard(password);
            }
        });

        settingsPanel.add(new JLabel("Password Length:"));
        settingsPanel.add(lengthField);
        settingsPanel.add(new JLabel("Password Complexity:"));
        settingsPanel.add(complexityField);

        add(passwordLabel);
        add(settingsPanel);
        add(generateButton);
        add(copyButton);
    }

    private String generatePassword(int length, int complexity) {
        String characters = "";
        String password = "";

        if (complexity >= 1) {
            characters += "abcdefghijklmnopqrstuvwxyz";
        }
        if (complexity >= 2) {
            characters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if (complexity >= 3) {
            characters += "0123456789";
        }
        if (complexity >= 4) {
            characters += "!@#$%^&*()-_=+";
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password += characters.charAt(randomIndex);
        }

        return password;
    }

    private void copyToClipboard(String password) {
        StringSelection stringSelection = new StringSelection(password);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}