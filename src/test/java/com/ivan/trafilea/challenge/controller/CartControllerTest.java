package com.ivan.trafilea.challenge.controller;

import com.ivan.trafilea.challenge.AbstractTest;
import com.ivan.trafilea.challenge.model.Cart;
import com.ivan.trafilea.challenge.model.User;
import com.ivan.trafilea.challenge.service.*;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest extends AbstractTest {

    @Autowired
    CartController cartController;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void createCartTest() throws Exception
    {
        ResponseEntity<Object> responseEntity = cartController.newCart("user1");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test(expected = RuntimeException.class)
    public void createCartTest_Fail() throws Exception
    {
        ResponseEntity<Object> responseEntity = cartController.newCart("user18");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
