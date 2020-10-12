package seedu.clinic.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.clinic.model.Clinic;
import seedu.clinic.model.ReadOnlyClinic;
import seedu.clinic.model.attribute.Address;
import seedu.clinic.model.attribute.Email;
import seedu.clinic.model.attribute.Name;
import seedu.clinic.model.attribute.Phone;
import seedu.clinic.model.attribute.Remark;
import seedu.clinic.model.attribute.Tag;
import seedu.clinic.model.product.Product;
import seedu.clinic.model.supplier.Supplier;
import seedu.clinic.model.warehouse.Warehouse;

/**
 * Contains utility methods for populating {@code Clinic} with sample data.
 */
public class SampleDataUtil {
    public static Supplier[] getSampleSuppliers() {
        return new Supplier[] {
            new Supplier(new Name("Alex Yeoh Ltd"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Remark("long term partner"),
                getProductSet(new HashSet<Product>(), Map.of("Panadol", new String[]{"fever"}))),
            new Supplier(new Name("Bernice Yu Pte Ltd"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Remark("long term partner"),
                getProductSet(new HashSet<Product>(), Map.of("Panadol", new String[]{"fever"}))),
            new Supplier(new Name("Charlotte Oliveiro Ltd"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Remark("long term partner"),
                getProductSet(new HashSet<Product>(), Map.of("Panadol", new String[]{"fever"}))),
            new Supplier(new Name("David Li Pte Ltd"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Remark("long term partner"),
                getProductSet(new HashSet<Product>(), Map.of("Panadol", new String[]{"fever"}))),
            new Supplier(new Name("Irfan Ibrahim Pte Ltd"), new Phone("92492021"), new Email("irfan@example.com"),
                new Remark("long term partner"),
                getProductSet(new HashSet<Product>(), Map.of("Panadol", new String[]{"fever"}))),
            new Supplier(new Name("Roy Balakrishnan Pte Ltd"), new Phone("92624417"), new Email("royb@example.com"),
                new Remark("long term partner"),
                getProductSet(new HashSet<Product>(), Map.of("Panadol", new String[]{"fever"}))),
        };
    }

    public static Warehouse[] getSampleWarehouses() {
        return new Warehouse[] {
            new Warehouse(new Name("Alex Yeoh warehouse"), new Phone("87438807"),
                new Address("21 Lower Kent Ridge Rd, Singapore 119077"), new Remark("long term partner"),
                    getProductSetForWarehouse(new HashSet<Product>(), Map.of("Panadol", 10))),
            new Warehouse(new Name("Bernice Yu warehouse"), new Phone("99272758"),
                new Address("21 Lower Kent Ridge Rd, Singapore 119077"), new Remark("long term partner"),
                    getProductSetForWarehouse(new HashSet<Product>(), Map.of("Panadol", 20))),
            new Warehouse(new Name("Charlotte Oliveiro warehouse"), new Phone("93210283"),
                new Address("21 Lower Kent Ridge Rd, Singapore 119077"), new Remark("long term partner"),
                    getProductSetForWarehouse(new HashSet<Product>(), Map.of("Panadol", 30))),
            new Warehouse(new Name("David Li warehouse"), new Phone("91031282"),
                new Address("21 Lower Kent Ridge Rd, Singapore 119077"), new Remark("long term partner"),
                    getProductSetForWarehouse(new HashSet<Product>(), Map.of("Panadol", 100))),
            new Warehouse(new Name("Irfan Ibrahim warehouse"), new Phone("92492021"),
                new Address("21 Lower Kent Ridge Rd, Singapore 119077"), new Remark("long term partner"),
                    getProductSetForWarehouse(new HashSet<Product>(), Map.of("Panadol", 50))),
            new Warehouse(new Name("Roy Balakrishnan warehouse"), new Phone("92624417"),
                new Address("21 Lower Kent Ridge Rd, Singapore 119077"), new Remark("long term partner"),
                    getProductSetForWarehouse(new HashSet<Product>(), Map.of("Panadol", 70))),
        };
    }


    public static ReadOnlyClinic getSampleClinic() {
        Clinic sampleAb = new Clinic();
        for (Supplier sampleSupplier : getSampleSuppliers()) {
            sampleAb.addSupplier(sampleSupplier);
        }

        for (Warehouse sampleWarehouse : getSampleWarehouses()) {
            sampleAb.addWarehouse(sampleWarehouse);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a product set containing the hashmap of strings given.
     */
    public static Set<Product> getProductSet(Set<Product> productSet, Map<String, String[]> productMap) {
        for (String productName: productMap.keySet()) {
            Set<Tag> productTags = getTagSet(productMap.get(productName));
            Product product = new Product(new Name(productName), productTags);
            productSet.add(product);
        }
        return productSet;
    }

    /**
     * Returns a product set containing the hashmap of strings given.
     */
    public static Set<Product> getProductSetForWarehouse(Set<Product> productSet, Map<String, Integer> productMap) {
        for (String productName: productMap.keySet()) {
            Product product = new Product(new Name(productName), productMap.get(productName));
            productSet.add(product);
        }
        return productSet;
    }
}
