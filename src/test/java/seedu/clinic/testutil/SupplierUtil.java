package seedu.clinic.testutil;

import static seedu.clinic.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_SUPPLIER_NAME;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.clinic.logic.commands.AddCommand;
import seedu.clinic.logic.commands.AddProductCommand;
import seedu.clinic.logic.commands.EditCommand.EditSupplierDescriptor;
import seedu.clinic.model.product.Product;
import seedu.clinic.model.supplier.Supplier;

/**
 * A utility class for Supplier.
 */
public class SupplierUtil {

    /**
     * Returns an add command string for adding the {@code supplier}.
     */
    public static String getAddCommand(Supplier supplier) {
        return AddCommand.COMMAND_WORD + " " + getSupplierDetails(supplier);
    }

    /**
     * Returns the part of AddProductCommand string for the given {@code supplier} and {@code product}'s details.
     */
    public static String getAddProductCommand(Supplier supplier, Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append(AddProductCommand.COMMAND_WORD + " ");
        sb.append(PREFIX_SUPPLIER_NAME + supplier.getName().fullName + " ");
        sb.append(PREFIX_PRODUCT_NAME + product.getProductName().fullName + " " + PREFIX_TAG);
        product.getProductTags().stream().forEach(
            s -> sb.append(s.tagName)
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code supplier}'s details.
     */
    public static String getSupplierDetails(Supplier supplier) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_SUPPLIER_NAME + supplier.getName().fullName + " ");
        sb.append(PREFIX_PHONE + supplier.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + supplier.getEmail().value + " ");
        sb.append(PREFIX_REMARK + supplier.getRemark().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditSupplierDescriptor}'s details.
     */
    public static String getEditSupplierDescriptorDetails(EditSupplierDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append(" "));
        if (descriptor.getProducts().isEmpty()) {
            return sb.toString();
        }
        Set<Product> products = descriptor.getProducts().get();
        if (products.isEmpty()) {
            sb.append(PREFIX_PRODUCT_NAME).append(" ").append(PREFIX_TAG);
        } else {
            for (Product product:products) {
                sb.append(PREFIX_PRODUCT_NAME + product.getProductName().fullName + " ");
                product.getProductTags().stream().forEach(
                    s -> sb.append(PREFIX_TAG + s.tagName + " ")
                );
            }
        }
        return sb.toString();
    }
}
