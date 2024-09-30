package com.example.signup.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AccountDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Account#setEmail(String)}
     *   <li>{@link Account#setId(Long)}
     *   <li>{@link Account#setName(String)}
     *   <li>{@link Account#setPassword(String)}
     *   <li>{@link Account#getEmail()}
     *   <li>{@link Account#getId()}
     *   <li>{@link Account#getName()}
     *   <li>{@link Account#getPassword()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Account account = new Account();

        // Act
        account.setEmail("jane.doe@example.org");
        account.setId(1L);
        account.setName("Name");
        account.setPassword("iloveyou");
        String actualEmail = account.getEmail();
        Long actualId = account.getId();
        String actualName = account.getName();

        // Assert that nothing has changed
        assertEquals("Name", actualName);
        assertEquals("iloveyou", account.getPassword());
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals(1L, actualId.longValue());
    }
}
