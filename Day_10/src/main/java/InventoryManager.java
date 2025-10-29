public class InventoryManager {

    // These fields hold info about a single item instance
    private final String itemName;
    private final String itemCode;
    int quantity;
    double price;

    public InventoryManager(String itemCode, String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.price = price;
    }

    public InventoryManager(String itemName, String itemCode) {
        this.itemName = itemName;
        this.itemCode = itemCode;
    }

    public InventoryManager() {
        this.itemName = "";
        this.itemCode = "";
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
