package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.student.Student;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.studentscore.model.StudentScoreBook;



/**
 * Format full help instructions for every command for display.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "addStu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the address book. \n"
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT ID"
            + PREFIX_NAME + "NAME "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL GROUP] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A1234567Z "
            + PREFIX_NAME + "John "
            + PREFIX_EMAIL + "John@example.com "
            + PREFIX_TUTORIAL_GROUP + "T05 "
            + PREFIX_TAG + "Potential TA";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the database";

    private Student toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getStudentBook().hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        List<GradedComponent> gradedComponents = model.getGradedComponentBook().getGradedComponentList();
        StudentScoreBook studentScoreBook = model.getStudentScoreBook();
        for (GradedComponent gradedComponent : gradedComponents) {
            studentScoreBook.addStudentScore(new StudentScore(toAdd.getStudentId(), gradedComponent.getName(), 0));
        }

        model.getStudentBook().addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }
}
