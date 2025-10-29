public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String msg) {
        super(msg + "Item not found in the inventory!");
    }
}
