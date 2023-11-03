package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;

import java.util.List;

import seedu.address.logic.commands.FindGradedComponentCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gradedcomponent.GcMatchPredicate;
import seedu.address.model.studentscore.ScoreMatchPredicate;

/**
 * The type Find graded component command parser.
 */
public class FindGradedComponentCommandParser implements Parser<FindGradedComponentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindStudentCommand
     * and returns an FindStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindGradedComponentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPONENT_NAME);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStudentCommand.MESSAGE_USAGE));
        }

        List<String> gcKeywords = argMultimap.getAllValues(PREFIX_COMPONENT_NAME);

        GcMatchPredicate gcMatchPredicate = new GcMatchPredicate(gcKeywords);
        ScoreMatchPredicate scoreMatchPredicate = new ScoreMatchPredicate(gcMatchPredicate);
        return new FindGradedComponentCommand(gcMatchPredicate, scoreMatchPredicate);
    }
}