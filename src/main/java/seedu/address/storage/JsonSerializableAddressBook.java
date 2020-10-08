package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.supplier.Supplier;
import seedu.address.model.warehouse.Warehouse;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_SUPPLIER = "suppliers list contains duplicate supplier(s).";
    public static final String MESSAGE_DUPLICATE_WAREHOUSE = "warehouses list contains duplicate warehouse(s).";

    private final List<JsonAdaptedSupplier> suppliers = new ArrayList<>();
    private final List<JsonAdaptedWarehouse> warehouses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given suppliers and warehouses.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("suppliers") List<JsonAdaptedSupplier> suppliers,
                                       @JsonProperty("warehouses") List<JsonAdaptedWarehouse> warehouses) {
        this.suppliers.addAll(suppliers);
        this.warehouses.addAll(warehouses);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        suppliers.addAll(source.getSupplierList().stream().map(JsonAdaptedSupplier::new)
                .collect(Collectors.toList()));
        warehouses.addAll(source.getWarehouseList().stream().map(JsonAdaptedWarehouse::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedSupplier jsonAdaptedSupplier : suppliers) {
            Supplier supplier = jsonAdaptedSupplier.toModelType();
            if (addressBook.hasSupplier(supplier)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SUPPLIER);
            }
            addressBook.addSupplier(supplier);
        }

        for (JsonAdaptedWarehouse jsonAdaptedWarehouse : warehouses) {
            Warehouse warehouse = jsonAdaptedWarehouse.toModelType();
            if (addressBook.hasWarehouse(warehouse)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_WAREHOUSE);
            }
            addressBook.addWarehouse(warehouse);
        }

        return addressBook;
    }

}
