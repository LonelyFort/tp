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
import seedu.address.model.person.Role;
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

        Role role = person.getRole();

        tagLabel.setText(role.toString());

        // sets tag colour based on the role. TA = yellow. Prof = orange
        Image taTag = new Image(getClass().getResourceAsStream("/images/tag_ta.png"));
        Image profTag = new Image(getClass().getResourceAsStream("/images/tag_prof.png"));

        Image image = switch (role) {
        case TA -> taTag;
        case PROFESSOR -> profTag;
        };
        tagType.setImage(image);

        Image favouriteStar = new Image(getClass().getResourceAsStream("/images/favourite_star.png"));
        favourite.setImage(favouriteStar);
        favourite.visibleProperty().bind(isFavourite);
        person.getModules().stream()
                .sorted(Comparator.comparing(module -> module.toString()))
                .forEach(module -> strModules.add(module.getModuleCode()));
        modules.setText(String.join(", ", strModules));
    }
}
