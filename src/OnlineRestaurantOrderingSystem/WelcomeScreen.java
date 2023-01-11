package OnlineRestaurantOrderingSystem;

import javax.swing.JButton;
import javax.swing.JFrame;


public class WelcomeScreen {
    public WelcomeScreen() {
        JFrame welcomeFrame = new JFrame("Online Restaurant Ordering System");
        
        // add menu button
        JButton btnAddMenu = new JButton("ADD MENU");
        btnAddMenu.setBounds(50, 40, 150, 40);
        btnAddMenu.addActionListener(v -> {
            new AddMenu();
            welcomeFrame.setVisible(false);
            welcomeFrame.dispose();
        });
        welcomeFrame.add(btnAddMenu);
        
        // online CustomerOrder button
        JButton btnOrder = new JButton("CUSTOMER ORDERING");
        btnOrder.setBounds(50, 100, 150, 40);
        btnOrder.addActionListener(v -> {
            new CustomerOrder();
            welcomeFrame.setVisible(false);
            welcomeFrame.dispose();
        });
        welcomeFrame.add(btnOrder);
        
        // view orders button
        JButton btnViewOrder = new JButton("VIEW ORDERS");
        btnViewOrder.setBounds(50, 160, 150, 40);
        btnViewOrder.addActionListener(v -> {
            new ViewOrders();
            welcomeFrame.setVisible(false);
            welcomeFrame.dispose();
        });
        welcomeFrame.add(btnViewOrder);
        
        welcomeFrame.setLayout(null);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(350, 350);
        welcomeFrame.setVisible(true);
    }
}
