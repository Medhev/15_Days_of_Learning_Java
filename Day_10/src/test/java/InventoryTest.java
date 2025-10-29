import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryTest {

    @Test
    void testAddItem() throws Exception {
        Inventory inventory = new Inventory();
        inventory.addItem("Test Item", 10, 99.99);
        Assertions.assertFalse(inventory.map.isEmpty());
        Assertions.assertEquals(1, inventory.map.size());

        Assertions.assertThrows(DuplicateItemException.class, () -> {
            inventory.addItem("Test Item", 5, 49.99);
        });
    }

    @Test
    void testSearchItem() throws Exception {
        Inventory inventory = new Inventory();
        inventory.addItem("Search Item", 15, 29.99);

        Assertions.assertDoesNotThrow(() -> inventory.searchItem("Search Item"));

        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            inventory.searchItem("Nonexistent Item");
        });
    }

    @Test
    void testEditQuantity() throws Exception {
        Inventory inventory = new Inventory();
        inventory.addItem("Edit Item", 20, 39.99);
        String itemCode = inventory.findItemCodeByName("Edit Item");
        Assertions.assertNotNull(itemCode);

        inventory.editQuantity(itemCode, 5, '+');
        Assertions.assertEquals(25, inventory.getItemByCode(itemCode).getQuantity());

        inventory.editQuantity(itemCode, 10, '-');
        Assertions.assertEquals(15, inventory.getItemByCode(itemCode).getQuantity());

        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            inventory.editQuantity("INVALID", 5, '+');
        });
    }

    @Test
    void testRemoveItem() throws Exception {
        Inventory inventory = new Inventory();
        inventory.addItem("Remove Item", 30, 59.99);
        String itemCode = inventory.findItemCodeByName("Remove Item");
        Assertions.assertNotNull(itemCode);

        Assertions.assertDoesNotThrow(() -> inventory.removeItem(itemCode));
        Assertions.assertTrue(inventory.map.isEmpty());

        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            inventory.removeItem("Nonexistent");
        });
    }
}