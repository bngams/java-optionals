import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneBookCrawlerTest {

//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();

    private PhoneBookCrawler phoneBookCrawler = new PhoneBookCrawler(new PhoneBook());

    @Test
    public void findPhoneNumberByNameAndPunishIfNothingFound() {
        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> phoneBookCrawler.findPhoneNumberByNameAndPunishIfNothingFound("Unknown")
        );
        assertThat(thrown.getMessage(), is("No phone number found"));
    }

    @Test
    public void findPhoneNumberByName_AndFindSomething(){
        String phoneNumber = phoneBookCrawler.findPhoneNumberByNameAndPrintPhoneBookIfNothingFound("Boris");
        assertThat(phoneNumber, is("033/161616"));
    }

    @Test
    public void findPhoneNumberByNameAndReturnEntirePhoneBookIfNothingFound() {
        String phoneBook = phoneBookCrawler.findPhoneNumberByNameAndPrintPhoneBookIfNothingFound("Unknown");
        assertThat(phoneBook, is("PhoneBook{phoneBookEntries={Eddy=033/161617, Boris=033/161616, Clément=033/161618}}"));
    }

    @Test
    public void findPhoneNumberByNameOrNameByPhoneNumber_foundByName() {
        String phoneNumber = phoneBookCrawler.findPhoneNumberByNameOrNameByPhoneNumber("Eddy", "Unknown");
        assertThat(phoneNumber, is("033/161617"));
    }

    @Test
    public void findPhoneNumberByNameOrNameByPhoneNumber_foundByPhoneNumber() {
        String name = phoneBookCrawler.findPhoneNumberByNameOrNameByPhoneNumber("Unknow", "033/161617");
        assertThat(name, is("Eddy"));
    }

    @Test
    public void findPhoneNumberByNameOrNameByPhoneNumber_nothingFoundReturnsFirstPhoneNumber() {
        String phoneNumber = phoneBookCrawler.findPhoneNumberByNameOrNameByPhoneNumber("Unkown", "Unknown");
        assertThat(phoneNumber, is("033/161616"));
    }
}