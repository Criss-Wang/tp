package seedu.clinic.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_SUPPLIER_NAME = new Prefix("s/");
    public static final Prefix PREFIX_WAREHOUSE_NAME = new Prefix("w/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("addr/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_PRODUCT_NAME = new Prefix("pd/");
    public static final Prefix PREFIX_PRODUCT_QUANTITY = new Prefix("q/");

    /* Type Declaration definitions */
    public static final String TYPE_SUPPLIER = "supplier";
    public static final String TYPE_WAREHOUSE = "warehouse";

}