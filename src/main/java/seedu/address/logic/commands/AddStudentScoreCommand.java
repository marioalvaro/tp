package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.studentscore.StudentScore;


/**
 * Command Class for adding Student Score.
 */
public class AddStudentScoreCommand extends Command {
    public static final String COMMAND_WORD = "addStuScore";
    // to expand
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student score to the database.\n"
            + "Example: " + COMMAND_WORD + " s/A1234567Y c/Midterm m/57";

    public static final String MESSAGE_SUCCESS = "New student score added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENTSCORE = "This student score already exists in the database";

    private StudentScore toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentScoreCommand(StudentScore studentScore) {
        requireNonNull(studentScore);
        toAdd = studentScore;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getStudentScoreBook().hasStudentScore(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENTSCORE);
        }

        model.getStudentScoreBook().addStudentScore(toAdd);

        // ADD to list of studentscore in students as well
        toAdd.getStudentId().toStudent(model).addScoreToStudent(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatStudentScore(toAdd)));
    }
}
