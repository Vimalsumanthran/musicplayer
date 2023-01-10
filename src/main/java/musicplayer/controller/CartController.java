package musicplayer.controller;

import musicplayer.entity.CartItem;
import musicplayer.entity.Song;
import musicplayer.entity.SongGenre;
import musicplayer.entity.User;
import musicplayer.exception.AlreadyInCartException;
import musicplayer.exception.ResourceNotFoundException;
import musicplayer.service.CartService;
import musicplayer.service.SongGenreService;
import musicplayer.service.SongService;
import musicplayer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/addtoCart")
    public String addToCart(@RequestParam("songId") Long theId, RedirectAttributes ra) throws Exception {



        try {
            this.cartService.addSongToCart(theId);
            ra.addFlashAttribute("successFlashAttr", "Song added to cart");
            return "redirect:/song/list";
        } catch (AlreadyInCartException e) {
            ra.addFlashAttribute("errorFlashAttr", e.getMessage());
            return "redirect:/song/list";
        }

    }
    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam("itemId") Long itemId, RedirectAttributes ra) {

        this.cartService.deleteCartItem(itemId);
        return "redirect:/cart/myCart";
    }
    @GetMapping("/myCart")
    public String listCartItems(Model theModel) {

        List <CartItem> theItems = this.cartService.getMyCartItems();
        theModel.addAttribute("items", theItems);

        return "my-cart";
    }
    @GetMapping("/purchaseCart")
    public String purchaseCart( RedirectAttributes ra) {
        this.cartService.purchaseCart();
        ra.addFlashAttribute("successFlashAttr", "Purchase request placed");
        return "redirect:/song/list";
    }
    @ModelAttribute("loggedUser")
    public User getLoggedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=this.userService.findByUsername(authentication.getName());
        return user;
    }

}
