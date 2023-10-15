package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddStudentScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.StudentId;
import seedu.address.model.studentscore.StudentScore;

/**
 * Command Parser for StudentScore
 */
public class AddStudentScoreCommandParser implements Parser<AddStudentScoreCommand> {


    @Override
    public AddStudentScoreCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_COMPONENT_NAME,
                        PREFIX_MARKS, PREFIX_COMMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID,
                PREFIX_COMPONENT_NAME, PREFIX_MARKS, PREFIX_COMMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID,
                PREFIX_COMPONENT_NAME, PREFIX_MARKS, PREFIX_COMMENT);
        StudentId sid = new StudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        GcName gcname = new GcName(argMultimap.getValue(PREFIX_COMPONENT_NAME).get());
        float score = Float.parseFloat(argMultimap.getValue(PREFIX_MARKS).orElse("0"));
        String comment = argMultimap.getValue(PREFIX_COMMENT).orElse("");

        StudentScore studentScore = new StudentScore(sid, gcname, score, comment);
        return new AddStudentScoreCommand(studentScore);
    }


    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}