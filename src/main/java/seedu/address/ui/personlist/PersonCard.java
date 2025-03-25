package seedu.address.ui.personlist;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final Image TA_TAG = new Image(PersonCard.class.getResourceAsStream("/images/tag_TA.png"));
    private static final Image PROF_TAG = new Image(PersonCard.class.getResourceAsStream("/images/tag_Prof.png"));
    private static final Image FAVOURITE_STAR = new Image(PersonCard.class.getResourceAsStream("/images/favourite_star.png"));

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private StackPane tag;
    @FXML
    private Label tagLabel;
    @FXML
    private ImageView tagType;
    @FXML
    private Label modules;
    @FXML
    private ImageView favourite;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        BooleanProperty isFavourite = new SimpleBooleanProperty(person.getIsFavourite());
        Set<String> strModules = new HashSet<>();
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);

        // there will be only one tag. If tag is empty tag will not be shown.
        //TODO: change this when Tag is stored as a string in future iterations.
        boolean isEmptySet = person.getTags().isEmpty();

        if (isEmptySet) {
            tag.getChildren().clear();
        } else {
            String tagName = person.getTags().stream().toList().get(0).tagName;
            boolean isValidTag = tagName.equals("TA") || tagName.equals("Professor");

            if (!isValidTag) {
                tag.getChildren().clear();
            } else {
                tagLabel.setText(tagName);

                // sets tag colour based on the role. TA = yellow. Prof = orange
                tagType.setImage(
                        tagLabel.getText().equals("TA")
                                ? TA_TAG
                                : PROF_TAG
                );
            }
        }
        favourite.setImage(FAVOURITE_STAR);
        favourite.visibleProperty().bind(isFavourite);
        person.getModules().stream()
                .sorted(Comparator.comparing(module -> module.toString()))
                .forEach(module -> strModules.add(module.getModuleCode()));
        modules.setText(String.join(", ", strModules));
    }
}
