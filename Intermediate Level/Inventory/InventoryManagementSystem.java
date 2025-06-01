
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InventoryManagementSystem extends JFrame {

    // Inventory Item Class
    static class InventoryItem {
        String name;
        int quantity;
        double pricePerItem;

        InventoryItem(String name, int quantity, double pricePerItem) {
            this.name = name;
            this.quantity = quantity;
            this.pricePerItem = pricePerItem;
        }

        public double getTotalPrice() {
            return quantity * pricePerItem;
        }
    }

    // Inventory list and table model
    private final ArrayList<InventoryItem> inventoryList = new ArrayList<>();
    private final DefaultTableModel tableModel;

    // UI Components
    private final JTextField nameField = new JTextField(15);
    private final JTextField qtyField = new JTextField(10);
    private final JTextField priceField = new JTextField(10);
    private final JTable table;

    // Constructor
    public InventoryManagementSystem() {
        setTitle("Inventory Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // === Input Panel ===
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Item Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Item Name:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(qtyField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Price per Item:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        inputPanel.add(btnPanel, gbc);

        add(inputPanel, BorderLayout.WEST);

        // === Table Panel ===
        String[] columnNames = {"Item Name", "Quantity", "Price per Item", "Total Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inventory List"));
        add(scrollPane, BorderLayout.CENTER);

        // === Button Actions ===
        addBtn.addActionListener(e -> addItem());
        updateBtn.addActionListener(e -> updateItem());
        deleteBtn.addActionListener(e -> deleteItem());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selected = table.getSelectedRow();
                if (selected != -1) {
                    nameField.setText(tableModel.getValueAt(selected, 0).toString());
                    qtyField.setText(tableModel.getValueAt(selected, 1).toString());
                    priceField.setText(tableModel.getValueAt(selected, 2).toString());
                }
            }
        });

        setVisible(true);
    }

    // === Add Item ===
    private void addItem() {
        try {
            String name = nameField.getText().trim();
            int qty = Integer.parseInt(qtyField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (name.isEmpty() || qty < 0 || price < 0) {
                JOptionPane.showMessageDialog(this, "Please enter valid input.");
                return;
            }

            InventoryItem item = new InventoryItem(name, qty, price);
            inventoryList.add(item);
            tableModel.addRow(new Object[]{name, qty, price, item.getTotalPrice()});
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity and Price must be numeric.");
        }
    }

    // === Update Item ===
    private void updateItem() {
        int selected = table.getSelectedRow();
        if (selected != -1) {
            try {
                String name = nameField.getText().trim();
                int qty = Integer.parseInt(qtyField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());

                if (name.isEmpty() || qty < 0 || price < 0) {
                    JOptionPane.showMessageDialog(this, "Please enter valid input.");
                    return;
                }

                InventoryItem item = new InventoryItem(name, qty, price);
                inventoryList.set(selected, item);
                tableModel.setValueAt(name, selected, 0);
                tableModel.setValueAt(qty, selected, 1);
                tableModel.setValueAt(price, selected, 2);
                tableModel.setValueAt(item.getTotalPrice(), selected, 3);
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity and Price must be numeric.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
        }
    }

    // === Delete Item ===
    private void deleteItem() {
        int selected = table.getSelectedRow();
        if (selected != -1) {
            inventoryList.remove(selected);
            tableModel.removeRow(selected);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    // === Clear Input Fields ===
    private void clearFields() {
        nameField.setText("");
        qtyField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryManagementSystem::new);
    }
}
