import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends JFrame {
    private JRadioButton regularPizzaButton;
    private JRadioButton deluxePizzaButton;
    private JCheckBox vegetarianCheckBox;
    private JComboBox<String> sizeComboBox;
    private JComboBox<String> crustComboBox;
    private JCheckBox extraCheeseCheckBox;
    private JCheckBox extraToppingsCheckBox;
    private JCheckBox takeAwayCheckBox;
    private JButton generateBillButton;
    private BufferedImage backgroundImage;

    public Main() {
        setTitle("Pizza Bill Generator");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            // Load the background image
            backgroundImage = ImageIO.read(new File("d:\\Users\\ADMIN\\Pictures\\Saved Pictures\\pizza.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create and set background image for main screen
        JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false); // Make the main panel transparent to show the background image

        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        optionsPanel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent background color
        optionsPanel.setForeground(Color.WHITE); // Setting text color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10); // Increased spacing between components

        Font labelFont = new Font("Arial", Font.BOLD, 20); // Larger and bold font for labels

        Font checkBoxFont = new Font("Arial", Font.PLAIN, 20); // Smaller font for checkboxes

        regularPizzaButton = new JRadioButton("Regular Pizza");
        regularPizzaButton.setForeground(Color.WHITE);
        regularPizzaButton.setFont(checkBoxFont); // Apply smaller font to checkboxes
        regularPizzaButton.setOpaque(false);
        deluxePizzaButton = new JRadioButton("Deluxe Pizza");
        deluxePizzaButton.setForeground(Color.WHITE);
        deluxePizzaButton.setFont(checkBoxFont); // Apply smaller font to checkboxes
        deluxePizzaButton.setOpaque(false);

        ButtonGroup group = new ButtonGroup();
        group.add(regularPizzaButton);
        group.add(deluxePizzaButton);

        JLabel pLabel=new JLabel("Pizza Type:");
        pLabel.setFont(new Font("Arial", Font.BOLD, 25));
        pLabel.setForeground(Color.WHITE);
        optionsPanel.add(pLabel, gbc);
        gbc.gridx++;
        optionsPanel.add(regularPizzaButton, gbc);
        gbc.gridx++;
        optionsPanel.add(deluxePizzaButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel vLabel=new JLabel("Vegetarian");
        vLabel.setFont(new Font("Arial", Font.BOLD, 25));
        vLabel.setForeground(Color.WHITE);
        optionsPanel.add(vLabel, gbc);
        gbc.gridx++;
        vegetarianCheckBox = new JCheckBox();
        vegetarianCheckBox.setFont(checkBoxFont); // Apply smaller font to checkboxes
        optionsPanel.add(vegetarianCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel sizeLabel=new JLabel("Size");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        sizeLabel.setForeground(Color.WHITE);
        optionsPanel.add(sizeLabel, gbc);
        gbc.gridx++;
        String[] sizes = {"Small", "Medium", "Large"};
        sizeComboBox = new JComboBox<>(sizes);
        sizeComboBox.setFont(checkBoxFont); // Apply smaller font to combobox
        optionsPanel.add(sizeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel crustLabel=new JLabel("Crust");
        crustLabel.setFont(new Font("Arial", Font.BOLD, 25));
        crustLabel.setForeground(Color.WHITE);
        optionsPanel.add(crustLabel, gbc);
        gbc.gridx++;
        String[] crusts = {"Thin Crust", "Thick Crust", "Stuffed Crust"};
        crustComboBox = new JComboBox<>(crusts);
        crustComboBox.setFont(checkBoxFont); // Apply smaller font to combobox
        optionsPanel.add(crustComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel cLabel=new JLabel("Extra Cheese:");
        cLabel.setFont(new Font("Arial", Font.BOLD, 25));
        cLabel.setForeground(Color.WHITE);
        optionsPanel.add(cLabel, gbc);
        gbc.gridx++;
        extraCheeseCheckBox = new JCheckBox();
        extraCheeseCheckBox.setFont(checkBoxFont); // Apply smaller font to checkboxes
        optionsPanel.add(extraCheeseCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tLabel=new JLabel("Extra Toppings");
        tLabel.setFont(new Font("Arial", Font.BOLD, 25));
        tLabel.setForeground(Color.WHITE);
        optionsPanel.add(tLabel, gbc);
        gbc.gridx++;
        extraToppingsCheckBox = new JCheckBox();
        extraToppingsCheckBox.setFont(checkBoxFont); // Apply smaller font to checkboxes
        optionsPanel.add(extraToppingsCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel aLabel=new JLabel("Take Away:");
        aLabel.setFont(new Font("Arial", Font.BOLD, 25));
        aLabel.setForeground(Color.WHITE);
        optionsPanel.add(aLabel, gbc);
        gbc.gridx++;
        takeAwayCheckBox = new JCheckBox();
        takeAwayCheckBox.setFont(checkBoxFont); // Apply smaller font to checkboxes
        optionsPanel.add(takeAwayCheckBox, gbc);

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // Make button panel transparent to show the background image
        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setFont(new Font("Arial", Font.BOLD, 20));
        generateBillButton.setBackground(Color.WHITE);
        generateBillButton.setForeground(Color.BLACK);
        generateBillButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(generateBillButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    private void generateBill() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                String bill = generateBillText();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        displayBill(bill);
                    }
                });
            }
        });
        executor.shutdown();
    }

    private String generateBillText() {
        StringBuilder bill = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#,##0");

        bill.append("------------------------------------------------------------------\n");
        bill.append("                      Pizza Bill          \n");
        bill.append("------------------------------------------------------------------\n\n");

        if (regularPizzaButton.isSelected()) {
            bill.append("Pizza Type: Regular\n");
        } else if (deluxePizzaButton.isSelected()) {
            bill.append("Pizza Type: Deluxe\n");
        }

        bill.append("Vegetarian: ").append(vegetarianCheckBox.isSelected()).append("\n");
        bill.append("Size: ").append(sizeComboBox.getSelectedItem()).append("\n");
        bill.append("Crust: ").append(crustComboBox.getSelectedItem()).append("\n");
        bill.append("Extra Cheese: ").append(extraCheeseCheckBox.isSelected()).append("\n");
        bill.append("Extra Toppings: ").append(extraToppingsCheckBox.isSelected()).append("\n");
        bill.append("Take Away: ").append(takeAwayCheckBox.isSelected()).append("\n");

        // Calculate total price
        double totalPrice = calculateTotalPrice();
        bill.append("\nTotal Price: Rs").append(df.format(totalPrice)).append("\n");

        return bill.toString();
    }

    private void displayBill(String billText) {
        JFrame billFrame = new JFrame("Pizza Bill");
        billFrame.setSize(300, 400);
        billFrame.setLocationRelativeTo(null);

        JTextArea billTextArea = new JTextArea(billText);
        billTextArea.setEditable(false);
        billTextArea.setBackground(Color.WHITE); // Setting background color
        billTextArea.setForeground(Color.BLACK); // Setting text color
        billTextArea.setFont(new Font("Arial", Font.PLAIN, 18)); // Adjust font size

        JScrollPane scrollPane = new JScrollPane(billTextArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder());
        billFrame.getContentPane().add(scrollPane);

        billFrame.setVisible(true);
    }

    private double calculateTotalPrice() {
        double basePrice = 0;
        String size = (String) sizeComboBox.getSelectedItem();
        switch (size) {
            case "Small":
                basePrice += 100;
                break;
            case "Medium":
                basePrice += 150;
                break;
            case "Large":
                basePrice += 250;
                break;
        }

        String crust = (String) crustComboBox.getSelectedItem();
        switch (crust) {
            case "Thin Crust":
                basePrice += 100;
                break;
            case "Thick Crust":
                basePrice += 150;
                break;
            case "Stuffed Crust":
                basePrice += 200;
                break;
        }

        if (extraCheeseCheckBox.isSelected()) {
            basePrice += 10;
        }
        if (extraToppingsCheckBox.isSelected()) {
            basePrice += 20;
        }
        if (takeAwayCheckBox.isSelected()) {
            basePrice += 10;
        }

        return basePrice;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
