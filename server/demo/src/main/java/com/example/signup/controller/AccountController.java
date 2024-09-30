package com.example.signup.controller;

import com.example.signup.dto.AccountCreationDTO; // DTO for user creation
import com.example.signup.dto.AccountDTO; // DTO for user retrieval
import com.example.signup.exception.UserCreationException; // Import custom exception
import com.example.signup.service.AccountService;
import io.swagger.v3.oas.annotations.Operation; // Import for Swagger documentation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Endpoint to create a new user
    @Operation(summary = "Create a new user", description = "Creates a new user account with the provided details.")
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody AccountCreationDTO accountCreationDTO) {
        try {
            accountService.createAccount(accountCreationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Send error message to UI
        }
    }

    // Endpoint to get all users
    @Operation(summary = "Get all users", description = "Retrieves a list of all user accounts.")
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllUsers() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Endpoint to get user details by ID
    @Operation(summary = "Get user by ID", description = "Retrieves the details of a user account by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getUserById(@PathVariable Long id) {
        AccountDTO account = accountService.getAccountById(id);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint to update user details by ID
    @Operation(summary = "Update user details", description = "Updates the details of a user account by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody AccountDTO accountDTO) {
        boolean updated = accountService.updateAccount(id, accountDTO);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint to change user password by ID
    @Operation(summary = "Change user password", description = "Changes the password of a user account by its ID.")
    @PatchMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody String newPassword) {
        boolean changed = accountService.changePassword(id, newPassword);
        if (changed) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint to delete user by ID
    @Operation(summary = "Delete user", description = "Deletes a user account by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = accountService.deleteAccount(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
