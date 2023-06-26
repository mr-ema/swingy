package swingy;

import swingy.views.MainView;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;

public class App {
        public static void main(String[] args) {
                FlatIntelliJLaf.setup();
                SwingUtilities.invokeLater(MainView::new);
        }
}
