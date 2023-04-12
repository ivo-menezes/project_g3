package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.AccountDTO;
import org.switch2022.project.mapper.RegisterAccountDTO;
import org.switch2022.project.model.account.Account;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class AccountListTest {

    @Test
    void addAccount() {
        Profile profile = new Profile("User");
        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        AccountList accountList = new AccountList();

        assertTrue(accountList.addAccount(account));
    }

    /***
     * Added two accounts to list, used this to check if the methods successfully access the list and chooses the
     * correct account.
     */
    @Test
    @DisplayName("ensure that the correct account is obtained")
    void getAccount() {
        // arrange
        Profile profile = new Profile("User");
        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        Account accountTwo = new Account("Joao", "yyyyyy@gmail.com", "44851114", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        String email = "xxxxx@gmail.com";

        // act
        Account result = accountList.getAccount(email);

        //assertNotSame(accountTwo, result);
        assertEquals(account, result);
    }

    /***
     * The method reaches into the list and gets the account assigned to the index.
     */
    @Test
    @DisplayName("Check if the method getAccountAtIndex works as intended")
    void testingGetAccountAtIndex() {
        Profile profile = new Profile("User");
        Profile profileTest = new Profile("Manager");

        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        Account accountTwo = new Account("Joao", "yyyyyy@gmail.com", "44851114", profileTest);
        Account accountThree = new Account("Pedro", "zzzzz@gmail.com", "44853224", profile);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        accountList.addAccount(accountThree);

        Account result = accountList.getAccountAtIndex(1);

        assertEquals(accountTwo, result);
    }

    /***
     * The test works with the same DTO, but doesn't compare two similar DTOs.
     * The method works by fetching the data of the account and sending it to the DTO.
     */
    @Test
    @DisplayName("Creates account DTO")
    void testIfAccountDTOIsCreatedSuccessfully() {
        Profile profile = new Profile("User");

        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        //AccountDTO accountDTOTest = new AccountDTO("xxxxx@gmail.com", true);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);

        AccountDTO accountDTO = accountList.createAccountDTO(account);
        AccountDTO accountDTOTestTwo = accountDTO;

        assertNotNull(accountDTO);
        assertSame(accountDTO, accountDTOTestTwo);
    }

    @Test
    @DisplayName("ensure isEmailInUse returns true when email is already in use")
    void isEmailInUseTrue() {
        // arrange
        // create a new account (dto+profile)
        RegisterAccountDTO dto = new RegisterAccountDTO();
        dto.name = "John Doe";
        dto.email = "johndoe@example.com";
        dto.phone = "1234565";
        Profile profile = new Profile("User");
        Account account = new Account(dto, profile);
        // add account to an empty ArrayList
        ArrayList<Account> testArray = new ArrayList<>();
        testArray.add(account);
        // use the ArrayList to initialize an AccountList
        AccountList accountList = new AccountList(testArray);

        String testEmail = "johndoe@example.com";

        // act
        boolean result = accountList.isEmailInUse(testEmail);
        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure isEmailInUse returns false when email is not in use")
    void isEmailInUseFalse() {
        // arrange
        // create a new account (dto+profile)
        RegisterAccountDTO dto = new RegisterAccountDTO();
        dto.name = "John Doe";
        dto.email = "johndoe@example.com";
        dto.phone = "1234565";
        Profile profile = new Profile("User");
        Account account = new Account(dto, profile);
        // add account to an empty ArrayList
        ArrayList<Account> testArray = new ArrayList<>();
        testArray.add(account);
        // use the ArrayList to initialize an AccountList
        AccountList accountList = new AccountList(testArray);

        String testEmail = "janedoe@example.com";

        // act
        boolean result = accountList.isEmailInUse(testEmail);
        // assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure isEmailInUse throws exception with null email")
    void isEmailInUseException() {
        // arrange
        AccountList accountList = new AccountList();
        String testEmail = null;
        String expectedMessage = "Email must not be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> accountList.isEmailInUse(testEmail));
        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure registering an account is successful")
    void registerAccountSuccess() {
        // arrange
        // create an account
        RegisterAccountDTO dto = new RegisterAccountDTO();
        dto.name = "John Doe";
        dto.email = "johndoe@example.com";
        dto.phone = "1234565";
        Profile profile = new Profile("User");
        Account testAccount = new Account(dto, profile);
        // add account to an empty ArrayList
        ArrayList<Account> testArray = new ArrayList<>();
        testArray.add(testAccount);
        // use the ArrayList to initialize an AccountList
        AccountList testAccountList = new AccountList(testArray);
        // initialize another (empty) AccountList
        AccountList accountList = new AccountList();

        // act
        // register account with the empty AccountList
        boolean result = accountList.registerAccount(dto, profile);
        // assert
        assertTrue(result);
        // both AccountLists must be equal
        assertEquals(testAccountList, accountList);
    }

    @Test
    @DisplayName("ensure registering an account with same email data is unsuccessful")
    void registerAccountSameEmailFailure() {
        // arrange
        AccountList accountList = new AccountList();
        Profile profile = new Profile("User");

        RegisterAccountDTO dto1 = new RegisterAccountDTO();
        dto1.name = "John Doe";
        dto1.email = "jdoe@example.com";
        dto1.phone = "1234565";

        RegisterAccountDTO dto2 = new RegisterAccountDTO();
        dto2.name = "Jimmy Doe";
        dto2.email = "jdoe@example.com";
        dto2.phone = "760100200";

        Account testAccount1 = new Account(dto1, profile);
        ArrayList<Account> testArray = new ArrayList<>();
        testArray.add(testAccount1);
        AccountList testAccountList = new AccountList(testArray);

        // act
        boolean result1 = accountList.registerAccount(dto1, profile);
        boolean result2 = accountList.registerAccount(dto2, profile);

        // assert
        assertTrue(result1);
        assertFalse(result2);
        assertEquals(testAccountList, accountList);
    }

    @Test
    @DisplayName("ensure registering an account with null DTO throws exception")
    void registerAccountNullDTOThrowsException() {
        AccountList accountList = new AccountList();
        Profile profile = new Profile("User");
        RegisterAccountDTO dto1 = null;
        String expectedMessage = "Account information and profile needed.";

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> accountList.registerAccount(dto1, profile));
        String resultMessage = result.getMessage();

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure registering an account with null profile throws exception")
    void registerAccountNullProfileThrowsException() {
        AccountList accountList = new AccountList();
        Profile profile = null;
        RegisterAccountDTO dto1 = new RegisterAccountDTO();
        dto1.name = "John Doe";
        dto1.email = "jdoe@example.com";
        dto1.phone = "1234565";

        String expectedMessage = "Account information and profile needed.";

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> accountList.registerAccount(dto1, profile));
        String resultMessage = result.getMessage();

        assertEquals(expectedMessage, resultMessage);
    }

    /***
     * The tests below serve to ensure the mutations are caught. At least, most of them.
     */
    @Test
    @DisplayName("ensure the adding account method catches the null account")
    void checkIfItCatchesAnIllegalArgumentInTheAddAccountMethod(){
        AccountList accountList = new AccountList();
        Account account = null;

        assertFalse(accountList.addAccount(account));
    }

    @Test
    @DisplayName("ensure the adding account method catches the null account")
    void checkIfItTheListSizeDoesNotGiveZero(){
        Profile profile = new Profile("User");
        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        Account accountTwo = new Account("Joao", "yyyyyy@gmail.com", "44851114", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        int expected = 2;

        int result = accountList.listSize();
        assertEquals(expected, result);
        assertNotEquals(0, result);
    }
    @Test
    @DisplayName("ensure the equals method is tested")
    void checkIfTheListEqualsItself(){
        Profile profile = new Profile("User");
        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        Account accountTwo = new Account("Joao", "yyyyyy@gmail.com", "44851114", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        AccountList accountListTwo = accountList;

        assertSame(accountListTwo, accountList);
        assertTrue(accountList.equals(accountListTwo));
    }
    @Test
    @DisplayName("checks if the object doesn't equal another object")
    void checkIfItDoesNotEqualsAnotherObject(){
        Profile profile = new Profile("User");
        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        Account accountTwo = new Account("Joao", "yyyyyy@gmail.com", "44851114", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);

        assertFalse(accountList.equals(account));
        assertNotEquals(true, accountList.equals(account));
    }
    @Test
    @DisplayName("testing the hashcodes")
    void checkTheHashCodes(){
        Profile profile = new Profile("User");
        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        Account accountTwo = new Account("Joao", "yyyyyy@gmail.com", "44851114", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        AccountList accountListTwo = accountList;

        assertEquals(accountListTwo.hashCode(),accountList.hashCode());
        assertNotEquals(0, accountList.hashCode());
    }
    @Test
    @DisplayName("ensure the list isn't equal to another list")
    void checkIfTheListIsNotEqualsAnotherList(){
        Profile profile = new Profile("User");
        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        Account accountTwo = new Account("Joao", "yyyyyy@gmail.com", "44851114", profile);
        Account accountThree = new Account("Mario", "ttttt@gmail.com", "45635435435", profile);
        Account accountFour = new Account("Rui", "hhhhhh@gmail.com", "24324424", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        AccountList accountListTwo = new AccountList();
        accountListTwo.addAccount(accountThree);
        accountListTwo.addAccount(accountFour);

        assertFalse(accountList.equals(accountListTwo));
    }
}