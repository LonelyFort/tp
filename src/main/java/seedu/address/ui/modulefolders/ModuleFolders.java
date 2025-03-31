package seedu.address.ui.modulefolders;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.ui.MainWindow;
import seedu.address.ui.UiPart;

/**
 * Represents the UI component for displaying module folders.
 * Each folder corresponds to a CS Module that when the user clicks on it,
 * it will redirect users to Contacts page with the list filtered for the module code.
 */
public class ModuleFolders extends UiPart<Region> {

    private static final String FXML = "ModuleFolders.fxml";
    private final Image folderImage = new Image(getClass().getResourceAsStream("/images/module_folder.png"));
    private final ObservableList<Person> personList;
    private final MainWindow mainWindow;

    @FXML
    private FlowPane folders;

    /**
     * Constructs a ModuleFolders component.
     * Generates folder buttons for each unique CS Module found inside the personList.
     *
     * @param personList The list of persons containing module data.
     * @param mainWindow mainWindow object that is created when GUI is loaded.
     */
    public ModuleFolders(ObservableList<Person> personList, MainWindow mainWindow) {
        super(FXML);
        this.personList = personList;
        this.mainWindow = mainWindow;

        // Initialise UI
        updateFolders();

        // Listen for changes in personList and update UI.
        // Behaviour similarly to useEffect in ReactJS.
        this.personList.addListener((ListChangeListener<? super Person>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    updateFolders();
                }
            }
        });
    }

    /**
     * Updates the folder UI dynamically when personList changes.
     */
    private void updateFolders() {
        Platform.runLater(() -> {
            folders.getChildren().clear();

            Set<String> moduleStringSet = new HashSet<>();

            // Add all unique modules into a set
            personList.forEach(person -> person.getModules()
                    .stream()
                    .sorted(Comparator.comparing(module -> module.toString()))
                    .forEach(module -> moduleStringSet.add(module.getModuleCode())));

            moduleStringSet.forEach(moduleString -> createFolder(moduleString, mainWindow));
        });
    }

    /**
     * Create a folder element with its respective tag inside the Modules Tab.
     * @param moduleString module code stored as a string.
     * @param mainWindow mainWindow object that is created when GUI is loaded.
     */
    private void createFolder(String moduleString, MainWindow mainWindow) {

        // Set folder image
        ImageView folderImageView = new ImageView(folderImage);
        folderImageView.setFitHeight(100.0);
        folderImageView.setFitWidth(100.0);

        // Set button function
        Button folderButton = new Button();
        folderButton.setGraphic(folderImageView);
        folderButton.setTranslateX(20.0);
        folderButton.setOnAction(e -> {
            mainWindow.filterListByModuleCode(moduleString);
            mainWindow.setSwitchWindowPlaceholder("Contacts");
        });

        // Set label
        Label label = new Label(moduleString);
        label.setTranslateX(40.0);

        // Group all JavaFX element into one VBox and add it into FlowPane
        VBox container = new VBox(folderButton, label);
        folders.getChildren().add(container);
    }


}
