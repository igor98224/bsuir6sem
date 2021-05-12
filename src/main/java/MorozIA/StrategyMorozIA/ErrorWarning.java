package MorozIA.StrategyMorozIA;

public class ErrorWarning implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Проверьте почту!";
    }
}

