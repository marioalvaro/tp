package seedu.address.model.studentScore.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.studentScore.StudentScore;
import seedu.address.model.studentScore.UniqueStudentScoreList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudentScore comparison)
 */
public class StudentScoreBook implements ReadOnlyStudentScoreBook {

    private final UniqueStudentScoreList studentScores;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        studentScores = new UniqueStudentScoreList();
    }

    public StudentScoreBook() {}

    /**
     * Creates an StudentScoreBook using the StudentScores in the {@code toBeCopied}
     */
    public StudentScoreBook(ReadOnlyStudentScoreBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the studentScore list with {@code studentScores}.
     * {@code studentScores} must not contain duplicate studentScores.
     */
    public void setStudentScores(List<StudentScore> studentScores) {
        this.studentScores.setStudentScores(studentScores);
    }

    /**
     * Resets the existing data of this {@code StudentScoreBook} with {@code newData}.
     */
    public void resetData(ReadOnlyStudentScoreBook newData) {
        requireNonNull(newData);

        setStudentScores(newData.getStudentScoreList());
    }

    //// studentScore-level operations

    /**
     * Returns true if a studentScore with the same identity as {@code studentScore} exists in the address book.
     */
    public boolean hasStudentScore(StudentScore studentScore) {
        requireNonNull(studentScore);
        return studentScores.contains(studentScore);
    }

    /**
     * Adds a studentScore to the address book.
     * The studentScore must not already exist in the address book.
     */
    public void addStudentScore(StudentScore p) {
        studentScores.add(p);
    }

    /**
     * Replaces the given studentScore {@code target} in the list with {@code editedStudentScore}.
     * {@code target} must exist in the address book.
     * The studentScore identity of {@code editedStudentScore} must not be the same as another existing
     * studentScore in the address book.
     */
    public void setStudentScore(StudentScore target, StudentScore editedStudentScore) {
        requireNonNull(editedStudentScore);

        studentScores.setStudentScore(target, editedStudentScore);
    }

    /**
     * Removes {@code key} from this {@code StudentScoreBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudentScore(StudentScore key) {
        studentScores.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentScores", studentScores)
                .toString();
    }

    @Override
    public ObservableList<StudentScore> getStudentScoreList() {
        return studentScores.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentScoreBook)) {
            return false;
        }

        StudentScoreBook otherStudentScoreBook = (StudentScoreBook) other;
        return studentScores.equals(otherStudentScoreBook.studentScores);
    }

    @Override
    public int hashCode() {
        return studentScores.hashCode();
    }
}