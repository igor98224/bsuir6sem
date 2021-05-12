package MorozIA.StrategyMorozIA;

public class ErrorAuthorization  implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Неверный логин или пароль";
    }
}
