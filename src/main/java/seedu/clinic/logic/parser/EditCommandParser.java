package seedu.clinic.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinic.logic.commands.EditCommand.MESSAGE_INVALID_TYPE_EDIT;
import static seedu.clinic.logic.commands.EditCommand.MESSAGE_NOT_EDITED;
import static seedu.clinic.logic.commands.EditCommand.MESSAGE_SUPPLIER_NO_ADDRESS;
import static seedu.clinic.logic.commands.EditCommand.MESSAGE_WAREHOUSE_NO_EMAIL;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.clinic.logic.parser.ParserUtil.checkInvalidArguments;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.clinic.commons.core.LogsCenter;
import seedu.clinic.commons.core.index.Index;
import seedu.clinic.logic.commands.EditCommand;
import seedu.clinic.logic.commands.EditCommand.EditDescriptor;
import seedu.clinic.logic.commands.EditCommand.EditSupplierDescriptor;
import seedu.clinic.logic.commands.EditCommand.EditWarehouseDescriptor;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.attribute.Phone;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private static final String LOG_MESSAGE_TOKENIZE_SUCCESS = "Successfully tokenized user input.";
    private static final String LOG_MESSAGE_VALID_TYPE_PREFIX_SUPPLIER =
            "User input contains type prefix for supplier.";
    private static final String LOG_MESSAGE_CREATE_SUPPLIER_DESCRIPTOR_SUCCESS =
            "Successfully created an editSupplierDescriptor using the given user input.";
    private static final String INVALID_WAREHOUSE_PREFIX_ASSERTION =
            "The warehouse prefix should have been present.";

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_INDEX, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_REMARK);

        logger.log(Level.INFO, LOG_MESSAGE_TOKENIZE_SUCCESS);

        // Check if prefixes are present
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE));
        }

        // Check if arguments are valid
        if (!argMultimap.getPreamble().isEmpty()) {
            System.out.println(argMultimap.getPreamble());
            ParserUtil.checkInvalidArgumentsInPreamble(argMultimap.getPreamble(), EditCommand.MESSAGE_USAGE);
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw checkInvalidArguments(PREFIX_INDEX, argMultimap, EditCommand.MESSAGE_USAGE);
        }

        Type type;
        try {
            type = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        } catch (ParseException pe) {
            throw checkInvalidArguments(PREFIX_TYPE, argMultimap, EditCommand.MESSAGE_USAGE);
        }

        if (type.equals(Type.WAREHOUSE_PRODUCT) || type.equals(Type.SUPPLIER_PRODUCT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_TYPE_EDIT, EditCommand.MESSAGE_USAGE));
        }

        // Parse into EditCommand
        if (type.equals(Type.SUPPLIER)) {
            return parseSupplierForEdit(argMultimap, index);
        } else {
            return parseWarehouseForEdit(argMultimap, index);
        }
    }

    private EditCommand parseSupplierForEdit(ArgumentMultimap argMultimap, Index index) throws ParseException {

        logger.log(Level.INFO, LOG_MESSAGE_VALID_TYPE_PREFIX_SUPPLIER);

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_SUPPLIER_NO_ADDRESS,
                    EditCommand.MESSAGE_USAGE));
        }

        EditSupplierDescriptor editSupplierDescriptor = new EditSupplierDescriptor();
        editSupplierDescriptor = parseSupplierDetailsForEditing(editSupplierDescriptor, argMultimap);

        logger.log(Level.INFO, LOG_MESSAGE_CREATE_SUPPLIER_DESCRIPTOR_SUCCESS);

        if (!editSupplierDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_NOT_EDITED,
                    EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(index, editSupplierDescriptor);
    }

    private EditCommand parseWarehouseForEdit(ArgumentMultimap argMultimap, Index index) throws ParseException {
        assert ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get()).equals(Type.WAREHOUSE)
                : INVALID_WAREHOUSE_PREFIX_ASSERTION;

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            throw new ParseException(String.format(MESSAGE_WAREHOUSE_NO_EMAIL,
                    EditCommand.MESSAGE_USAGE));
        }

        EditWarehouseDescriptor editWarehouseDescriptor = new EditWarehouseDescriptor();
        editWarehouseDescriptor = parseWarehouseDetailsForEditing(editWarehouseDescriptor, argMultimap);

        if (!editWarehouseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_NOT_EDITED,
                    EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(index, editWarehouseDescriptor);
    }

    private EditWarehouseDescriptor parseWarehouseDetailsForEditing(
            EditWarehouseDescriptor editWarehouseDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {
        parseGeneralDetails(editWarehouseDescriptor, argMultimap);

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            try {
                editWarehouseDescriptor.setAddress(
                        ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
            } catch (ParseException pe) {
                throw new ParseException(pe.getMessage() + "\n\n" + EditCommand.MESSAGE_USAGE);
            }
        }

        return editWarehouseDescriptor;
    }

    private EditSupplierDescriptor parseSupplierDetailsForEditing(
            EditSupplierDescriptor editSupplierDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {
        parseGeneralDetails(editSupplierDescriptor, argMultimap);

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            try {
                editSupplierDescriptor.setEmail(
                        ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            } catch (ParseException pe) {
                throw new ParseException(pe.getMessage() + "\n\n" + EditCommand.MESSAGE_USAGE);
            }
        }

        return editSupplierDescriptor;
    }


    private EditDescriptor parseGeneralDetails(EditDescriptor editDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {
        try {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editDescriptor.setName(
                        ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }

            if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
                editDescriptor.setRemark(
                        ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
            }
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + EditCommand.MESSAGE_USAGE);
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            Phone phoneNumber;
            try {
                phoneNumber = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            } catch (ParseException pe) {
                throw checkInvalidArguments(PREFIX_PHONE, argMultimap, EditCommand.MESSAGE_USAGE);
            }
            editDescriptor.setPhone(phoneNumber);
        }

        return editDescriptor;
    }
}
