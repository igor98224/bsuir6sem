package MorozIA.controllerMorozIA;

import MorozIA.StrategyMorozIA.*;
import MorozIA.modeMorozIA.Comment;
import MorozIA.modeMorozIA.Customer;
import MorozIA.otherMorozIA.ForgotPasswordClass;
import MorozIA.serviceMorozIA.AdminService;
import MorozIA.serviceMorozIA.CustomerService;
import MorozIA.serviceMorozIA.RatingService;
import MorozIA.validatorsMorozIA.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {

    EmailValidator validator = new EmailValidator();
    private final CustomerService customerService;
    private final AdminService adminService;
    private final RatingService ratingService;

    private String errorMess;

    @Autowired
    public CustomerController(CustomerService customerService, AdminService adminService, RatingService ratingService) {
        this.customerService = customerService;
        this.adminService = adminService;
        this.ratingService = ratingService;
    }

    @GetMapping("/customer-comments")
    public String commentsAdmin(Model model){
        List<Comment> comments = ratingService.findAll();
        model.addAttribute("comments",comments);
        return "/admin/rates";
    }

    @GetMapping("/rate-us")
    public String rate(){
        return "/customer/rate-us";
    }

    @PostMapping("/comment-us")
    public String commentUs(@RequestParam("user-name")String login, @RequestParam("comment")String comment){
        ratingService.saveComment(login,comment);

        return "redirect:/rate-us";
    }

    @GetMapping("/my-rate")
    public String myRate(){

        return "/customer/my-rating";
    }

    @PostMapping("/my-rating")
    public String myRating(@RequestParam("login")String customer, Model model){
        List<Customer> customer1 = customerService.findByEmail2(customer);
        model.addAttribute("name", customer1.get(0).getName());
        model.addAttribute("email",customer1.get(0).getEmail());
        model.addAttribute("rating",customer1.get(0).getRating());
        String how = "Рейтинг пользователя DriveAve. ";
        how+=customerService.how();
        model.addAttribute("how",how);
        System.out.println(customer1.get(0).getName());
        return "/customer/my-rating";
    }

    @GetMapping("/auth")
    public String createCustomerForm(Customer customer){
        return "/authorization/auth";
    }

    @GetMapping("/auth-error")
    public String createCustomerFormWithError(Customer customer, Model model){
        model.addAttribute("error",errorMess);
        return "/authorization/auth";
    }

    @PostMapping("/create-customer")
    public String createCustomer(Customer customer){

        for(char c : customer.getName().toCharArray()){
            if(Character.isDigit(c)){
                ErrorStrategy errorStrategy = new ErrorName();
                errorMess = errorStrategy.validRegistration();
                return "redirect:/auth-error";
            }
            if(!Character.isAlphabetic(c)) {
                ErrorStrategy errorStrategy = new ErrorName();
                errorMess = errorStrategy.validRegistration();
                return "redirect:/auth-error";
            }
        }
        if(!validator.validate(customer.getEmail())){
            ErrorStrategy errorStrategy = new ErrorEmail();
            errorMess = errorStrategy.validRegistration();
            return "redirect:/auth-error";
        }
        if(customer.getAccess().length()<6){
            ErrorStrategy errorStrategy = new ErrorPassword();
            errorMess = errorStrategy.validRegistration();
            return "redirect:/auth-error";
        }

        customerService.saveCustomer(customer);
        return "/customer/profile";
    }

    @PostMapping("/entrance")
    public String entrance(Customer customer){

        if(adminService.findByNameAndAccess(customer.getEmail(),customer.getAccess()).size()!=0)
            return "redirect:/admin-profile";

        if(customerService.findByEmail(customer.getEmail(),customer.getAccess()).size()!=0)
            return "/customer/profile";
        ErrorStrategy errorStrategy = new ErrorAuthorization();
        errorMess = errorStrategy.validRegistration();
        return "redirect:/auth-error";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(Customer customer){
        List<Customer> customers = customerService.findByEmail2(customer.getEmail());
        if(customers.size()!=0){
            ForgotPasswordClass.mail(customer.getEmail(),customers.get(0).getAccess(), customers.get(0).getName());

            ErrorStrategy errorStrategy = new ErrorWarning();
            errorMess = errorStrategy.validRegistration();
            return "redirect:/auth-error";

        }
        ErrorStrategy errorStrategy = new ErrorInvalidEmail();
        errorMess = errorStrategy.validRegistration();
        return "redirect:/auth-error";
    }

}
