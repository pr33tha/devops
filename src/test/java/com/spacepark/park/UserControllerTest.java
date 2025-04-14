package com.spacepark.park;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.spacepark.park.domain.User;
import com.spacepark.park.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testSignup_Success() throws Exception {
        mockMvc.perform(post("/signup")
                        .param("username", "newuser")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testSignup_UsernameAlreadyExists() throws Exception {
        doThrow(new RuntimeException("Username already exists")).when(userService).registerUser(any(User.class));

        mockMvc.perform(post("/signup")
                        .param("username", "existinguser")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Username already exists"));
    }

    @Test
    public void testLogin_Success() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "validuser")
                        .param("password", "correctpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void testLogin_InvalidCredentials() throws Exception {
        doThrow(new RuntimeException("Invalid username or password"))
                .when(userService).loginUser(anyString(), anyString());

        mockMvc.perform(post("/login")
                        .param("username", "invaliduser")
                        .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "Invalid username or password"));
    }

    @Test
    public void testHomePageAccess() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testSignupPageAccess() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"));
    }

    @Test
    public void testParkPageAccess() throws Exception {
        mockMvc.perform(get("/park"))
                .andExpect(status().isOk())
                .andExpect(view().name("park"));
    }

    @Test
    public void testParkDetailsSubmission() throws Exception {
        mockMvc.perform(post("/park")
                        .param("parkingName", "Main Street Parking"))
                .andExpect(status().isOk())
                .andExpect(view().name("park"))
                .andExpect(model().attributeExists("parkingName"))
                .andExpect(model().attribute("parkingName", "Main Street Parking"));
    }
}
