package com.redmath.Bank.App;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmath.Bank.App.Balance.Balance;
import com.redmath.Bank.App.Balance.BalanceService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BalanceService balanceService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    @Order(1)
    @WithMockUser
    public void testGetAllBalances() throws Exception {
        Balance balance = new Balance();
        balance.setId(1L);
        when(balanceService.getAll()).thenReturn(Collections.singletonList(balance));


        mockMvc.perform(get("/api/v1/balance/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }

    @Test
    @Order(2)
    @WithMockUser
    public void testCheckAmount() throws Exception {
        Balance balance = new Balance();
        balance.setId(1L);
        when(balanceService.checkAmount(any(Long.class))).thenReturn(balance);

        mockMvc.perform(get("/api/v1/balance/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    @Order(3)
    @WithMockUser
    public void testAddAmount() throws Exception {
        Balance balance = new Balance();
        balance.setId(1L);
        Map<String, Object> request = new HashMap<>();
        request.put("userId", 1L);
        request.put("amount", 100.0);
        when(balanceService.addAmount(any(Long.class), any(Double.class))).thenReturn(balance);

        mockMvc.perform(post("/api/v1/balance/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
}
