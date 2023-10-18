package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.Model;

/**
 * Represents a Student's ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "Student IDs must start and end with a capital letter, have 7 numbers in between, and should not be blank";

    public static final String VALIDATION_REGEX = "[A-Z]\\d{7}[A-Z]";

    public final String sid;

    /**
     * Constructs a {@code studentId}.
     *
     * @param sid A valid student id.
     */
    public StudentId(String sid) {
        requireNonNull(sid);
        checkArgument(isValidName(sid), MESSAGE_CONSTRAINTS);
        this.sid = sid;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Method to convert studentId to student.
     *
     * @param model the system model
     * @return a Student
     */
    public Student toStudent(Model model) {
        int size = model.getStudentBook().getStudentList().size();
        Student student = null;
        for (int i = 0; i < size; i++) {
            if (this.equals(model.getStudentBook().getStudentList().get(i).getStudentId())) {
                student = model.getStudentBook().getStudentList().get(i);
            }
        }
        return student;
    }

    @Override
    public String toString() {
        return sid;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherName = (StudentId) other;
        return sid.equals(otherName.sid);
    }

    @Override
    public int hashCode() {
        return sid.hashCode();
    }

}
