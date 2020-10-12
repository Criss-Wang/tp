package seedu.clinic.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.clinic.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.clinic.commons.core.GuiSettings;
import seedu.clinic.logic.commands.exceptions.CommandException;
import seedu.clinic.model.Clinic;
import seedu.clinic.model.Model;
import seedu.clinic.model.ReadOnlyClinic;
import seedu.clinic.model.ReadOnlyUserPrefs;
import seedu.clinic.model.supplier.Supplier;
import seedu.clinic.model.warehouse.Warehouse;
import seedu.clinic.testutil.SupplierBuilder;
import seedu.clinic.testutil.WarehouseBuilder;

public class AddCommandTest {
    @Test
    public void constructor_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand((Supplier) null));
    }

    @Test
    public void constructor_nullWarehouse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand((Warehouse) null));
    }

    @Test
    public void execute_supplierAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingSupplierAdded modelStub = new ModelStubAcceptingSupplierAdded();
        Supplier validSupplier = new SupplierBuilder().build();

        CommandResult commandResult = new AddCommand(validSupplier).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUPPLIER_SUCCESS, validSupplier),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSupplier), modelStub.suppliersAdded);
    }

    @Test
    public void execute_warehouseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingWarehouseAdded modelStub = new ModelStubAcceptingWarehouseAdded();
        Warehouse validWarehouse = new WarehouseBuilder().build();

        CommandResult commandResult = new AddCommand(validWarehouse).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_WAREHOUSE_SUCCESS, validWarehouse),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validWarehouse), modelStub.warehousesAdded);
    }

    @Test
    public void execute_duplicateSupplier_throwsCommandException() {
        Supplier validSupplier = new SupplierBuilder().build();
        AddCommand addCommand = new AddCommand(validSupplier);
        ModelStub modelStub = new ModelStubWithSupplier(validSupplier);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_SUPPLIER, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateWarehouse_throwsCommandException() {
        Warehouse validWarehouse = new WarehouseBuilder().build();
        AddCommand addCommand = new AddCommand(validWarehouse);
        ModelStub modelStub = new ModelStubWithWarehouse(validWarehouse);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_WAREHOUSE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Supplier alice = new SupplierBuilder().withName("Alice").build();
        Supplier bob = new SupplierBuilder().withName("Bob").build();
        Warehouse alpha = new WarehouseBuilder().withName("Alpha").build();
        Warehouse beta = new WarehouseBuilder().withName("Beta").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);
        AddCommand addAlphaCommand = new AddCommand(alpha);
        AddCommand addBetaCommand = new AddCommand(beta);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);
        assertEquals(addAlphaCommand, addAlphaCommand);

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertEquals(addAliceCommandCopy, addAliceCommand);
        AddCommand addAlphaCommandCopy = new AddCommand(alpha);
        assertEquals(addAlphaCommandCopy, addAlphaCommand);

        // different types -> returns false
        assertNotEquals(addAliceCommand, 1);
        assertNotEquals(addAlphaCommand, 1);

        // null -> returns false
        assertNotEquals(addAliceCommand, null);
        assertNotEquals(addAlphaCommand, null);

        // different supplier -> returns false
        assertNotEquals(addBobCommand, addAliceCommand);

        // different warehouse -> returns false
        assertNotEquals(addBetaCommand, addAlphaCommand);
    }

    /*
     * A default model stub that have all of the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getClinicFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClinicFilePath(Path clinicFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSupplier(Supplier supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClinic(ReadOnlyClinic newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClinic getClinic() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSupplier(Supplier supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSupplier(Supplier target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSupplier(Supplier target, Supplier editedSupplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Supplier> getFilteredSupplierList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSupplierList(Predicate<Supplier> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasWarehouse(Warehouse warehouse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteWarehouse(Warehouse target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addWarehouse(Warehouse warehouse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWarehouse(Warehouse target, Warehouse editedWarehouse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Warehouse> getFilteredWarehouseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredWarehouseList(Predicate<Warehouse> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /*
     * A Model stub that contains a single supplier.
     */
    private static class ModelStubWithSupplier extends ModelStub {
        private final Supplier supplier;

        ModelStubWithSupplier(Supplier supplier) {
            requireNonNull(supplier);
            this.supplier = supplier;
        }

        @Override
        public boolean hasSupplier(Supplier supplier) {
            requireNonNull(supplier);
            return this.supplier.isSameSupplier(supplier);
        }
    }

    /*
     * A Model stub that always accept the supplier being added.
     */
    private static class ModelStubAcceptingSupplierAdded extends ModelStub {
        final ArrayList<Supplier> suppliersAdded = new ArrayList<>();

        @Override
        public boolean hasSupplier(Supplier supplier) {
            requireNonNull(supplier);
            return suppliersAdded.stream().anyMatch(supplier::isSameSupplier);
        }

        @Override
        public void addSupplier(Supplier supplier) {
            requireNonNull(supplier);
            suppliersAdded.add(supplier);
        }

        @Override
        public ReadOnlyClinic getClinic() {
            return new Clinic();
        }
    }

    /*
     * A Model stub that contains a single warehouse.
     */
    private static class ModelStubWithWarehouse extends ModelStub {
        private final Warehouse warehouse;

        ModelStubWithWarehouse(Warehouse warehouse) {
            requireNonNull(warehouse);
            this.warehouse = warehouse;
        }

        @Override
        public boolean hasWarehouse(Warehouse warehouse) {
            requireNonNull(warehouse);
            return this.warehouse.isSameWarehouse(warehouse);
        }
    }

    /*
     * A Model stub that always accept the warehouse being added.
     */
    private static class ModelStubAcceptingWarehouseAdded extends ModelStub {
        final ArrayList<Warehouse> warehousesAdded = new ArrayList<>();

        @Override
        public boolean hasWarehouse(Warehouse warehouse) {
            requireNonNull(warehouse);
            return warehousesAdded.stream().anyMatch(warehouse::isSameWarehouse);
        }

        @Override
        public void addWarehouse(Warehouse warehouse) {
            requireNonNull(warehouse);
            warehousesAdded.add(warehouse);
        }

        @Override
        public ReadOnlyClinic getClinic() {
            return new Clinic();
        }
    }

}
