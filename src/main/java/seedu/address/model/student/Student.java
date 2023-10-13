package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final StudentId sid;

    private StudentName name;
    private StudentEmail email;
    private TutorialGroup tg;

    /**
     * Every field must be present and not null.
     */
    public Student(StudentId sid, StudentName name, StudentEmail email, TutorialGroup tg) {
        requireAllNonNull(sid, name, email, tg);
        this.sid = sid;
        this.name = name;
        this.email = email;
        this.tg = tg;
    }

    public StudentId getStudentId() {
        return this.sid;
    }

    public StudentEmail getEmail() {
        return this.email;
    }

    public StudentName getName() {
        return this.name;
    }

    public TutorialGroup getTutorial() {
        return this.tg;
    }
    /**
     * Returns true if both students have the same student ID.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }
    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherPerson = (Student) other;
        return sid.equals(otherPerson.sid);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(sid);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student id", sid)
                .toString();

    }

}