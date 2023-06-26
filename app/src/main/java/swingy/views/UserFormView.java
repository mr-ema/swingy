package swingy.views;

import javax.annotation.Nullable;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class UserFormView extends JPanel {
        private GridBagConstraints gbc;
        private Map<String, JComponent> formData;
        private JButton createBtn;
        private JButton backBtn;

        public UserFormView() {
                super(new GridBagLayout());

                formData = new HashMap<>(7);
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.CENTER;

                // Title
                JLabel titleLabel = new JLabel("New User");
                Font titleFont = titleLabel.getFont();
                titleLabel.setFont(titleFont.deriveFont(titleFont.getStyle() | Font.BOLD, 24f));
                
                gbc.insets = new Insets(0, 0, 40, 0);
                add(titleLabel, gbc);

                // Fields margin and position
                gbc.insets = new Insets(6, 6, 6, 6);
                gbc.gridy++;

                // Input fields (labelName:inputBoxCols)
                gbc.anchor = GridBagConstraints.WEST;
                String[] labelsInputText = { "Name:20", "Password:20" };

                Arrays.stream(labelsInputText).forEach(label -> {
                        String[] chunk = label.split(":", 2);
                        addLabelInputText(chunk[0], Integer.parseInt(chunk[1]));
                });

                int borderWidth = 2;
                int padding = 10;
                Border border = BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK, borderWidth),
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createEmptyBorder(padding / 2, padding * 2, padding / 2, padding * 2),
                                BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth)
                        )
                );

                // Submit button
                gbc.gridy += 2;
                gbc.anchor = GridBagConstraints.EAST;
                createBtn = new JButton("Create");
                createBtn.setFont(createBtn.getFont().deriveFont(Font.BOLD));
                createBtn.setBorder(border);
                add(createBtn, gbc);

                // Back button
                gbc.anchor = GridBagConstraints.WEST;
                backBtn = new JButton("Go Back");
                backBtn.setFont(backBtn.getFont().deriveFont(Font.BOLD));
                backBtn.setBorder(border);
                add(backBtn, gbc);
        }

        private void addLabelInputText(String labelText, int cols) {
                gbc.gridx = 0;
                gbc.gridy++;

                JLabel label = new JLabel(labelText);
                label.setFont(label.getFont().deriveFont(Font.BOLD));
                add(label, gbc);

                gbc.gridx = 1;

                JTextField field = new JTextField(cols);
                add(field, gbc);
                add(Box.createVerticalGlue());
                
                formData.put(labelText.toLowerCase(), field);
        }

        @Nullable
        public String getFieldValue(String label) {
                JComponent component = formData.get(label);

                if (component instanceof JTextField) {
                        return ((JTextField) component).getText();
                } else if (component instanceof JComboBox) {
                        return ((JComboBox<?>) component).getSelectedItem().toString();
                }

                return null;
        }

        public void submitData(ActionListener actionListener) {
                createBtn.addActionListener(actionListener); 
        }

        public void backButton(ActionListener actionListener) {
                backBtn.addActionListener(actionListener);
        }

        public void reset() {
                for (JComponent component : formData.values()) {
                        if (component instanceof JTextField) {
                                ((JTextField) component).setText("");
                        }
                }
        }
}
