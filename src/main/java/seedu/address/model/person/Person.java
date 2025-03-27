package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.ModuleRegistry.Module;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Role role;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Module> modules = new HashSet<>();
    private final Boolean isFavourite;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Role role, Set<Tag> tags, Set<Module> modules) {
        requireAllNonNull(name, phone, email, tags, modules);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.tags.addAll(tags);
        this.modules.addAll(modules);
        this.isFavourite = false;
    }

    /**
     * Every field must be present and not null.
     * Allows setting of isFavourite when constructing new Person object.
     */
    public Person(Name name, Phone phone, Email email, Role role, Set<Tag> tags,
                  Set<Module> modules, Boolean isFavourite) {
        requireAllNonNull(name, phone, email, tags, modules, isFavourite);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.tags.addAll(tags);
        this.modules.addAll(modules);
        this.isFavourite = isFavourite;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }
    public Role getRole() {
        return role;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    /**
     * Returns an immutable module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(this.modules);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Creates a new person with the exact same attribute as the current person, except
     * isFavourite is toggled.
     * @return A new person object that is created.
     */
    public Person toggleFav() {
        Person toggled = new Person(this.getName(), this.getPhone(),
                this.getEmail(), this.getRole(), this.getTags(), this.getModules(), !this.isFavourite);
        return toggled;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && tags.equals(otherPerson.tags)
                && modules.equals(otherPerson.modules)
                && isFavourite == (otherPerson.isFavourite);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own.
        return Objects.hash(name, phone, email, tags, modules, isFavourite);

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("tags", tags)
                .add("modules", modules)
                .add("isFavourite", isFavourite)
                .toString();
    }

}
