package seedu.clinic.logic.parser;

import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_WAREHOUSE_NAME;

import java.util.stream.Stream;

import seedu.clinic.logic.commands.UpdateCommand;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.attribute.Name;
import seedu.clinic.model.product.Product;

public class UpdateCommandParser implements Parser<UpdateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateCommand
     * and returns an UpdateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public UpdateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WAREHOUSE_NAME, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_WAREHOUSE_NAME, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }

        Name warehouseName = ParserUtil.parseName(argMultimap.getValue(PREFIX_WAREHOUSE_NAME).get());
        Product product = ParserUtil.parseProduct(argMultimap.getValue(PREFIX_PRODUCT_NAME).get(),
                argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).get());
        return new UpdateCommand(warehouseName, product);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
