package com.myapp.WishList.unitTestCases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.WishList.controller.WishListController;
import com.myapp.WishList.entity.Product;
import com.myapp.WishList.service.WishListService;
import com.myapp.WishList.utils.DataFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {


    @MockBean
    private WishListService wishListService;

    @Autowired
    private WishListController wishListController;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;


    @Test
    public void testEndpointGetWishlist() throws Exception {
        var user = DataFactory.buildUser();
        Set<Product> mockWishList = user.getWishList();

        when(wishListService.getWishList(user.getLogin())).thenReturn(mockWishList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/wishlist/person/{loginID}/list", user.getLogin())
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void testEndpointAddProductInTheWishlist() throws Exception {
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(6L);
        var req = DataFactory.buildRequest(user.getLogin(),product);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/wishlist/insert")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(req));

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testEndpointCheckIfAProductExist() throws Exception {
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(3L);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/wishlist/person/{loginID}/search/product/{codProduct}",user.getLogin()
                        ,product.getCodProduct())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isBoolean());
    }

    @Test
    public void testEndpointRemoveAProduct() throws Exception {
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(6L);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/wishlist/person/{loginID}/product/{codProduct}",user.getLogin()
                        ,product.getCodProduct())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
