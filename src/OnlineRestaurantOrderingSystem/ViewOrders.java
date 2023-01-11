package OnlineRestaurantOrderingSystem;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewOrders {

    public double getDouble(double d) {
        String str = String.format("%1.2f", d);
        return Double.valueOf(str);
    }
    public ViewOrders() {

        try {
            JFrame frame = new JFrame("View Orders");

            JButton btnBack = new JButton("back");
            btnBack.setFont(new Font("Serif", Font.PLAIN, 20));
          
            btnBack.setBounds(40, 30, 80, 40);
            btnBack.addActionListener((e) -> {
                new WelcomeScreen();
                frame.setVisible(false);
                frame.dispose();
            });
            frame.add(btnBack);

            String lines = "";
            File txt = new File("./txt/orders.txt");
            Scanner myReader = new Scanner(txt);
            while (myReader.hasNextLine()) {
                lines += myReader.nextLine();
            }
            myReader.close();
            String[] orders = lines.split("-");
            if (orders.length == 0) {
                JLabel NoOrders = new JLabel("NO ORDERS AVAILABLE");
                NoOrders.setFont(new Font("Serif", Font.PLAIN, 20));
                NoOrders.setForeground(Color.red);
                NoOrders.setBounds(30, 70, 440, 30);
                frame.add(NoOrders);
            } else {
           
                JLabel CustomerName = new JLabel("CUSTOMER");
                CustomerName.setFont(new Font("Serif", Font.PLAIN, 20));
                CustomerName.setBounds(20, 80, 120, 30);
                frame.add(CustomerName);

              
                JLabel addressLabel = new JLabel("ADDRESS");
                addressLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                addressLabel.setBounds(150, 80, 150, 30);
                frame.add(addressLabel);

         
                JLabel MenuLbl = new JLabel("MENU ITEMS");
                MenuLbl.setFont(new Font("Serif", Font.PLAIN, 20));
                MenuLbl.setBounds(310, 80, 280, 30);
                frame.add(MenuLbl);


                
                JLabel amountLbl = new JLabel("AMOUNT");
                amountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
             
                amountLbl.setBounds(600, 80, 100, 30);
                frame.add(amountLbl);

                int y = 120;
                double totalValue = 0;
               
                for (int i = 0; i < orders.length; i++) {
                    String single = orders[i];
                    String[] items = single.split(",");
                    String customerName = items[0];
                    String customerAddress = items[1];

                    String[] menuItemsList = items[2].split(";");
                    String menuValue = "";
                    double subTotal = 0;
                    for (int j = 0; j < menuItemsList.length; j++) {
                        String menuString = menuItemsList[j];
                        
                        String[] allValues = menuString.split("@");
                        menuValue += allValues[0];

                        String[] prices = allValues[1].split("x");
                        String price = prices[0];
                        String quantity = prices[1];
                        subTotal += getDouble((Double.parseDouble(price) * Integer.parseInt(quantity)));
                        
                        menuValue += " x " + quantity + ", ";
                    }
                    totalValue += getDouble(subTotal);

                  
                    JLabel nameTextLabel = new JLabel(customerName);
                    nameTextLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                    nameTextLabel.setForeground(Color.BLACK);
                    nameTextLabel.setBounds(20, y, 120, 30);
                    frame.add(nameTextLabel);

                  
                    JLabel addressTextLabel = new JLabel(customerAddress);
                    addressTextLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                    addressTextLabel.setForeground(Color.BLACK);
                    addressTextLabel.setBounds(150, y, 150, 30);
                    frame.add(addressTextLabel);

                    if(menuValue.length() >= 120) {
                        menuValue = menuValue.substring(0, 118) + "...";
                    }
                    JLabel menuTextLabel = new JLabel(menuValue);
                    menuTextLabel.setFont(new Font("Serif", Font.PLAIN, 18));
                    menuTextLabel.setForeground(Color.BLACK);
                    menuTextLabel.setBounds(310, y, 280, 30);
                    frame.add(menuTextLabel);

               
                    JTextField subTotalTextBox = new JTextField("R" + String.valueOf(getDouble(subTotal)));
                    subTotalTextBox.setFont(new Font("Serif", Font.PLAIN, 20));
                    subTotalTextBox.setForeground(Color.BLACK);
                    subTotalTextBox.setBounds(600, y, 100, 30);
                    subTotalTextBox.setEditable(false);
                    frame.add(subTotalTextBox);
                 
                    y += 40;
                }
                
                
                JLabel TotalLabel = new JLabel("TOTAL:");
                TotalLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                TotalLabel.setForeground(Color.BLACK);
                TotalLabel.setBounds(50, y + 10, 100, 30);
                frame.add(TotalLabel);
                
               
                JTextField TotalTextBox = new JTextField("R" + String.valueOf(getDouble(totalValue)));
                TotalTextBox.setFont(new Font("Serif", Font.PLAIN, 20));
                TotalTextBox.setForeground(Color.BLACK);
                TotalTextBox.setBounds(600, y + 10, 100, 30);
                TotalTextBox.setEditable(false);
                frame.add(TotalTextBox);
            }


            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 700);
            frame.setVisible(true);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
