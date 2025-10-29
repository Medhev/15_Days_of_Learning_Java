public class DuplicateItemException extends Exception {
    public DuplicateItemException(String msg) {
        super(msg + "Item already exists in the inventory!");
    }
}
