package com.myapp.WishList.unitTestCases;

import com.myapp.WishList.entity.RequestDTO;
import com.myapp.WishList.exception.FullWishListException;
import com.myapp.WishList.exception.NotFoundException;
import com.myapp.WishList.exception.ProductAlreadyExistException;
import com.myapp.WishList.repository.UserRepository;
import com.myapp.WishList.service.WishListServiceImpl;
import com.myapp.WishList.utils.DataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @InjectMocks
    private WishListServiceImpl wishService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldAddProductSuccessfully(){
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(6L);

        RequestDTO req = DataFactory.buildRequest(user.getLogin(),product);

        when(userRepository.findUserByLogin(any())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any())).thenReturn(user);

        wishService.addProductToClientList(req);
        verify(userRepository,times(1)).save(any());
    }

    @Test
    public void shouldAddProductSuccessfullyEvenIfClientDontExistInDataBase(){
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(6L);

        RequestDTO req = DataFactory.buildRequest(user.getLogin(),product);

        when(userRepository.findUserByLogin(any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(user);

        wishService.addProductToClientList(req);
        verify(userRepository,times(1)).save(any());
    }

    @Test
    public void shouldNotSaveProduct(){
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(3L);

        RequestDTO req = DataFactory.buildRequest(user.getLogin(),product);
        when(userRepository.findUserByLogin(any())).thenReturn(Optional.ofNullable(user));

        Assertions.assertThrows(ProductAlreadyExistException.class,()-> wishService.addProductToClientList(req));
    }

    @Test
    public void shouldThrowExceptionWhenTryToAddProductWithEmptyProduct()  {
        RequestDTO req = DataFactory.buildRequest("Cavaquinho",null);
        Assertions.assertThrows(NotFoundException.class,()-> wishService.addProductToClientList(req));
    }

    @Test
    public void shouldThrowExceptionWhenTryToAddProductWithEmptyLoginID()  {
        RequestDTO req = DataFactory.buildRequest("",null);
        Assertions.assertThrows(NotFoundException.class,()-> wishService.addProductToClientList(req));
    }

    @Test
    public void shouldThrowExceptionWhenTryToAddProductWithFullQueue()  {
        var user = DataFactory.buildUser();
        var newProduct = DataFactory.buildProduct(21L);
        user.setWishList(DataFactory.getBachOfProducts(20));

        when(userRepository.findUserByLogin(any())).thenReturn(Optional.ofNullable(user));

        RequestDTO req = DataFactory.buildRequest(user.getLogin(),newProduct);
        Assertions.assertThrows(FullWishListException.class,()-> wishService.addProductToClientList(req));
    }

    @Test
    public void shouldReturnWishListSuccessfully(){
        var user = DataFactory.buildUser();
        when(userRepository.findUserByLogin(any())).thenReturn(Optional.ofNullable(user));

        wishService.getWishList(user.getLogin());

        verify(userRepository,times(1)).findUserByLogin(any());
    }

    @Test
    public void shouldThrowExceptionWhenTryReturnWishList(){
        var user = DataFactory.buildUser();

        when(userRepository.findUserByLogin(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class,()-> wishService.getWishList(user.getLogin()));
        verify(userRepository,times(1)).findUserByLogin(any());
    }


    @Test
    public void shouldReturnTrueWhenSearchingForProductInWishList(){
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(2L);
        when(userRepository.findUserByLogin(any())).thenReturn(Optional.of(user));

        Assertions.assertTrue(wishService.checkProductExist(user.getLogin(),product.getCodProduct()));
        verify(userRepository,times(1)).findUserByLogin(any());
    }

    @Test
    public void shouldReturnFalseWhenSearchingForProductInWishList(){
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(22L);
        when(userRepository.findUserByLogin(any())).thenReturn(Optional.of(user));

        Assertions.assertFalse(wishService.checkProductExist(user.getLogin(),product.getCodProduct()));
        verify(userRepository,times(1)).findUserByLogin(any());
    }

    @Test
    public void shouldRemoveProductSuccessfully(){
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(3L);

        when(userRepository.findUserByLogin(any())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any())).thenReturn(user);

        wishService.removeProduct(user.getLogin(),product.getCodProduct());
        verify(userRepository,times(1)).save(any());
    }

    @Test
    public void shouldThrowExceptionWhenRemoveProductAndItIsNotInTheList(){
        var user = DataFactory.buildUser();
        var product = DataFactory.buildProduct(20L);

        when(userRepository.findUserByLogin(any())).thenReturn(Optional.ofNullable(user));

        Assertions.assertThrows(NotFoundException.class,()-> wishService.removeProduct(user.getLogin(),product.getCodProduct()));
    }

}
