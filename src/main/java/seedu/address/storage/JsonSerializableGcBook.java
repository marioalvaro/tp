package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.model.GradedComponentBook;
import seedu.address.model.gradedcomponent.model.ReadOnlyGradedComponentBook;
import seedu.address.model.studentscore.StudentScore;
import seedu.address.model.studentscore.model.StudentScoreBook;


/**
 * An Immutable StudentScoreBook that is serializable to JSON format.
 */
@JsonRootName(value = "gradedcomponentbook")
public class JsonSerializableGcBook {

    public static final String MESSAGE_DUPLICATE_COMPONENT = "Graded Components list contains duplicate Component(s).";

    private final List<JsonAdaptedGradedComponent> gradedComponents = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGcBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableGcBook(@JsonProperty("gradedComponents") List<JsonAdaptedGradedComponent>
        gradedComponents) {
        this.gradedComponents.addAll(gradedComponents);
    }

    /**
     * Converts a given {@code ReadOnlyGradedComponentBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGcBook}.
     */
    public JsonSerializableGcBook(ReadOnlyGradedComponentBook source) {
        gradedComponents.addAll(source.getGradedComponentList().stream().map(JsonAdaptedGradedComponent::new)
            .collect(Collectors.toList()));
    }
    
}
