package swingy.views;

import java.awt.*;
import javax.swing.*;

import swingy.controllers.*;

public class MainView extends JFrame {
        private static final int WINDOW_WIDTH = 1250;
        private static final int WINDOW_HEIGHT = 680;

        private CardLayout cardLayout;

        public MainView() {
                super("Swingy");

                // Initialization
                cardLayout = new CardLayout();

                HomeView home = new HomeView();
                UserDetailsView userDetails = new UserDetailsView();
                UserFormView form = new UserFormView();

                // Setup
                setLayout(cardLayout);
                setLocationRelativeTo(null);

                new UserController(form, userDetails, home);

                // Panels
                add(home, "home");
                add(userDetails, "userDetails");
                add(form, "form"); 

                // Actions
                home.showForm(e -> cardLayout.show(MainView.this.getContentPane(), "form"));
                home.showUserDetails(e -> cardLayout.show(MainView.this.getContentPane(), "userDetails"));
                form.backButton(e -> cardLayout.show(MainView.this.getContentPane(), "home"));
                userDetails.backButton(e -> cardLayout.show(MainView.this.getContentPane(), "home"));

                // Window
                ImageIcon icon = new ImageIcon("src/main/java/swingy/assets/favicon.png");
                setIconImage(icon.getImage());

                setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
        }
}
