
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class SortInventory {
    private final int itemQuantity;
    private final String itemName;
    protected ArrayList<SortInventory> tryingSorting = new ArrayList<>();

    public SortInventory(String itemName, int ItemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = ItemQuantity;
    }

    public SortInventory() {
        this.itemName = null;
        this.itemQuantity = 0;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "name='" + itemName + '\'' +
                ", quantity=" + itemQuantity +
                '}';
    }

}

class SortAndDisplay extends SortInventory {
    void forQuickDisplayingLowSocks(int threshold) {
        tryingSorting.stream().filter(item -> item.getItemQuantity() < threshold).forEach(System.out::println);
    }

    void forSortedStoringOfLowStocks(int threshold) {
        List<SortInventory> sortedList = tryingSorting.stream()
                .filter(item -> item != null && item.getItemQuantity() < threshold)
                .sorted((a, b) -> a.getItemQuantity() - b.getItemQuantity())
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }
}

public class InventoryWithStreamFunctionalities {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to my App!");
        SortAndDisplay manager = new SortAndDisplay();
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Item");
            System.out.println("2. Display Low Stock Items");
            System.out.println("3. Store Low Stock Items");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = input.nextInt();
                input.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter item name: ");
                        String name = input.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = input.nextInt();
                        manager.tryingSorting.add(new SortInventory(name, quantity));
                        System.out.println("Item added successfully!");
                    }
                    case 2 -> {
                        System.out.print("Enter threshold for low stock: ");
                        int threshold = input.nextInt();
                        if (threshold < 0) {
                            System.out.println("Threshold must be non-negative!");
                            continue;
                        }
                        try {
                            manager.forQuickDisplayingLowSocks(threshold);
                        } catch (Exception e) {
                            System.out.println("Error processing stream: " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter threshold for low stock: ");
                        int threshold = input.nextInt();
                        if (threshold < 0) {
                            System.out.println("Threshold must be non-negative!");
                            continue;
                        }
                        try {
                            manager.forSortedStoringOfLowStocks(threshold);
                        } catch (Exception e) {
                            System.out.println("Error processing stream: " + e.getMessage());
                        }
                    }
                    case 4 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please try again.");
                input.nextLine(); // clear the invalid input
            }
        }
        input.close();
    }
}
