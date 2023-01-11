package OnlineRestaurantOrderingSystem;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

class Order {

    private int quantity = 0;
    private double price = 0;
    private double total = 0;
    private String address = "";
    private String product = "";
   
  private String name = "";
    public Order(String prdct, String nmString, int q, double p, String addr) {
        name = nmString;
        quantity = q;
        price = p;
        total = price * quantity;
        address = addr;
        product = prdct;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }

    public String getProduct() {
        return product;
    }

    public String getAddress() {
        return address;
    }
}

public class CustomerOrder {

    public double getDouble(double d) {
        String str = String.format("%1.2f", d);
        return Double.valueOf(str);
    }
    
    public CustomerOrder() {
        try {
            JFrame frame = new JFrame("Customer Orders");

            ArrayList<Order> orderList = new ArrayList<Order>();

            String lines = "";
            File txt = new File("./txt/menu.txt");
            Scanner myReader = new Scanner(txt);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                lines += data;
            }
            myReader.close();
            String[] orders = lines.split("-");
            String[] jlistItems = new String[orders.length];
            for (int i = 0; i < orders.length; i++) {
                String[] MenuItems = orders[i].split("=");
                String item = "\t" + MenuItems[0] + "  R " + MenuItems[1];
                jlistItems[i] = item;
            }
            
        
            JButton btnBack = new JButton("back");
            btnBack.setFont(new Font("Serif", Font.PLAIN, 20));
            btnBack.setBounds(40, 10, 80, 30);
            btnBack.addActionListener((e) -> {
                new WelcomeScreen();
                frame.setVisible(false);
                frame.dispose();
            });
            frame.add(btnBack);

           
            JList<String> MenuItemsList = new JList<String>(jlistItems);
            MenuItemsList.setBounds(30, 50, 250, 350);
            MenuItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            frame.add(MenuItemsList);

            
            JLabel qty = new JLabel("Quantity:");
            qty.setBounds(300, 50, 80, 30);
            frame.add(qty);

            JTextField num = new JTextField();
            num.setBounds(390, 50, 70, 30);
            num.setEditable(false);
            frame.add(num);

           
            JTextField customerName = new JTextField("Name");
            customerName.setBounds(350, 100, 80, 30);
            frame.add(customerName);

           
            JTextField customerAddress = new JTextField("Address");
            customerAddress.setBounds(350, 150, 80, 30);
            frame.add(customerAddress);

            MenuItemsList.addListSelectionListener((e) -> {
                num.setEditable(true);
            });

            JButton BtnAdd = new JButton("ADD TO CART");
            BtnAdd.setBounds(100, 400, 120, 30);
            BtnAdd.addActionListener((e) -> {
                String[] lst = MenuItemsList.getSelectedValue().toString().split(" @ R");
                double prc = getDouble(Double.parseDouble(lst[1].replaceAll(" ", "")));
                String name = customerName.getText().toString();
                String address = customerAddress.getText().toString();
                int quantity = Integer.parseInt(num.getText().toString());
                String product = lst[0];

                System.out.println("Item: " + lst[0]);
                System.out.println("price: " + prc);
                System.out.println("Name: " + name);
                System.out.println("Address: " + address);
                
                if(name == "") {
                    JOptionPane.showMessageDialog(null, "Enter a valid name!!!");
                } else if (address == "") {
                    JOptionPane.showMessageDialog(null, "Enter a valid address!!!");
                } else {
                    
                    orderList.add(new Order(product, name, quantity, prc, address));
                    JOptionPane.showMessageDialog(null, "Item successfully added to cart");
                    
                    customerAddress.setEditable(false);
                    customerName.setEditable(false);
                }

                
            });
            frame.add(BtnAdd);

          
            JButton BtnCheckout = new JButton("CHECKOUT");
            BtnCheckout.setBounds(280, 400, 120, 30);

            BtnCheckout.addActionListener((e) -> {
                double totalPrice = 0;
                String toWrite = orderList.get(0).getName() + "," + orderList.get(0).getAddress() + ",";
                for (int i = 0; i < orderList.size(); i++) {
                    Order product = orderList.get(i);

                    toWrite += product.getProduct() + "@" + getDouble(product.getPrice()) + "x" + product.getQuantity();
                    try {
                        if (orderList.get(i + 1) != null) {
                            toWrite += ";";
                        }
                    } catch (IndexOutOfBoundsException ie) {

                    }

                    totalPrice += getDouble(product.getTotal());
                }
                String finalString = toWrite.replaceAll("\\s+", "");
                System.out.println(finalString);

              
                String ordersString = "";
                try {
                    File txt2 = new File("./txt/orders.txt");
                    Scanner myReader2 = new Scanner(txt2);
                    
                    while (myReader2.hasNextLine()) {
                        String data = myReader2.nextLine();
                        System.out.println(data);
                        ordersString += data;
                    }
                    myReader2.close();
                } catch (FileNotFoundException fn) {
                    JOptionPane.showMessageDialog(null, fn.getMessage());
                }
                
                try {
                    ordersString += finalString;
                    FileWriter myWriter = new FileWriter("./txt/orders.txt");
                    myWriter.write(ordersString + "-");
                    myWriter.close();
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, ioe.getMessage());
                }
                
                
            });
            frame.add(BtnCheckout);

            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);
        } catch (NumberFormatException nn) {
            JOptionPane.showMessageDialog(null, "Enter a valid quantity value!!!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
}
