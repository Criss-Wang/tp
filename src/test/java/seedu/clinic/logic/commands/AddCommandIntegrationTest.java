package seedu.clinic.logic.commands;

import static seedu.clinic.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.clinic.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinic.testutil.TypicalSupplier.getTypicalClinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.clinic.model.Model;
import seedu.clinic.model.ModelManager;
import seedu.clinic.model.UserPrefs;
import seedu.clinic.model.supplier.Supplier;
import seedu.clinic.testutil.SupplierBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClinic(), new UserPrefs());
    }

    @Test
    public void execute_newSupplier_success() {
        Supplier validSupplier = new SupplierBuilder().build();

        Model expectedModel = new ModelManager(model.getClinic(), new UserPrefs());
        expectedModel.addSupplier(validSupplier);

        assertCommandSuccess(new AddCommand(validSupplier), model,
                String.format(AddCommand.MESSAGE_SUPPLIER_SUCCESS, validSupplier), expectedModel);
    }

    @Test
    public void execute_duplicateSupplier_throwsCommandException() {
        Supplier supplierInList = model.getClinic().getSupplierList().get(0);
        assertCommandFailure(new AddCommand(supplierInList), model, AddCommand.MESSAGE_DUPLICATE_SUPPLIER);
    }

    /*
    @Test
    public void execute_newWarehouse_success() {
        Warehouse validWarehouse = new WarehouseBuilder().build();

        Model expectedModel = new ModelManager(model.getClinic(), new UserPrefs());
        expectedModel.addWarehouse(validWarehouse);

        assertCommandSuccess(new AddCommand(validWarehouse), model,
                String.format(AddCommand.MESSAGE_WAREHOUSE_SUCCESS, validWarehouse), expectedModel);
    }

    @Test
    public void execute_duplicateWarehouse_throwsCommandException() {
        Warehouse warehouseInList = model.getClinic().getWarehouseList().get(0);
        assertCommandFailure(new AddCommand(warehouseInList), model, AddCommand.MESSAGE_DUPLICATE_WAREHOUSE);
    }
    */

}