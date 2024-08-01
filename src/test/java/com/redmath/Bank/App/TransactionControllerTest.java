package com.redmath.Bank.App;

import com.redmath.Bank.App.Transaction.Transaction;
import com.redmath.Bank.App.Transaction.TransactionService;
import com.redmath.Bank.App.User.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;
    @Autowired
    private ObjectMapper objectMapper;
    @Order(1)
    @Test
    @WithMockUser
    public void getAllTransation() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transactions/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    @Order(2)
    @WithMockUser
    public void testCreateTransactionUserNotFound() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        User user= new User(-1L);
        transaction.setReceiver(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("User not found"));
    }
    @Test
    @Order(3)
    @WithMockUser
    public void testCreateTransactionSuccess() throws Exception {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setReceiver(new User(12L));
        when(transactionService.checkUserExist(any(Long.class))).thenReturn(true);
        when(transactionService.createTransaction(any(Transaction.class))).thenReturn(transaction);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    @Test
    @Order(4)
    @WithMockUser
    public void testGetTransactionUser() throws Exception {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        when(transactionService.allTransactionUser(any(Long.class))).thenReturn(Collections.singletonList(transaction));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }
}
