package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.PersonContainsKeywordsPredicate.SearchField;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final Pattern PREFIX_PATTERN =
            Pattern.compile("(?<prefix>[npmf]/)(?<keywords>.*?)(?=\\s+[npmf]/|$)",
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        detectInvalidPrefixes(trimmedArgs);

        Map<SearchField, List<String>> fieldKeywordsMap = extractFieldKeywordsMap(trimmedArgs);
        if (fieldKeywordsMap.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(new PersonContainsKeywordsPredicate(fieldKeywordsMap));
    }

    /**
     * Ensures only valid prefix(es) is present.
     * @throws ParseException if invalid prefix(es) are found.
     */
    private void detectInvalidPrefixes(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        long invalidPrefixCount = Arrays.stream(args.split("\\s+"))
                .map(String::toLowerCase)
                .filter(token -> token.matches("^[a-z]+/$"))
                .filter(token -> !token.matches("^(n/|m/|p/|f/)$"))
                .count();
        if (invalidPrefixCount > 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    private Map<SearchField, List<String>> extractFieldKeywordsMap(String args) throws ParseException {
        Map<SearchField, List<String>> fieldKeywordsMap = new HashMap<>();
        Matcher matcher = PREFIX_PATTERN.matcher(args);

        while (matcher.find()) {
            String prefix = matcher.group("prefix").trim();
            String keywordStr = matcher.group("keywords").trim();

            if (keywordStr.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            SearchField field = getSearchField(prefix);
            assert field != null : "Parsed prefix must map to a valid SearchField";
            List<String> keywords = Arrays.asList(keywordStr.split("\\s+"));

            if (field == SearchField.FAVOURITE) {
                validateFavouriteKeywords(keywords);
            }
            if (fieldKeywordsMap.containsKey(field)) {
                throw new ParseException("Duplicate prefix detected: " + prefix
                        + ". Each prefix should only appear once.");
            }
            fieldKeywordsMap.put(field, keywords);
        }
        return fieldKeywordsMap;
    }

    private void validateFavouriteKeywords(List<String> keywords) throws ParseException {
        for (String keyword : keywords) {
            String lower = keyword.toLowerCase();
            if (!lower.equals("y") && !lower.equals("n")) {
                throw new ParseException("f/ field only accepts 'y' or 'n' (case-insensitive).");
            }
        }
    }

    /**
     * Converts a prefix string into the corresponding SearchField.
     * @throws ParseException if the prefix is invalid.
     */
    private PersonContainsKeywordsPredicate.SearchField getSearchField(String prefix) throws ParseException {
        return switch (prefix.toLowerCase()) {
        case "n/" -> PersonContainsKeywordsPredicate.SearchField.NAME;
        case "p/" -> PersonContainsKeywordsPredicate.SearchField.PHONE;
        case "m/" -> PersonContainsKeywordsPredicate.SearchField.MODULE;
        case "f/" -> PersonContainsKeywordsPredicate.SearchField.FAVOURITE;
        default -> throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
        };
    }
}
