package musicplayer.controller;


import musicplayer.entity.*;
import musicplayer.exception.ResourceNotFoundException;
import musicplayer.form.UserForm;
import musicplayer.repository.PurchaseRequestRepository;
import musicplayer.repository.UserRepository;
import musicplayer.service.CartService;
import musicplayer.service.MasterGenderService;
import musicplayer.service.UserRegAppService;
import musicplayer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRegAppService userRegAppService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private MasterGenderService masterGenderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;


    @GetMapping("/admin/user-reg-apps")
    public String listPendingUserApps(Model theModel) {

        return "list-reg-apps";
    }
    @GetMapping("/active-users")
    public String listActiveUsers(Model theModel) {
        List <User> theUsers = this.userService.getActiveUsers();

        theModel.addAttribute("users", theUsers);
        return "list-users";
    }

    @GetMapping("/superadmin/admin-list")
    public String listAdmins(Model theModel) {
        List <User> theUsers = this.userService.getActiveAdmins();

        theModel.addAttribute("users", theUsers);
        theModel.addAttribute("adminList", true);

        return "list-users";
    }

    @GetMapping("/superadmin/makeasadmin")
    public String makeAsAdmin(@RequestParam("userId") Long theId,Model theModel) {
        this.userService.setAsAdmin(theId);
        return "redirect:/active-users";
    }

    @GetMapping("/superadmin/removeadmin")
    public String removeAdmin(@RequestParam("userId") Long theId,Model theModel) {
        this.userService.RemoveAdmin(theId);
        return "redirect:/active-users";
    }

    @GetMapping("/admin/blockUser")
    public String blockUser(@RequestParam("userId") Long theId,Model theModel) {
        this.userService.handleUserBlock(theId,true);
        return "redirect:/active-users";
    }
    @GetMapping("/admin/unblockUser")
    public String unblockUser(@RequestParam("userId") Long theId,Model theModel) {
        this.userService.handleUserBlock(theId,false);
        return "redirect:/active-users";
    }

    @GetMapping("/admin/approveUser")
    public String approveUserApp(@RequestParam("appId") Long theId,
                                    Model theModel) throws ResourceNotFoundException {

        this.userRegAppService.updateUserStatus(2,theId);
        return "redirect:/admin/user-reg-apps";
    }
    @GetMapping("/admin/rejectUser")
    public String rejectUserApp(@RequestParam("appId") Long theId,
                                    Model theModel) throws ResourceNotFoundException {

        this.userRegAppService.updateUserStatus(3,theId);
        return "redirect:/admin/user-reg-apps";
    }
    @ModelAttribute("userRegapps")
    public List<UserRegApp> pendingUserRegAppsList()
    {
        List<UserRegApp> pendingUserRegApps = this.userRegAppService.getUserRegAppsByStatus(1);

        return pendingUserRegApps;
    }

    @GetMapping("/user/myProfile")
    public String showProfileUpdate(Model theModel) throws Throwable {
        User theUser = this.userService.getUser();
        theModel.addAttribute("user", theUser);

        List<UserAddress> addressDataList = this.userService.getUserAddress(theUser);
        theModel.addAttribute("addressDataList", addressDataList);
        UserAddress corresAddress=addressDataList.get(0);
        UserAddress perAddress=addressDataList.get(1);
        theModel.addAttribute("userForm", new UserForm(theUser,corresAddress,perAddress));

        return "profile-form";
    }
    @GetMapping("/admin/viewUser")
    public String viewUserDetails(@RequestParam("userId") Long theId,Model theModel) throws Throwable {
        User theUser= userRepository.findById(theId).get();
        theModel.addAttribute("user", theUser);
        List<UserAddress> addressDataList = this.userService.getUserAddress(theUser);
        UserAddress corresAddress=addressDataList.get(0);
        UserAddress perAddress=addressDataList.get(1);
        theModel.addAttribute("corresAddress", corresAddress);
        theModel.addAttribute("perAddress", perAddress);
        return "user-details";
    }

    @RequestMapping(value = "/user/myProfile", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model theModel) {

        userService.update(userForm);
        theModel.addAttribute("SuccessMsg", "User Application Created Successfully");
        return "profile-form";
    }
    @ModelAttribute("loggedUser")
    public User getLoggedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=this.userService.findByUsername(authentication.getName());
        return user;
    }
    @ModelAttribute("genderDetailsList")
    public List<MasterGender> getGenderDetailsList()
    {
        List<MasterGender> genderDetailsList = this.masterGenderService.getGenders();

        return genderDetailsList;
    }
    @GetMapping("admin/purchase-requests")
    public String listPendingPurchaseRequests(Model theModel) {

        List <PurchaseRequest> thePurchaseRequests = this.cartService.getPendingPurchaseRequests(1);
        theModel.addAttribute("purchaseRequests", thePurchaseRequests);
        return "list-purchase";
    }
    @GetMapping("/admin/approvePurchaseReq")
    public String approvePurchaseReq(@RequestParam("appId") Long theId,
                                 Model theModel) throws ResourceNotFoundException {

        this.cartService.updatePurchaseReqStatus(2,theId);
        return "redirect:/admin/purchase-requests";
    }
    @GetMapping("/admin/rejectPurchaseReq")
    public String rejectPurchaseReq(@RequestParam("appId") Long theId,
                                Model theModel) throws ResourceNotFoundException {

        this.cartService.updatePurchaseReqStatus(3,theId);
        return "redirect:/admin/purchase-requests";
    }

    @GetMapping("/admin/viewPurchaseReq")
    public String viewPurchaseReq(@RequestParam("reqId") Long purchaseId,
                                    Model theModel) throws ResourceNotFoundException {

        List <CartItem> theItems = this.cartService.getPurchaseReqItems(purchaseId);
        theModel.addAttribute("items", theItems);
        PurchaseRequest purchaseRequest=purchaseRequestRepository.findById(purchaseId);
        theModel.addAttribute("purchaseRequest", purchaseRequest);
        return "purchase-details";
    }
    @GetMapping("admin/create-song")
    public String createSong(Model theModel) {


        Song theSong = new Song();
        theModel.addAttribute("song", theSong);

        return "song-form";
    }

}
