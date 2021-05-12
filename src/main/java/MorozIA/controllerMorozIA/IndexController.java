package MorozIA.controllerMorozIA;

import MorozIA.decoratorMorozIA.*;
import MorozIA.modeMorozIA.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/facts")
    public String facts(Model model){
        InterestingFact first = new Decorator1(new InterestingFact());
        InterestingFact second = new Decorator2(new InterestingFact());
        InterestingFact third = new Decorator3(new InterestingFact());
        InterestingFact fourth = new Decorator4(new InterestingFact());
        InterestingFact fifth = new Decorator5(new InterestingFact());

        model.addAttribute("first", first.getFact());
        model.addAttribute("second", second.getFact());
        model.addAttribute("third", third.getFact());
        model.addAttribute("fourth", fourth.getFact());
        model.addAttribute("fifth", fifth.getFact());

        return "/customer/interesting-facts";
    }

    @GetMapping("/profile")
    public String profile(){
        return "/customer/profile";
    }


    @GetMapping("/rates")
    public String ratesAdmin(){
        return "/admin/other/rates";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Customer customer){
        return "/authorization/forgot-password";
    }

    @GetMapping("/drive-ave-recommends")
    public String recommend(Model model){
        int num = (int) (Math.random() * 8)+1;
        String recc = "";


        BufferedReader objReader = null;
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader("C:\\Users\\Moroz\\Downloads\\moroz\\moroz\\src\\main\\resources\\recommendations\\"+num+".txt"));
            while ((strCurrentLine = objReader.readLine())!=null) {
                recc+=strCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        model.addAttribute("recommend", recc);
        return "customer/recommendations";
    }
}
