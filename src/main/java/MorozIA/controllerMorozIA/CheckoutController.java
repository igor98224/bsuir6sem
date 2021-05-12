package MorozIA.controllerMorozIA;

import MorozIA.modeMorozIA.ChargeRequest;
import MorozIA.otherMorozIA.StaticMorozIA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Value("pk_test_51IorqdKEroMT0hwh6VPwT6DPnn3wLCDbja8sWmlWpKUGPaFikHksGF4N5GFWHQ3VxVRVmM71xZCKEmcF7BDWd7lF00dVhxCqwE")
    private String stripePublicKey;

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("name", StaticMorozIA.Name);
        model.addAttribute("model", StaticMorozIA.Model);
        model.addAttribute("start", StaticMorozIA.Start);
        model.addAttribute("end", StaticMorozIA.End);
        model.addAttribute("days", StaticMorozIA.Days);

        model.addAttribute("amount", StaticMorozIA.Amount*100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "/customer/checkout-page";
    }
}