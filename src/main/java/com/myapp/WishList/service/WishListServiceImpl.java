package com.myapp.WishList.service;

import com.myapp.WishList.entity.Product;
import com.myapp.WishList.entity.RequestDTO;
import com.myapp.WishList.entity.User;
import com.myapp.WishList.exception.FullWishListException;
import com.myapp.WishList.exception.NotFoundException;
import com.myapp.WishList.exception.ProductAlreadyExistException;
import com.myapp.WishList.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class WishListServiceImpl implements WishListService {
    @Autowired
    private UserRepository userRepository;

    private final Integer MAX_QUEUE_SIZE=20;

    @Override
    public Set<Product> getWishList(String email){
        log.info("Retrieving wishlist related to email: {}",email);
        User user = getUserByEmail(email).orElseThrow(NotFoundException::new);
        log.info("Wishlist related to email: {} retrieved successfully",email);
        return user.getWishList();
    }

    @Override
    public boolean checkProductExist(String email, Long codProduct){
        User user = getUserByEmail(email).orElseThrow(NotFoundException::new);
        return returnIfExists(user.getWishList(), codProduct) != null;

    }
    @Override
    @Transactional
    public void removeProduct(String email, Long codProduct){
        User user = getUserByEmail(email).orElseThrow(NotFoundException::new);
        Product chosenProduct = returnIfExists(user.getWishList(),codProduct);
        if(chosenProduct!=null){
            user.getWishList().remove(chosenProduct);
            saveOrUpdateUser(user);
        }else{
            throw new NotFoundException("Selected product not find in client`s wishlist ");
        }
    }

    private Product returnIfExists(Set<Product> whishList, Long codProduct ){
        for (Product p : whishList){
            if(p.getCodProduct().equals(codProduct)){
                return p;
            }
        }
        return null;
    }

    @Override
    public void addProductToClientList(RequestDTO req){
        validateReq(req);
        log.info("Searching user with email: {} ", req.getLogin());
        Optional<User> user = getUserByEmail(req.getLogin());
        if(user.isEmpty()){
            log.info("Creating user with email: {}", req.getLogin());
            User newUser = createNewUser(req);
            saveOrUpdateUser(newUser);
        }else if(checkAddProduct(user.get(),req.getProduct())){
            log.info("Adding product: {} into wishlist for email: {} ",req.getProduct().getName(), req.getLogin());
            user.get().getWishList().add(req.getProduct());
            saveOrUpdateUser(user.get());
        }
    }

    private boolean checkAddProduct(User u, Product p){
        if(u.getWishList().size()>=MAX_QUEUE_SIZE){
            throw new FullWishListException();
        }else if(u.getWishList().contains(p)){
            throw new ProductAlreadyExistException();
        }
        return true;
    }

    private Optional<User> getUserByEmail(String email){
        return userRepository.findUserByLogin(email);
    }

    @Transactional
    private User saveOrUpdateUser(User user){
        return userRepository.save(user);
    }
    private User createNewUser(RequestDTO req){
        Set<Product> products = new HashSet<>();
        products.add(req.getProduct());
        return User.builder()
                .login(req.getLogin())
                .wishList(products)
                .build();
    }

    private void validateReq(RequestDTO req) {
        if( req.getLogin().isBlank()){
            throw new NotFoundException("User Login is empty");
        } else if (Objects.isNull(req.getProduct())) {
            throw new NotFoundException("Product is empty");
        }
    }
}
