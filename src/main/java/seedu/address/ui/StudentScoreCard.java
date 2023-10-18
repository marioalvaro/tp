package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.studentscore.StudentScore;

/**
 * An UI component that displays information of a {@code StudentScore}.
 */
public class StudentScoreCard extends UiPart<Region> {

    private static final String FXML = "StudentScoreListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final StudentScore studentScore;

    @FXML
    private HBox cardPane;
    @FXML
    private Label sid;
    @FXML
    private Label id;
    @FXML
    private Label score;
    @FXML
    private VBox studentScoreBox;


    /**
     * Creates a {@code StudentScoreCard} with the given {@code StudentScore} and index to display.
     */
    public StudentScoreCard(StudentScore studentScore, int displayedIndex) {
        super(FXML);
        this.studentScore = studentScore;
        id.setText(displayedIndex + ". ");
        sid.setText(studentScore.getStudentId().sid + " - " + studentScore.getGc().getName());
        score.setText("Score: " + studentScore.getScore() + "/" + studentScore.getGc().getMaxMarks());
        if (!studentScore.getComment().isEmpty()) {
            studentScoreBox.getChildren().add(new Label("Comment: " + studentScore.getComment()));
        }

    }
}
