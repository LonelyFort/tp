package seedu.address.ui;

/**
 * Represents an interface for panes that allow filtering of person List with GUI interaction events
 * such as button press.
 */
public interface GuiFilterHandler {

    /**
     * Filters the contact list with the module code received via label in Module Folder.
     *
     * @param moduleCode module code in string.
     */
    void filterListByModuleCode(String moduleCode);

    /**
     * Clears filter when switching to Contacts tab manually.
     */
    void clearFilter();
}
