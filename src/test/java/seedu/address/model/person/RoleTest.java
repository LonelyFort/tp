package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void get_validRoleAll_equal() {
        for (Role r : Role.values()) {
            assertEquals(r, Role.getRole(r.roleName));
        }
    }

    @Test
    public void get_validRoleCapitalisedAll_equal() {
        for (Role r : Role.values()) {
            assertEquals(r, Role.getRole(r.roleName.toUpperCase(Locale.ROOT)));
        }
    }

    @Test
    public void get_invalidRole_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, Role.MESSAGE_CONSTRAINTS, () -> Role.getRole("chocolate_cake"));
    }

    @Test
    public void print_roleTA_returnsPrettyName() {
        assertEquals("TA", Role.TA.toString());
    }

    @Test
    public void print_roleProf_returnsPrettyName() {
        assertEquals("Professor", Role.PROFESSOR.toString());
    }
}
