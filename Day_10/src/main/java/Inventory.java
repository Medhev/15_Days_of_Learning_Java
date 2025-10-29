import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class Inventory {

    public final Map<String, InventoryManager> map = new HashMap<>();
    private final Random rand = new Random();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Inventory Management System!\n");
        boolean repeat = true;
        Inventory manager = new Inventory();

        while (repeat) {
            System.out.println("____________________________");
            System.out.println();
            System.out.println("1. Add Items");
            System.out.println("2. View Items");
            System.out.println("3. Search Items");
            System.out.println("4. Edit Inventory");
            System.out.println("5. Remove Items");
            System.out.println("6. Exit");
            System.out.println("____________________________");
            System.out.print("Enter the choice: ");
            try {
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1: {
                        System.out.print("\nEnter the item name: ");
                        String name = input.nextLine();
                        System.out.print("Enter the quantity: ");
                        int quantity = input.nextInt();
                        System.out.print("Enter the price: ");
                        double price = input.nextDouble();
                        input.nextLine(); // clear newline
                        manager.addItem(name, quantity, price);
                        break;
                    }
                    case 2:
                        manager.display();
                        break;
                    case 3: {
                        System.out.print("\nEnter the item name to be searched: ");
                        String searchingName = input.nextLine();
                        manager.searchItem(searchingName);
                        break;
                    }
                    case 4: {
                        System.out.print("Enter the Inventory item's name or code to edit: ");
                        String itemIdentifier = input.nextLine();
                        System.out.print("What do u want to edit? (quantity/price): ");
                        String operation = input.nextLine();
                        System.out.print("Enter the operator (+/-): ");
                        String operatorString = input.nextLine();
                        char Operator = operatorString.isEmpty() ? '+' : operatorString.charAt(0);
                        System.out.print("Enter the value to get edited: ");
                        double value = input.nextDouble();
                        input.nextLine(); // consume newline
                        if (operation.equalsIgnoreCase("quantity")) {
                            manager.editQuantity(itemIdentifier, (int) value, Operator);
                        } else if (operation.equalsIgnoreCase("price")) {
                            manager.editPrice(itemIdentifier, value, Operator);
                        } else {
                            System.out.println("Invalid operation. Choose 'quantity' or 'price'.");
                        }
                        break;
                    }
                    case 5: {
                        System.out.print("\nEnter the item code or name to be removed: ");
                        String removingItem = input.nextLine();
                        manager.removeItem(removingItem);
                        break;
                    }
                    case 6:
                        repeat = false;
                        System.out.println("Bye!");
                        break;
                    default:
                        System.out.println("Invalid Choice! Try Again\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }
        input.close();
    }

    public void addItem(String itemName, int quantity, double price) throws Exception {
        // Generate a new code until unique
        String itemCode;
        do {
            itemCode = "ITEMID@" + Math.abs(rand.nextLong(999_999_998, 999_999_999));
        } while (map.containsKey(itemCode));
        // Check by name if this already exists. (Optional)
        for (InventoryManager inv : map.values()) {
            if (inv.getItemName().equalsIgnoreCase(itemName)) {
                throw new DuplicateItemException("WARNING: ");
            }
        }
        map.put(itemCode, new InventoryManager(itemCode, itemName, quantity, price));
        System.out.println("Item added. Code: " + itemCode);
    }

    public void display() {
        if (map.isEmpty()) {
            System.out.println("Inventory is empty!");
            return;
        }
        System.out.println("\nCurrent Inventory:");
        for (InventoryManager item : map.values()) {
            System.out.println(item.getItemName() + " : " + item.getItemCode());
            System.out.println("Quantity : " + item.getQuantity() + "\tPrice : " + item.getPrice());
            System.out.println();
        }
    }

    public String findItemCodeByName(String name) {
        for (Map.Entry<String, InventoryManager> entry : map.entrySet()) {
            if (entry.getValue().getItemName().equalsIgnoreCase(name)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public InventoryManager getItemByCode(String code) {
        return map.get(code);
    }

    public InventoryManager getItemByName(String name) {
        for (InventoryManager inv : map.values()) {
            if (inv.getItemName().equalsIgnoreCase(name))
                return inv;
        }
        return null;
    }

    public void searchItem(String name) throws Exception {
        boolean found = false;
        for (InventoryManager item : map.values()) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                System.out.println("Found\nName: " + item.getItemName() + " Code: " + item.getItemCode() +
                        " Quantity: " + item.getQuantity() + " Price: " + item.getPrice());
                found = true;
            }
        }
        if (!found) {
            throw new ItemNotFoundException("WARNING: ");
        }
    }

    public void removeItem(String itemCodeOrName) throws Exception {
        InventoryManager item = map.get(itemCodeOrName);
        if (item != null) {
            map.remove(itemCodeOrName);
            System.out.println("Item Removed Successfully by Code.");
            return;
        }
        // Try to remove by name
        String code = findItemCodeByName(itemCodeOrName);
        if (code != null) {
            map.remove(code);
            System.out.println("Item Removed Successfully by Name.");
            return;
        }
        throw new ItemNotFoundException("WARNING: ");
    }

    public void editQuantity(String identifier, int quantity, char operator) throws Exception {
        InventoryManager item = map.get(identifier);
        if (item == null) {
            // try by name
            item = getItemByName(identifier);
        }
        if (item == null) {
            throw new ItemNotFoundException("WARNING: ");
        }
        if (operator == '+') {
            item.quantity += quantity;
        } else if (operator == '-') {
            item.quantity = Math.max(0, item.quantity - quantity);
        } else {
            System.out.println("Invalid operator. Use + or -");
            return;
        }
        System.out.println("Quantity Edited! New quantity: " + item.quantity);
    }

    public void editPrice(String identifier, double price, char operator) throws Exception {
        InventoryManager item = map.get(identifier);
        if (item == null) {
            // try by name
            item = getItemByName(identifier);
        }
        if (item == null) {
            throw new ItemNotFoundException("WARNING: ");
        }
        if (operator == '+') {
            item.price += price;
        } else if (operator == '-') {
            item.price = Math.max(0, item.price - price);
        } else {
            System.out.println("Invalid operator. Use + or -");
            return;
        }
        System.out.println("Price Edited! New price: " + item.price);
    }
}

