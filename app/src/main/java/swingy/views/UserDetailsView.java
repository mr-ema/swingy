package swingy.views;

import swingy.models.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.lang.reflect.Field;

public class UserDetailsView extends JPanel {
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;
        private JButton backBtn;
        private JLabel noUsersLabel;

        public UserDetailsView() {
                super(new GridBagLayout());

                // Set up the grid bag constraints
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(6, 6, 6, 6);
                gbc.fill = GridBagConstraints.BOTH;

                noUsersLabel = new JLabel("0 Users");
                noUsersLabel.setFont(noUsersLabel.getFont().deriveFont(Font.BOLD, 20f));
                noUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(noUsersLabel, gbc);
                noUsersLabel.setVisible(false);

                Field[] fields = User.class.getFields();
                String[] fieldNames = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                        fieldNames[i] = fields[i].getName();
                }
                tableModel = new DefaultTableModel(fieldNames, 0);

                table = new JTable(tableModel);
                table.setFont(table.getFont().deriveFont(Font.PLAIN));
                table.setRowHeight(30);

                scrollPane = new JScrollPane(table);
                gbc.gridy++;
                add(scrollPane, gbc);
                scrollPane.setVisible(false);

                // Back button
                int borderWidth = 2;
                int padding = 10;
                Border border = BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK, borderWidth),
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createEmptyBorder(padding / 2, padding * 2, padding / 2, padding * 2),
                                BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth)
                        )
                );

                backBtn = new JButton("Go Back");
                backBtn.setFont(backBtn.getFont().deriveFont(Font.BOLD));
                backBtn.setBorder(border);
                gbc.gridy++;
                add(backBtn, gbc);
        }

        public void displayUsers(List<User> userList) {
                if (userList == null || userList.isEmpty()) {
                        scrollPane.setVisible(false);
                        noUsersLabel.setVisible(true);
                } else {
                        noUsersLabel.setVisible(false);
                        scrollPane.setVisible(true);
                        clearTable();

                        for (User user : userList) {
                                Object[] rowData = { user.name, user.password };
                                tableModel.addRow(rowData);
                        }
                }

                revalidate();
                repaint();
        }

        public void clearTable() {
                tableModel.setRowCount(0);
        }

        public void backButton(ActionListener actionListener) {
                backBtn.addActionListener(actionListener);
        }
}
