package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Represents a Role that a person may have.
 * Guarantees: immutable; name is one of the valid enum values as declared in {@link Role}
 */
public enum Role {
    TA("ta", "TA"),
    PROFESSOR("prof", "Professor");

    public static final String MESSAGE_CONSTRAINTS = "Roles should be one of the following: ta, prof";
    private static final Map<String, Role> lookupTable = new HashMap<>();
    static {
        for (Role r : EnumSet.allOf(Role.class)) {
            lookupTable.put(r.roleName, r);
        }
    }

    public final String roleName;
    private final String prettyName;

    /**
     * Constructs a {@code Role}.
     *
     * @param roleName A valid role name.
     */
    Role(String roleName, String prettyName) {
        this.roleName = roleName;
        this.prettyName = prettyName;
    }

    /**
     * Returns true if the given string is a valid role name
     */
    public static boolean isValidRole(String roleName) {
        return lookupTable.containsKey(roleName.toLowerCase(Locale.ROOT));
    }

    /**
     * Returns the {@link Role} from a given {@code roleName}.
     *
     * @param roleName A valid role name.
     * @return The respective {@code Role}.
     * @throws IllegalArgumentException if the role name is not valid.
     */
    public static Role getRole(String roleName) {
        requireNonNull(roleName);
        checkArgument(isValidRole(roleName), MESSAGE_CONSTRAINTS);
        return lookupTable.get(roleName.toLowerCase(Locale.ROOT));
    }

    /**
     * Print the pretty name of this role
     */
    public String toString() {
        return prettyName;
    }
}
