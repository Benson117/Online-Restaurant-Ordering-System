package OnlineRestaurantOrderingSystem;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.TemporalQueries;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddMenu {
    
    public double getDouble(double d) {
        String str = String.format("%1.2f", d);
        return Double.valueOf(str);
    }
    public AddMenu() {
        JFrame addMenu = new JFrame("Add Item Menu");
        
        // back button
            JButton btnBack = new JButton("Back");
            btnBack.setFont(new Font("Serif", Font.PLAIN, 20));
            btnBack.setForeground(Color.black);
            btnBack.setBounds(40, 30, 80, 40);
            btnBack.addActionListener((e) -> {
               new WelcomeScreen();
               addMenu.setVisible(false);
               addMenu.dispose();
            });
            addMenu.add(btnBack);
        
        // name
        JLabel itemLabel = new JLabel("Item Name:");
        itemLabel.setBounds(40, 100, 100, 30);
        addMenu.add(itemLabel);
        
        JTextField itemName = new JTextField();
        itemName.setBounds(180, 100, 160, 30);
        addMenu.add(itemName);
        
        // price
        JLabel priceLbl = new JLabel("Item Price (R):");
        priceLbl.setBounds(40, 160, 100, 30);
        addMenu.add(priceLbl);
        
        JTextField priceInput = new JTextField();
        priceInput.setBounds(180, 160, 100, 30);
        addMenu.add(priceInput);
        
        // add button
        JButton addButton = new JButton("ADD MENU");
        addButton.setBounds(100, 250, 120, 40);
        addButton.addActionListener((e) -> {
            String name = itemName.getText().toString();
            String priceStr = priceInput.getText().toString();
            
            try {
                double price = Double.parseDouble(priceStr);
                
                File txt = new File("./txt/menu.txt");
                Scanner myReader = new Scanner(txt);
                String lines = "";
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                    lines += data;
                }
                myReader.close();
               
                lines += name + "=" + getDouble(price);
                
                FileWriter myWriter = new FileWriter("./txt/menu.txt");
                myWriter.write(lines + "-");
                myWriter.close();
                
            
            } catch (IOException i) {
                System.out.println("error.");
                i.printStackTrace();
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, n.toString());
            } 
        });
        addMenu.add(addButton);
        
        addMenu.setLayout(null);
        addMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMenu.setSize(500, 400);
        addMenu.setVisible(true);
    }
    
}
