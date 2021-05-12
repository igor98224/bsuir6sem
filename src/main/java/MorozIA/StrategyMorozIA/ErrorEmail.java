package MorozIA.StrategyMorozIA;

public class ErrorEmail  implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Неверный email";
    }
}
