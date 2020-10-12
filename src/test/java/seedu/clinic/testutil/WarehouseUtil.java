package seedu.clinic.testutil;

import static seedu.clinic.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_WAREHOUSE_NAME;

import seedu.clinic.logic.commands.AddCommand;
import seedu.clinic.logic.commands.UpdateCommand;
import seedu.clinic.model.product.Product;
import seedu.clinic.model.warehouse.Warehouse;

/**
 * A utility class for Warehouse.
 */
public class WarehouseUtil {
    /**
     * Returns an add command string for adding the {@code warehouse}.
     */
    public static String getAddCommand(Warehouse warehouse) {
        return AddCommand.COMMAND_WORD + " " + getWarehouseDetails(warehouse);
    }

    /**
     * Returns the part of command string for the given {@code warehouse}'s details.
     */
    public static String getWarehouseDetails(Warehouse warehouse) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_WAREHOUSE_NAME + warehouse.getName().fullName + " ");
        sb.append(PREFIX_PHONE + warehouse.getPhone().value + " ");
        sb.append(PREFIX_ADDRESS + warehouse.getAddress().value + " ");
        sb.append(PREFIX_REMARK + warehouse.getRemark().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of UpdateCommand string for the given {@code warehouse} and {@code product}'s details.
     */
    public static String getUpdateCommand(Warehouse warehouse, Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append(UpdateCommand.COMMAND_WORD + " ");
        sb.append(PREFIX_WAREHOUSE_NAME + warehouse.getName().fullName + " ");
        sb.append(PREFIX_PRODUCT_NAME + product.getProductName().fullName + " " + PREFIX_PRODUCT_QUANTITY);
        sb.append(product.getProductQuantity() + " ");
        return sb.toString();
    }
}
