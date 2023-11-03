package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.gradedcomponent.GcName;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.studentscore.StudentScore;


/**
 * Calculates the statistics.
 */
public class CompStatsCommand extends StatsCommand {
    public static final String COMMAND_WORD = "compStats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Calculate the statistics of a certain graded component.\n"
            + "Example: " + COMMAND_WORD;


    public static final String MESSAGE_EMPTY = "Please at that one score fulfilling the condition.\n";
    public static final String MESSAGE_EMPTY_TUT = "This tutorial group does not have any valid scores."
            + "Please check if the information is correct";

    private final GcName gradedComponentName;

    /**
     * Creates a CompStatsCommand to calculate the stats of a given grade component.
     *
     * @param gradedComponentName the name of the graded component to be analyzed
     */
    public CompStatsCommand(List<String> allStats, GcName gradedComponentName) {
        super(allStats);
        this.gradedComponentName = gradedComponentName;
    }

    /**
     * Creates a CompStatsCommand to calculate the stats of a given tutorial group in a given grade component.
     *
     * @param gradedComponentName the name of the graded component to be analyzed
     * @param tutorialGroup the tutorial group to be analyzed
     */
    public CompStatsCommand(List<String> allStats, GcName gradedComponentName, TutorialGroup tutorialGroup) {
        super(allStats, tutorialGroup);
        this.gradedComponentName = gradedComponentName;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Student> students = model.getStudentBook().getStudentList();
        if (isForTut) {
            return new CommandResult(generateTutStatsSummary(students, tutorialGroup.toString()));
        }
        return new CommandResult(generateOverallStatsSummary(students));
    }

    private String generateOverallStatsSummary(List<Student> students) {
        if (students.isEmpty()) {
            return MESSAGE_EMPTY;
        }
        List<Float> studentScores = students.stream().map(student -> student.getScores().stream()
                .filter(studentScore -> studentScore.getGcName().equals(gradedComponentName))
                .collect(Collectors.toList())).reduce(new ArrayList<>(), (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                }).stream().map(StudentScore::getScore).collect(Collectors.toList());
        return generateSummaryFromScores(studentScores, MESSAGE_SUCCESS);
    }

    private String generateTutStatsSummary(List<Student> students, String tutGroup) {
        List<Float> studentScores = students.stream().filter(student -> student.getTutorial().toString()
                .equals(tutGroup)).map(student -> student.getScores().stream()
                .filter(studentScore -> studentScore.getGcName().equals(gradedComponentName))
                .collect(Collectors.toList())).reduce(new ArrayList<>(), (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                }).stream().map(StudentScore::getScore).collect(Collectors.toList());
        if (studentScores.isEmpty()) {
            return MESSAGE_EMPTY_TUT;
        }
        return generateSummaryFromScores(studentScores, generateSummaryOpenning(tutGroup));
    }



    private String generateSummaryOpenning(String tutGroup) {
        StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS);
        sb.insert(MESSAGE_SUCCESS.length() - 2, String.format(" of %s of Tutorial Group %S",
                gradedComponentName.gcName, tutGroup));
        return sb.toString();
    }
}