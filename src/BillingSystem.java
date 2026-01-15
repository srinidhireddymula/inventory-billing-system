import java.util.ArrayList;
import java.util.Scanner;

// Item class to represent inventory items
class Item {
    private String name;
    private double price;
    private int quantity;

    // Constructor
    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters & Setters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to calculate total value of item
    public double getTotalValue() {
        return price * quantity;
    }
}

// Inventory class to manage items
class Inventory {
    private ArrayList<Item> items = new ArrayList<>();

    // Add item
    public void addItem(Item item) {
        items.add(item);
    }

    // Update stock
    public void updateStock(String name, int newQuantity) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setQuantity(newQuantity);
                return;
            }
        }
        System.out.println("Item not found!");
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n--- Inventory ---");
        for (Item item : items) {
            System.out.printf("%s | Price: %.2f | Qty: %d | Total: %.2f\n",
                item.getName(), item.getPrice(), item.getQuantity(), item.getTotalValue());
        }
    }

    // Generate bill
    public void generateBill() {
        double total = 0;
        System.out.println("\n--- Bill ---");
        for (Item item : items) {
            double itemTotal = item.getTotalValue();
            System.out.printf("%s x %d = %.2f\n", item.getName(), item.getQuantity(), itemTotal);
            total += itemTotal;
        }
        System.out.printf("Grand Total: %.2f\n", total);
    }
}

// Main class with menu-driven navigation
public class BillingSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Inventory Billing System ---");
            System.out.println("1. Add Item");
            System.out.println("2. Update Stock");
            System.out.println("3. Display Inventory");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    inventory.addItem(new Item(name, price, qty));
                    break;
                case 2:
                    System.out.print("Enter item name to update: ");
                    String updateName = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQty = sc.nextInt();
                    inventory.updateStock(updateName, newQty);
                    break;
                case 3:
                    inventory.displayInventory();
                    break;
                case 4:
                    inventory.generateBill();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}