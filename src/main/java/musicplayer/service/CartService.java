package musicplayer.service;

import musicplayer.entity.*;
import musicplayer.exception.AlreadyInCartException;
import musicplayer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService  {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;
    @Autowired
    private UserSongRepository userSongRepository;
    @Autowired
    UserService userService;



    @Transactional
    public void addSongToCart(Long theSongId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cart activeCart =cartRepository.getActiveCart(true,this.userService.findByUsername(authentication.getName()));

        UserSong userSong=userSongRepository.getUserSong(songRepository.findById(theSongId),this.userService.findByUsername(authentication.getName()));
        if(userSong!=null){
            throw new AlreadyInCartException("Song Already Purchased");
        }
        if(activeCart==null){
            Cart newCart=new Cart();
            newCart.setUser(this.userService.findByUsername(authentication.getName()));
            newCart.setActive(true);
            cartRepository.save(newCart);
            activeCart =newCart;
        } else{
            CartItem cartItem=cartItemRepository.getCartItem(songRepository.findById(theSongId),activeCart);
            if(cartItem!=null){
                throw new AlreadyInCartException(cartItem.getSong().getSongName() + " Already In Cart.");
            }
        }
        CartItem newItem=new CartItem();
        newItem.setCart(activeCart);
        newItem.setSong(songRepository.findById(theSongId));
        cartItemRepository.save(newItem);
    }
    @Transactional
    public List <CartItem> getMyCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        Cart activeCart =this.getMyCart();
        if(activeCart!=null) {
            cartItems = cartItemRepository.findByCart(activeCart);
        }
        return cartItems;
    }
    @Transactional
    public List <CartItem> getPurchaseReqItems(Long purchaseId) {
        PurchaseRequest purchaseRequest=purchaseRequestRepository.findById(purchaseId);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems = cartItemRepository.findByCart(purchaseRequest.getCart());
        return  cartItems;
    }

    @Transactional
    public void purchaseCart() {
        List<CartItem> cartItems=this.getMyCartItems();
        boolean ans = cartItems.isEmpty();
        if(!cartItems.isEmpty()) {
            PurchaseRequest newPurchaseRequest = new PurchaseRequest();
            Cart myCart=this.getMyCart();
            newPurchaseRequest.setCart(myCart);
            newPurchaseRequest.setStatus(1);
            newPurchaseRequest.setCreatedOn(new Date());
            purchaseRequestRepository.save(newPurchaseRequest);
            myCart.setActive(false);
            cartRepository.save(myCart);
        }
    }

    @Transactional
    public Cart getMyCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cart activeCart =cartRepository.getActiveCart(true,this.userService.findByUsername(authentication.getName()));
        return activeCart;
    }
    @Transactional
    public List <PurchaseRequest> getPendingPurchaseRequests(int status) {
        return purchaseRequestRepository.findByStatus(1);
    }
    @Transactional
    public void updatePurchaseReqStatus(int status,Long reqId) {

        PurchaseRequest purchaseRequest=purchaseRequestRepository.findById(reqId);
        purchaseRequest.setStatus(status);
        purchaseRequestRepository.save(purchaseRequest);
        User purchasedUser=purchaseRequest.getCart().getUser();
        if(status==2) {
            List<CartItem> cartItems = cartItemRepository.findByCart(purchaseRequest.getCart());
            for (CartItem cartItem : cartItems){
                UserSong newUserSong = new UserSong();
                newUserSong.setUser(purchasedUser);
                newUserSong.setSong(cartItem.getSong());
                userSongRepository.save(newUserSong);
            }
        }
    }
    @Transactional
    public void deleteCartItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }


}
