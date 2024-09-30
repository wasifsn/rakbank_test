package com.example.signup.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AccountDTODiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AccountDTO#AccountDTO()}
     *   <li>{@link AccountDTO#setEmail(String)}
     *   <li>{@link AccountDTO#setName(String)}
     *   <li>{@link AccountDTO#getEmail()}
     *   <li>{@link AccountDTO#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        AccountDTO actualAccountDTO = new AccountDTO();
        actualAccountDTO.setEmail("jane.doe@example.org");
        actualAccountDTO.setName("Name");
        String actualEmail = actualAccountDTO.getEmail();

        // Assert that nothing has changed
        assertEquals("Name", actualAccountDTO.getName());
        assertEquals("jane.doe@example.org", actualEmail);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AccountDTO#AccountDTO(String, String)}
     *   <li>{@link AccountDTO#setEmail(String)}
     *   <li>{@link AccountDTO#setName(String)}
     *   <li>{@link AccountDTO#getEmail()}
     *   <li>{@link AccountDTO#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        AccountDTO actualAccountDTO = new AccountDTO("Name", "jane.doe@example.org");
        actualAccountDTO.setEmail("jane.doe@example.org");
        actualAccountDTO.setName("Name");
        String actualEmail = actualAccountDTO.getEmail();

        // Assert that nothing has changed
        assertEquals("Name", actualAccountDTO.getName());
        assertEquals("jane.doe@example.org", actualEmail);
    }
}
