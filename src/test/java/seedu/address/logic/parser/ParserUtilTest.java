package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleRegistry;
import seedu.address.model.person.ModuleRegistry.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE_1 = "999999999999999999";
    private static final String INVALID_PHONE_2 = "-123456";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MODULE = "CS2103";
    private static final String REPEATED_INDEXES = "1 2 2";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_NAME_SPACES = "Rachel                Walker";
    private static final String VALID_PHONE_1 = "123456";
    private static final String VALID_PHONE_2 = "+98765432";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_MODULE_1 = "CS2103T";
    private static final String VALID_MODULE_2 = "CS2101";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseName_validValueWithWhitespaceInbetween_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = VALID_NAME_SPACES;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_1));
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_2));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE_1);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE_1));
        expectedPhone = new Phone(VALID_PHONE_2);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE_2));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE_1 + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE_1);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
        phoneWithWhitespace = WHITESPACE + VALID_PHONE_2 + WHITESPACE;
        expectedPhone = new Phone(VALID_PHONE_2);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModule(null));
    }

    @Test
    public void parseModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModule(INVALID_MODULE));
    }

    @Test
    public void parseModule_validValueWithoutWhitespace_returnsModule() throws Exception {
        Module expectedModule = ModuleRegistry.getModuleByCode(VALID_MODULE_1);
        assertEquals(expectedModule, ParserUtil.parseModule(VALID_MODULE_1));
    }

    @Test
    public void parseModule_validValueWithWhitespace_returnsTrimmedModule() throws Exception {
        String moduleWithWhitespace = WHITESPACE + VALID_MODULE_1 + WHITESPACE;
        Module expectedModule = ModuleRegistry.getModuleByCode(VALID_MODULE_1);
        assertEquals(expectedModule, ParserUtil.parseModule(moduleWithWhitespace));
    }

    @Test
    public void parseModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModules(null));
    }

    @Test
    public void parseModules_collectionWithInvalidModules_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseModules(Arrays.asList(VALID_MODULE_1, INVALID_MODULE)));
    }

    @Test
    public void parseModules_collectionWithValidModules_returnsModuleSet() throws Exception {
        Set<Module> actualModuleSet = ParserUtil.parseModules(Arrays.asList(VALID_MODULE_1, VALID_MODULE_2));
        Set<Module> expectedModuleSet = new HashSet<>(Arrays
                .asList(ModuleRegistry.getModuleByCode(VALID_MODULE_1),
                        ModuleRegistry.getModuleByCode(VALID_MODULE_2)));

        assertEquals(expectedModuleSet, actualModuleSet);
    }

    @Test
    public void parseMassIndex_duplicateIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseMassIndex(REPEATED_INDEXES));
    }
}
