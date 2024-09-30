package com.example.signup.service;

import com.example.signup.dto.AccountCreationDTO; // Separate DTO for creation
import com.example.signup.dto.AccountDTO; // DTO for retrieval
import com.example.signup.entity.Account;
import com.example.signup.exception.UserCreationException;
import com.example.signup.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Use a custom pattern for email validation
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{8,}$");

    public void createAccount(@Valid AccountCreationDTO accountCreationDTO) {
        validateAccountCreationDTO(accountCreationDTO); // Validate the DTO before creating

        Account account = new Account();
        account.setName(accountCreationDTO.getName());
        account.setEmail(accountCreationDTO.getEmail());
        account.setPassword(passwordEncoder.encode(accountCreationDTO.getPassword()));
        accountRepository.save(account);
    }

    private void validateAccountCreationDTO(AccountCreationDTO accountCreationDTO) {
        if (accountCreationDTO.getName() == null || accountCreationDTO.getName().isEmpty() || accountCreationDTO.getName().length() > 50) {
            throw new UserCreationException("Name is mandatory and must be at most 50 characters.");
        }

        if (accountCreationDTO.getEmail() == null || accountCreationDTO.getEmail().isEmpty() || !EMAIL_PATTERN.matcher(accountCreationDTO.getEmail()).matches()) {
            throw new UserCreationException("Valid email is mandatory.");
        }

        if (accountCreationDTO.getPassword() == null || accountCreationDTO.getPassword().isEmpty() || !PASSWORD_PATTERN.matcher(accountCreationDTO.getPassword()).matches()) {
            throw new UserCreationException("Password must be at least 8 characters and contain only alphabets and numbers.");
        }
    }

    public List<AccountDTO> getAllAccounts() {
        // Convert Account entities to AccountDTOs to exclude passwords
        return accountRepository.findAll().stream()
                .map(account -> new AccountDTO(account.getName(), account.getEmail())) // Adjust according to your DTO structure
                .toList();
    }

    public AccountDTO getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(acc -> new AccountDTO(acc.getName(), acc.getEmail())).orElse(null);
    }

    public boolean updateAccount(Long id, AccountDTO accountDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setName(accountDTO.getName());
            account.setEmail(accountDTO.getEmail());
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public boolean changePassword(Long id, String newPassword) {
        if (!PASSWORD_PATTERN.matcher(newPassword).matches()) {
            throw new UserCreationException("New password must be at least 8 characters and contain only alphabets and numbers.");
        }

        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setPassword(passwordEncoder.encode(newPassword));
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public boolean deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
