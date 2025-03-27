package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> firstFieldKeywordMap = new HashMap<>();
        firstFieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.NAME,
                Collections.singletonList("first"));
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> secondFieldKeywordMap = new HashMap<>();
        secondFieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.NAME,
                Collections.singletonList("second"));
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(firstFieldKeywordMap);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(secondFieldKeywordMap);

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_emptyInput_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsKeywordsPredicate predicate =
                preparePredicate(" ", PersonContainsKeywordsPredicate.SearchField.NAME);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz",
                PersonContainsKeywordsPredicate.SearchField.NAME);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_partialPhoneSearch_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonContainsKeywordsPredicate predicate =
                preparePredicate("9876", PersonContainsKeywordsPredicate.SearchField.PHONE);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_caseInsensitiveSearch_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonContainsKeywordsPredicate predicate =
                preparePredicate("alice benson", PersonContainsKeywordsPredicate.SearchField.NAME);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_phoneSearch_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonContainsKeywordsPredicate predicate =
                preparePredicate("98765432", PersonContainsKeywordsPredicate.SearchField.PHONE);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_moduleSearch_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonContainsKeywordsPredicate predicate =
                preparePredicate("CS3230", PersonContainsKeywordsPredicate.SearchField.MODULE);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_partialModuleSearch_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonContainsKeywordsPredicate predicate =
                preparePredicate("3230", PersonContainsKeywordsPredicate.SearchField.MODULE);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_favourite_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonContainsKeywordsPredicate predicate =
                preparePredicate("y", PersonContainsKeywordsPredicate.SearchField.FAVOURITE);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_caseInsensitiveFavourite_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonContainsKeywordsPredicate predicate =
                preparePredicate("Y", PersonContainsKeywordsPredicate.SearchField.FAVOURITE);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndFavourite_onePersonFound() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> map = new HashMap<>();
        map.put(PersonContainsKeywordsPredicate.SearchField.NAME, List.of("Fiona"));
        map.put(PersonContainsKeywordsPredicate.SearchField.FAVOURITE, List.of("y"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(map);
        FindCommand command = new FindCommand(predicate);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_moduleAndFavourite_onePersonFound() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> map = new HashMap<>();
        map.put(PersonContainsKeywordsPredicate.SearchField.MODULE, List.of("CS3230"));
        map.put(PersonContainsKeywordsPredicate.SearchField.FAVOURITE, List.of("y"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(map);
        FindCommand command = new FindCommand(predicate);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameModuleAndFavourite_onePersonFound() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> map = new HashMap<>();
        map.put(PersonContainsKeywordsPredicate.SearchField.NAME, List.of("Fiona"));
        map.put(PersonContainsKeywordsPredicate.SearchField.MODULE, List.of("CS3230"));
        map.put(PersonContainsKeywordsPredicate.SearchField.FAVOURITE, List.of("y"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(map);
        FindCommand command = new FindCommand(predicate);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.NAME, Arrays.asList("keyword"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PersonContainsKeywordsPredicate} with the given field.
     */
    private PersonContainsKeywordsPredicate preparePredicate(String userInput,
                                                             PersonContainsKeywordsPredicate.SearchField field) {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(field, Arrays.asList(userInput.split("\\s+")));
        return new PersonContainsKeywordsPredicate(fieldKeywordMap);
    }
}
