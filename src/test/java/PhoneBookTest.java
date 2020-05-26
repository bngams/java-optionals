import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PhoneBookTest {

    private PhoneBook phoneBook = new PhoneBook();

    @Test
    public void findPhoneNumberByName() {
        String name = "Boris";
        Optional<String> phoneNumber = phoneBook.findPhoneNumberByName(name);
        assertThat(phoneNumber.get(), is("033/161616"));
    }

    @Test
    public void findPhoneNumberByName_NotFound() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            String name = "Unknown";
            Optional<String> phoneNumber = phoneBook.findPhoneNumberByName(name);
            phoneNumber.get();
        });
    }

    @Test
    public void findNameByPhoneNumber() {
        Optional<String> name = phoneBook.findNameByPhoneNumber("033/161616");
        assertThat(name.get(), is("Boris"));
    }

    @Test
    public void findNameByPhoneNumber_NotFound() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Optional<String> phoneNumber = phoneBook.findPhoneNumberByName("0241/161619");
            phoneNumber.get();
        });
    }
}