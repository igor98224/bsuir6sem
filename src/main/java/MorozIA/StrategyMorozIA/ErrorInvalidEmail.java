package MorozIA.StrategyMorozIA;

public class ErrorInvalidEmail implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Пользователь с данным email не найден";
    }
}
