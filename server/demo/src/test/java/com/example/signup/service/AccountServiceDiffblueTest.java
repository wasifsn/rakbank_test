package com.example.signup.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.signup.entity.Account;
import com.example.signup.exception.UserCreationException;
import com.example.signup.repository.AccountRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class AccountServiceDiffblueTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    /**
     * Method under test: {@link AccountService#changePassword(Long, String)}
     */
    @Test
    void testChangePassword() {
        // Arrange
        Account account = new Account();
        account.setEmail("jane.doe@example.org");
        account.setId(1L);
        account.setName("Name");
        account.setPassword("iloveyou");
        Optional<Account> ofResult = Optional.of(account);

        Account account2 = new Account();
        account2.setEmail("jane.doe@example.org");
        account2.setId(1L);
        account2.setName("Name");
        account2.setPassword("iloveyou");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        boolean actualChangePasswordResult = accountService.changePassword(1L, "iloveyou");

        // Assert
        verify(accountRepository).findById(eq(1L));
        verify(accountRepository).save(isA(Account.class));
        assertTrue(actualChangePasswordResult);
    }

    /**
     * Method under test: {@link AccountService#changePassword(Long, String)}
     */
    @Test
    void testChangePassword2() {
        // Arrange
        Account account = new Account();
        account.setEmail("jane.doe@example.org");
        account.setId(1L);
        account.setName("Name");
        account.setPassword("iloveyou");
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.save(Mockito.<Account>any())).thenThrow(new UserCreationException("An error occurred"));
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserCreationException.class, () -> accountService.changePassword(1L, "iloveyou"));
        verify(accountRepository).findById(eq(1L));
        verify(accountRepository).save(isA(Account.class));
    }

    /**
     * Method under test: {@link AccountService#changePassword(Long, String)}
     */
    @Test
    void testChangePassword3() {
        // Arrange
        Optional<Account> emptyResult = Optional.empty();
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act
        boolean actualChangePasswordResult = accountService.changePassword(1L, "iloveyou");

        // Assert
        verify(accountRepository).findById(eq(1L));
        assertFalse(actualChangePasswordResult);
    }

    /**
     * Method under test: {@link AccountService#changePassword(Long, String)}
     */
    @Test
    void testChangePassword4() {
        // Arrange, Act and Assert
        assertThrows(UserCreationException.class, () -> accountService.changePassword(1L, "U"));
    }
}
