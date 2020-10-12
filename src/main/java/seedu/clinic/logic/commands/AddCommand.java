package seedu.clinic.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_SUPPLIER_NAME;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_WAREHOUSE_NAME;

import seedu.clinic.logic.commands.exceptions.CommandException;
import seedu.clinic.model.Model;
import seedu.clinic.model.supplier.Supplier;
import seedu.clinic.model.warehouse.Warehouse;

/**
 * Adds a supplier/warehouse to the CLI-nic app.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a supplier/warehouse to CLI-nic.\n"
            + "Supplier Parameters: "
            + PREFIX_SUPPLIER_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_REMARK + "REMARK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SUPPLIER_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_REMARK + "Largest contractor \n"
            + "Warehouse Parameters: "
            + PREFIX_WAREHOUSE_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_ADDRESS + "Address "
            + PREFIX_REMARK + "REMARK \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_WAREHOUSE_NAME + "NUS South "
            + PREFIX_PHONE + "91234562 "
            + PREFIX_ADDRESS + "21 Lower Kent Ridge Rd, Singapore 119077 "
            + PREFIX_REMARK + "Mainly deal with schools ";

    public static final String MESSAGE_SUPPLIER_SUCCESS = "New supplier added: %1$s";
    public static final String MESSAGE_WAREHOUSE_SUCCESS = "New warehouse added: %1$s";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "This supplier already exists in CLI-nic";
    public static final String MESSAGE_DUPLICATE_WAREHOUSE = "This warehouse already exists in CLI-nic";

    private final Supplier supplierToAdd;
    private final Warehouse warehouseToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Supplier}
     */
    public AddCommand(Supplier supplier) {
        requireNonNull(supplier);
        this.supplierToAdd = supplier;
        this.warehouseToAdd = null;
    }

    /**
     * Creates an AddCommand to add the specified {@code Warehouse}
     */
    public AddCommand(Warehouse warehouse) {
        requireNonNull(warehouse);
        this.supplierToAdd = null;
        this.warehouseToAdd = warehouse;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (supplierToAdd != null) {
            if (model.hasSupplier(supplierToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_SUPPLIER);
            }
            model.addSupplier(supplierToAdd);
            return new CommandResult(String.format(MESSAGE_SUPPLIER_SUCCESS, supplierToAdd));
        } else if (warehouseToAdd != null) {
            if (model.hasWarehouse(warehouseToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_WAREHOUSE);
            }
            model.addWarehouse(warehouseToAdd);
            return new CommandResult(String.format(MESSAGE_WAREHOUSE_SUCCESS, warehouseToAdd));
        }
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && (supplierToAdd != null
                ? supplierToAdd.equals(((AddCommand) other).supplierToAdd)
                : warehouseToAdd.equals(((AddCommand) other).warehouseToAdd)));
    }
}
