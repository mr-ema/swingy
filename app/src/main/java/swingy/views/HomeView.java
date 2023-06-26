package swingy.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {
        private JButton formBtn;
        private JButton detailsBtn;

        public HomeView() {
                setLayout(new BorderLayout());

                // Create a JLabel for the app logo
                JLabel logoLabel = new JLabel();
                ImageIcon logoIcon = new ImageIcon("src/main/java/swingy/assets/favicon.png");
                logoLabel.setIcon(logoIcon);

                logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

                // Add the logo label to the center of the panel
                add(logoLabel, BorderLayout.CENTER);

                int borderWidth = 2;
                int padding = 10;
                Border border = BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK, borderWidth),
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createEmptyBorder(padding / 2, padding * 2, padding / 2, padding * 2),
                                BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth)
                        )
                );

                // Create the buttons
                formBtn = new JButton("Form");
                detailsBtn = new JButton("Details");

                // Set the border for the buttons
                formBtn.setBorder(border);
                detailsBtn.setBorder(border);

                // Create a panel for the buttons
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.add(formBtn);
                buttonPanel.add(detailsBtn);

                // Add the button panel to the south of the panel
                add(buttonPanel, BorderLayout.PAGE_END);
        }

        public void showForm(ActionListener actionListener) {
                formBtn.addActionListener(actionListener); 
        }

        public void showUserDetails(ActionListener actionListener) {
                detailsBtn.addActionListener(actionListener); 
        }
}
