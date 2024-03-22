package com.aggr.invest.service;

import com.aggr.invest.controller.dto.AccountResponseDto;
import com.aggr.invest.controller.dto.CreateAccountDto;
import com.aggr.invest.controller.dto.CreateUserDTO;
import com.aggr.invest.controller.dto.UpdateUserDTO;
import com.aggr.invest.entity.Account;
import com.aggr.invest.entity.BillingAddress;
import com.aggr.invest.entity.User;
import com.aggr.invest.repository.AccountRepository;
import com.aggr.invest.repository.BillingAddressRepository;
import com.aggr.invest.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    private AccountRepository accountRepository;

    private BillingAddressRepository billingAddressRepository;
    ;

    public UUID createUser(CreateUserDTO dto) {

        var u = new User(
                UUID.randomUUID(),
                dto.username(),
                dto.email(),
                dto.password(),
                Instant.now(),
                null
        );

        var userSaved = userRepository.save(u);

        return userSaved.getUserId();

    }

    public Optional<User> getUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));

    }

    public void deleteById(String userId) {

        UUID uId = UUID.fromString(userId);
        var userExists = userRepository.existsById(uId);

        if (userExists)
            userRepository.deleteById(uId);

    }

    public void updateUserById(String userId, UpdateUserDTO dto) {

        UUID uId = UUID.fromString(userId);
        var userEntity =userRepository.findById(uId);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (user.getUsername() != null) {
                user.setUsername(dto.username());
            }

            if (user.getPassword() != null) {
                user.setPassword(dto.password());
            }

            userRepository.save(user);
        }


    }

    public List<User> listUsers() {

        return userRepository.findAll();
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE));

        var account = new Account(
                UUID.randomUUID(),
                createAccountDto.description(),
                user,
                null,
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAddressRepository.save(billingAddress);
    }

    public List<AccountResponseDto> listAccounts(String userId) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE));

        return user.getAccounts()
                .stream()
                .map(ac -> new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription()))
                .toList();
    }
}
