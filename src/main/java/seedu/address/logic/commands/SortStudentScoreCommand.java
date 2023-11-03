package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPONENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVERSE;

import java.util.Collections;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.Student;
import seedu.address.model.student.model.StudentBook;
import seedu.address.model.studentscore.ScoreMatchPredicate;
import seedu.address.model.studentscore.model.StudentScoreBook;

/**
 * Sorts the student(s) whose student id by the given order.
 */
public class SortStudentScoreCommand extends Command {
    public static final String COMMAND_WORD = "sortStuScore";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all students scores under a given graded component.\n"
            + "Parameters:\n"
            + "[" + PREFIX_COMPONENT_NAME + "COMPONENT NAME]\n"
            + "[" + PREFIX_REVERSE + "Reverse (optional, by default increasing)]: "
            + "If true, the sorted list is reversed (or sorted in Descending order)\n"
            + "Example: " + COMMAND_WORD + " c/Midterm r/true";

    private final GcName gcName;
    private final boolean reverse;

    /**
     * Creates a SortStudentCommand to sort the displayed students.
     */
    public SortStudentScoreCommand(GcName gcName, boolean reverse) {
        this.gcName = gcName;
        this.reverse = reverse;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Student> currentStuPredicate = model.getCurrentStudentsPredicate();
        StudentBook studentBook = model.getStudentBook();
        StudentScoreBook studentScoreBook = model.getStudentScoreBook();
        studentScoreBook.sortStudentScore(reverse);
        studentBook.sortStudentScore(gcName, reverse);
        model.updateFilteredStudentScoreList(new ScoreMatchPredicate(Collections.singletonList(gcName.gcName)));
        if (currentStuPredicate != null) {
            model.updateFilteredStudentList(currentStuPredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortStudentScoreCommand)) {
            return false;
        }

        SortStudentScoreCommand otherSortCommand = (SortStudentScoreCommand) other;
        return gcName.equals(otherSortCommand.gcName) && reverse == otherSortCommand.reverse;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("of graded component ", gcName.toString())
                .toString();
    }
}
