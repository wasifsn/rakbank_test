package com.example.signup.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.example.signup.dto.AccountCreationDTO;
import com.example.signup.dto.AccountDTO;
import com.example.signup.exception.UserCreationException;
import com.example.signup.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
class AccountControllerDiffblueTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    /**
     * Method under test: {@link AccountController#changePassword(Long, String)}
     */
    @Test
    void testChangePassword() throws Exception {
        // Arrange
        when(accountService.changePassword(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(true);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.patch("/api/v1/users/{id}/password", 1L)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AccountController#changePassword(Long, String)}
     */
    @Test
    void testChangePassword2() throws Exception {
        // Arrange
        when(accountService.changePassword(Mockito.<Long>any(), Mockito.<String>any())).thenReturn(false);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.patch("/api/v1/users/{id}/password", 1L)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountController#createUser(AccountCreationDTO)}
     */
    @Test
    void testCreateUser() throws Exception {
        // Arrange
        doNothing().when(accountService).createAccount(Mockito.<AccountCreationDTO>any());

        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        accountCreationDTO.setEmail("jane.doe@example.org");
        accountCreationDTO.setName("Name");
        accountCreationDTO.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(accountCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * Method under test: {@link AccountController#createUser(AccountCreationDTO)}
     */
    @Test
    void testCreateUser2() throws Exception {
        // Arrange
        doThrow(new UserCreationException("An error occurred")).when(accountService)
                .createAccount(Mockito.<AccountCreationDTO>any());

        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        accountCreationDTO.setEmail("jane.doe@example.org");
        accountCreationDTO.setName("Name");
        accountCreationDTO.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(accountCreationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("An error occurred"));
    }

    /**
     * Method under test: {@link AccountController#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() throws Exception {
        // Arrange
        when(accountService.deleteAccount(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link AccountController#deleteUser(Long)}
     */
    @Test
    void testDeleteUser2() throws Exception {
        // Arrange
        when(accountService.deleteAccount(Mockito.<Long>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountController#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        // Arrange
        when(accountService.getAllAccounts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccountController#getUserById(Long)}
     */
    @Test
    void testGetUserById() throws Exception {
        // Arrange
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(new AccountDTO("Name", "jane.doe@example.org"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"name\":\"Name\",\"email\":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link AccountController#getUserById(Long)}
     */
    @Test
    void testGetUserById2() throws Exception {
        // Arrange
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountController#updateUser(Long, AccountDTO)}
     */
    @Test
    void testUpdateUser() throws Exception {
        // Arrange
        when(accountService.updateAccount(Mockito.<Long>any(), Mockito.<AccountDTO>any())).thenReturn(true);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("jane.doe@example.org");
        accountDTO.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(accountDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AccountController#updateUser(Long, AccountDTO)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        // Arrange
        when(accountService.updateAccount(Mockito.<Long>any(), Mockito.<AccountDTO>any())).thenReturn(false);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("jane.doe@example.org");
        accountDTO.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(accountDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
