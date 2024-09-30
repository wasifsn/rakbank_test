package com.example.signup.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AccountCreationDTODiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AccountCreationDTO#setEmail(String)}
     *   <li>{@link AccountCreationDTO#setName(String)}
     *   <li>{@link AccountCreationDTO#setPassword(String)}
     *   <li>{@link AccountCreationDTO#getEmail()}
     *   <li>{@link AccountCreationDTO#getName()}
     *   <li>{@link AccountCreationDTO#getPassword()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();

        // Act
        accountCreationDTO.setEmail("jane.doe@example.org");
        accountCreationDTO.setName("Name");
        accountCreationDTO.setPassword("iloveyou");
        String actualEmail = accountCreationDTO.getEmail();
        String actualName = accountCreationDTO.getName();

        // Assert that nothing has changed
        assertEquals("Name", actualName);
        assertEquals("iloveyou", accountCreationDTO.getPassword());
        assertEquals("jane.doe@example.org", actualEmail);
    }
}
