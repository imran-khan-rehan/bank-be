package com.redmath.Bank.App;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.redmath.Bank.App.User.User;
//import com.redmath.Bank.App.User.UserService;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.junit.jupiter.api.Order;
//
//
//import static org.mockito.Mockito.*;
//        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Order(1)
//    @Test
//    @WithMockUser
//
//    void shouldReturnAllUsers() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//    }
//    @Order(2)
//    @Test
//    @WithMockUser
//
//    public void testGetUserDetails() throws Exception {
//
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("test@example.com");
//        user.setRole("USER");
//        user.setName("Test User");
//        when(userService.findUser(1L)).thenReturn(user);
//
//
//        mockMvc.perform(get("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@example.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("USER"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test User"));
//    }
//    @Order(3)
//    @Test
//    @WithMockUser
//
//    public void testUpdateUser() throws Exception {
//
//        User existingUser = new User();
//        existingUser.setId(1L);
//        existingUser.setEmail("test@example.com");
//        existingUser.setName("Test User");
//        User updatedUser = new User();
//        updatedUser.setId(1L);
//        updatedUser.setEmail("updated@example.com");
//        updatedUser.setName("Updated User");
//        when(userService.findUser(1L)).thenReturn(existingUser);
//        when(userService.alreadyRegister("updated@example.com")).thenReturn(false);
//        when(userService.save(any(User.class))).thenReturn(updatedUser);
//
//
//        mockMvc.perform(put("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"id\": 1,\"role\": \"USER\",\"password\":null,\"name\": \"Updated User\", \"email\": \"updated@example.com\"}")
//                ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("updated@example.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated User"));
//    }
//    @Order(4)
//    @Test
//    @WithMockUser
//
//    public void testUpdateUserNotFound() throws Exception {
//        User updatedUser = new User();
//        updatedUser.setId(1L);
//        updatedUser.setEmail("updated@example.com");
//        updatedUser.setName("Updated User");
//        when(userService.findUser(1L)).thenReturn(null);
//
//        mockMvc.perform(put("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedUser)))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("User not found"));
//    }
//
//@Order(5)
//    @Test
//@WithMockUser
//
//    public void testDeleteUser() throws Exception {
//        User existingUser = new User();
//        existingUser.setId(1L);
//        when(userService.findUser(1L)).thenReturn(existingUser);
//
//
//        mockMvc.perform(delete("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//    @Order(6)
//    @Test
//    @WithMockUser
//
//    public void testDeleteUserNotFound() throws Exception {
//        when(userService.findUser(1L)).thenReturn(null);
//
//        mockMvc.perform(delete("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//}

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    @WithMockUser
    void shouldReturnAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Order(2)
    @Test
    @WithMockUser
    void shouldReturnUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("user@user.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("$2a$12$oM/hqR2TGWbp1fQwlgH8t.nmGSy2AuKTz92ro/LPUiDkXA9IrK0fi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", Matchers.is("USER")));
    }

    @Order(3)
    @Test
    @WithMockUser
    void shouldUpdateUser() throws Exception {
        String updatedUserJson = """
            {
                "name": "Updated User",
                "email": "updated@user.com",
                "password": "newPassword"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("updated@user.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Updated User")));
    }

    @Order(4)
    @Test
    @WithMockUser
    void shouldReturnNotFoundWhenUpdatingNonExistingUser() throws Exception {
        String updatedUserJson = """
            {
                "name": "Updated User",
                "email": "updated@user.com",
                "password": "newPassword"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
//                .andExpect(MockMvcResultMatchers.content().string("User not found"));
    }

    @Order(5)
    @Test
    @WithMockUser
    void shouldReturnConflictWhenUpdatingWithExistingEmail() throws Exception {
        String updatedUserJson = """
            {
                "name": "User",
                "email": "admin@admin.com",
                "password": "newPassword"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Email already exists"));
    }

    @Order(6)
    @Test
    @WithMockUser
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Order(7)
    @Test
    @WithMockUser
    void shouldReturnNotFoundWhenDeletingNonExistingUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
