package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.mapper.AccountDTO;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.Profile;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ListAccountsControllerTest {

    @Test
    @DisplayName("Test whether the Controller can create the Account DTO list")
    void testForCreatingAccountDTOList(){
        Profile profile = new Profile("User");
        Profile profileTest = new Profile("Manager");

        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profileTest);
        Account accountThree = new Account("Pedro","zzzzz@gmail.com","22255588", profileTest);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        accountList.addAccount(accountThree);

        ListAccountsController controller = new ListAccountsController(accountList);

        controller.listAccounts(accountList);

        assertNotNull(controller);
    }

    /***
     * Entering an empty list will give a message of error.
     */
    @Test
    void checkTheEmptyList(){
        AccountList accountList = new AccountList();
        AtomicReference<ListAccountsController> controller = null;
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> controller.set(new ListAccountsController(accountList)));
        String resultMessage = result.getMessage();

        // assert
        assertEquals("List is empty", resultMessage);
    }

    /***
     * The first assertTrue of the method below will check if the result is an instance of the
     * List class and not another type of objet.
     * The second assertTrue checks if the first element of the returned list is
     * an instance of the AccountDTO class.
     * If the tests come out as failing, then it means there is an issue with the object
     * or if the list received is empty.
     */
    @Test
    public void testListAccounts_ShouldReturnListOfAccountDTO() {
        Profile profile = new Profile("User");
        Profile profileTest = new Profile("Manager");

        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profileTest);
        Account accountThree = new Account("Pedro","zzzzz@gmail.com","22255588", profileTest);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        accountList.addAccount(accountThree);

        // add some accounts to the list
        ListAccountsController controller = new ListAccountsController(accountList);
        List<AccountDTO> result = controller.listAccounts(accountList);
        assertTrue(result instanceof List);
        assertTrue(result.get(0) instanceof AccountDTO);
    }

    /***
     * This test will ensure the resulting list doesn't come out as empty as well.
     */
    @Test
    public void testListAccounts_ShouldReturnNonEmptyList() {
        Profile profile = new Profile("User");
        Profile profileTest = new Profile("Manager");

        Account account = new Account("Joana", "xxxxx@gmail.com", "22255588", profile);
        Account accountTwo = new Account("Joao", "yyyyyy@gmail.com", "44851114", profileTest);
        Account accountThree = new Account("Pedro", "zzzzz@gmail.com", "22255588", profileTest);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        accountList.addAccount(accountThree);

        ListAccountsController controller = new ListAccountsController(accountList);
        List result = controller.listAccounts(accountList);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}