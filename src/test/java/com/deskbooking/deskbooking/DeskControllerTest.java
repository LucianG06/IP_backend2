//package com.deskbooking.deskbooking;
//
//import com.deskbooking.deskbooking.controller.DeskController;
//import com.deskbooking.deskbooking.filter.JwtFilter;
//import com.deskbooking.deskbooking.model.Desk;
//import com.deskbooking.deskbooking.security.SecurityConfig;
//import com.deskbooking.deskbooking.service.DeskService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithAnonymousUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.servlet.Filter;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(DeskController.class)
//public class DeskControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DeskService deskService;
//
//    @MockBean
//    private JwtFilter jwtFilter;
//
//    @MockBean
//    private SecurityConfig securityConfig;
//
//    @MockBean(name = "springSecurityFilterChain")
//    private Filter filter;
//
//    @Test
//    public void itShouldAddDesk() throws Exception {
//        Desk desk = new Desk(1, "desk1");
//        ObjectMapper objectMapper = new ObjectMapper();
//        mockMvc.perform(post("/desk/create")
//                        .content(objectMapper.writeValueAsString(desk))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test @WithAnonymousUser
//    public void itShouldGetAllDesks() throws Exception {
//        Desk desk1 = new Desk(1, "desk1");
//        Desk desk2 = new Desk(1, "desk2");
//        List<Desk> allDesks = Arrays.asList(desk1, desk2);
//        given(deskService.getAllDesks()).willReturn(allDesks);
//        mockMvc.perform(get("/desk/getAll")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(desk1.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is(desk2.getName())));
//    }
//
//    @Test @WithAnonymousUser
//    public void itShouldGetDeskById() throws Exception {
//        Desk desk = new Desk(1, "desk1");
//        given(deskService.findDeskById(1)).willReturn(desk);
//        mockMvc.perform(get("/desk/getById/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is(desk.getName())));
//    }
//}
