package org.switch2022.project.service;

import org.springframework.stereotype.Service;
import org.switch2022.project.mapper.NewAccountDTO;
import org.switch2022.project.mapper.NewAccountDTOMapper;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.account.IAccountFactory;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.irepositories.IAccountRepository;

@Service
public class AccountService {

    private final IAccountFactory accountFactory;
    private final IAccountRepository accountRepository;
    private final NewAccountDTOMapper accountDTOMapper;

    /**
     * Constructor for AccountRepository.
     * @param accountFactory injected parameter
     * @param accountRepository injected parameter
     * @param accountDTOMapper injected parameter
     */


    public AccountService(IAccountFactory accountFactory, IAccountRepository accountRepository, NewAccountDTOMapper accountDTOMapper) {
        if (accountFactory == null) {
            throw new IllegalArgumentException("AccountFactory must not be null.");
        }
        if (accountRepository == null) {
            throw new IllegalArgumentException("AccountRepository must not be null.");
        }
        this.accountFactory = accountFactory;
        this.accountRepository = accountRepository;
        this.accountDTOMapper = accountDTOMapper;
    }

    /**
     * Creates account from data provided by the DTO
     * @param newAccountDTO containing the necessary data
     * @return the updated NewAccountDTO with the saved account id
     */

    public NewAccountDTO createAccount (NewAccountDTO newAccountDTO) {
        //creating the value objects from the DTO
        AccountID accountID = newAccountDTO.accountID;
        Email email = newAccountDTO.email;
        Name name = newAccountDTO.name;
        PhoneNumber phoneNumber = newAccountDTO.phoneNumber;
        Photo photo = newAccountDTO.photo;
        ProfileName profile = newAccountDTO.profile;

        //confirm if the email already exists in the repository
        String emailPrimitive = newAccountDTO.email.toString();

        boolean emailAlreadyExists = accountRepository.existsByEmail(emailPrimitive);

        if(emailAlreadyExists) {
            throw new IllegalArgumentException("Account with given email already exists");
        }

        //creating the account using the accountFactory
        AccountDDD account = accountFactory.createAccount(accountID, email, name, phoneNumber, photo, profile);

        //saving the account in the repository
        AccountDDD savedAccount = accountRepository.save(account);

        // transforming the savedAccount to a dto
        NewAccountDTO accountDTO = accountDTOMapper.toDTO(savedAccount);

        //update the accountID
        accountDTO.accountID = savedAccount.identity();

        return accountDTO;
    }
}
