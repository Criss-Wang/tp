package seedu.clinic.logic.parser;

import static seedu.clinic.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.clinic.logic.parser.CliSyntax.PREFIX_COMMAND_STRING;

import seedu.clinic.logic.commands.AssignMacroCommand;
import seedu.clinic.logic.parser.exceptions.ParseException;
import seedu.clinic.model.macro.Alias;
import seedu.clinic.model.macro.Macro;
import seedu.clinic.model.macro.SavedCommandString;

/**
 * Parses input arguments and creates a new AssignMacroCommand object
 */
public class AssignMacroCommandParser implements Parser<AssignMacroCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignMacroCommand
     * and returns an AssignMacroCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignMacroCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_COMMAND_STRING);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_ALIAS, PREFIX_COMMAND_STRING)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignMacroCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            ParserUtil.checkInvalidArgumentsInPreamble(argMultimap.getPreamble(), AssignMacroCommand.MESSAGE_USAGE);
        }

        Alias alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());
        SavedCommandString savedCommandString = ParserUtil.parseCommandString(
                argMultimap.getValue(PREFIX_COMMAND_STRING).get());

        Macro macro = new Macro(alias, savedCommandString);

        return new AssignMacroCommand(macro);
    }
}
