//package com.deskbooking.deskbooking;
//
//import com.deskbooking.deskbooking.controller.UserController;
//import com.deskbooking.deskbooking.filter.JwtFilter;
//import com.deskbooking.deskbooking.model.User;
//import com.deskbooking.deskbooking.security.SecurityConfig;
//import com.deskbooking.deskbooking.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Getter;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.autoconfigure.filter.FilterAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.test.context.support.WithAnonymousUser;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.RequestPostProcessor;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.servlet.*;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.hamcrest.Matchers.hasSize;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
//@ContextConfiguration(classes = {FilterChainProxy.class})
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    @Qualifier
//    private UserService userService;
//
//    @MockBean
//    private JwtFilter jwtFilter;
//
//    @MockBean
//    private SecurityConfig securityConfig;
//
//    @MockBean(name = "springSecurityFilterChain")
//    private Filter filter;
////    = new Filter() {
////        @Override
////        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////
////        }
////        public void getFilters(MockHttpServletRequest mockHttpServletRequest){}
////    };
//
//    @Test
//    public void itShouldAddUser() throws Exception {
//        User user = new User(1, "aaa@gmail.com", "sfdafAAaaa", "Lucian",
//                "Trasca", "0765222222", 0, 1, new ArrayList<>());
//        ObjectMapper objectMapper = new ObjectMapper();
//        mockMvc.perform(post("/register")
//                        .content(objectMapper.writeValueAsString(user))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test @WithAnonymousUser
//    public void itShouldGetAllUsers() throws Exception {
//        User user = new User(1, "aaa@gmail.com", "sfdafAAaaa", "Lucian",
//                "Trasca", "0765222222", 0, 1, new ArrayList<>());
//        List<User> allUsers = List.of(user);
//        given(userService.getAllUsers()).willReturn(allUsers);
//        mockMvc.perform(get("/user/getAll")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is(user.getLastName())));
//    }
//
//    @Test @WithMockUser
//    public void itShouldGetUserById() throws Exception {
//        User user = new User(1, "aaa@gmail.com", "sfdafAAaaa", "Lucian",
//                "Trasca", "0765222222", 0, 1, new ArrayList<>());
//        given(userService.findUserById(1)).willReturn(user);
//        mockMvc.perform(get("/user/getById/1")
//                    .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("lastName", Matchers.is(user.getLastName())));
//    }
//}
