package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class PersonContainsKeywordsPredicateTest {

    @Test
    void test_nameContainsKeyword_returnsTrue() {
        PersonContainsKeywordsPredicate.SearchField field =
                PersonContainsKeywordsPredicate.SearchField.NAME;
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();

        // One keyword
        fieldKeywordMap.put(field, Collections.singletonList("Alice"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keyword
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, Arrays.asList("Alice", "Bob"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, Arrays.asList("Bob", "Carol"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, Arrays.asList("aLiCe", "bOb"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Partial matching keyword
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, Collections.singletonList("Ali"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));
    }

    @Test
    void test_nameDoesNotContainKeyword_returnsFalse() {
        PersonContainsKeywordsPredicate.SearchField field =
                PersonContainsKeywordsPredicate.SearchField.NAME;
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();

        // Zero keywords
        fieldKeywordMap.put(field, Collections.emptyList());
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertFalse(predicate.test(new PersonBuilder().withName("Bob").build()));

        // Non-matching keywords
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, Collections.singletonList("Carol"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withModule("CS2103T").withEmail("alice@email.com").build()));
    }

    @Test
    void test_phoneContainsKeyword_returnsTrue() {
        PersonContainsKeywordsPredicate.SearchField field = PersonContainsKeywordsPredicate.SearchField.PHONE;
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();

        // One keyword
        fieldKeywordMap.put(field, Collections.singletonList("91234567"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));

        // Partial matching keyword
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, List.of("1234"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));

        // Separated search
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, List.of("9123", "4567"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));
    }

    @Test
    void test_phoneDoesNotContainKeyword_returnsFalse() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.PHONE, List.of("9999"));
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertFalse(predicate.test(new PersonBuilder().withPhone("91234567").build()));
    }

    @Test
    void test_moduleContainsKeyword_returnsTrue() {
        PersonContainsKeywordsPredicate.SearchField field =
                PersonContainsKeywordsPredicate.SearchField.MODULE;
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(field, Collections.singletonList("CS2103T"));

        // Fully matched
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withModule("CS2103T").build()));

        // Partial matched
        fieldKeywordMap.clear();
        fieldKeywordMap.put(field, Collections.singletonList("2103"));
        predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withModule("CS2103T").build()));
    }

    @Test
    void test_moduleDoesNotContainKeyword_returnsFalse() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.MODULE, List.of("IS2218"));
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertFalse(predicate.test(new PersonBuilder().withModule("CS2103T").build()));
    }

    @Test
    void test_isFavourite_returnsTrue() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.FAVOURITE, List.of("y"));
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withFavourite(true).build()));
    }

    @Test
    void test_isFavourite_returnsFalse() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.FAVOURITE, List.of("y"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertFalse(predicate.test(new PersonBuilder().withFavourite(false).build()));
    }

    @Test
    void test_keywordContainsNameAndPhone_returnsTrue() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.NAME, List.of("Alice"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.PHONE, List.of("91234567"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("91234567").build()));
    }

    @Test
    void test_keywordContainsNamePhoneAndFavourite_returnsTrue() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.NAME, List.of("Alice"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.PHONE, List.of("91234567"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.FAVOURITE, List.of("y"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice")
                .withPhone("91234567").withFavourite(true).build()));
    }

    @Test
    void test_keywordContainsNamePhoneModuleAndFavourite_returnsTrue() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.NAME, List.of("Alice"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.PHONE, List.of("91234567"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.FAVOURITE, List.of("y"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.MODULE, List.of("CS2103T"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice")
                .withPhone("91234567").withFavourite(true).withModule("CS2103T").build()));
    }

    @Test
    void test_partialKeywordContainsNamePhoneModuleAndFavourite_returnsTrue() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.NAME, List.of("A"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.PHONE, List.of("9123"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.FAVOURITE, List.of("y"));
        fieldKeywordMap.put(PersonContainsKeywordsPredicate.SearchField.MODULE, List.of("2103"));
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(fieldKeywordMap);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice")
                .withPhone("91234567").withFavourite(true).withModule("CS2103T").build()));
    }

    @Test
    void test_equals() {
        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> map1 = new HashMap<>();
        map1.put(PersonContainsKeywordsPredicate.SearchField.NAME, Collections.singletonList("Alice"));
        PersonContainsKeywordsPredicate predicate1 =
                new PersonContainsKeywordsPredicate(map1);
        PersonContainsKeywordsPredicate predicate2 =
                new PersonContainsKeywordsPredicate(map1);
        assertTrue(predicate1.equals(predicate2));

        Map<PersonContainsKeywordsPredicate.SearchField, List<String>> map2 = new HashMap<>();
        map2.put(PersonContainsKeywordsPredicate.SearchField.PHONE, List.of("91234567"));
        PersonContainsKeywordsPredicate predicate3 =
                new PersonContainsKeywordsPredicate(map2);
        assertFalse(predicate1.equals(predicate3));
    }
}
