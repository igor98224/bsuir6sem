package MorozIA.StrategyMorozIA;

public class ErrorPassword  implements ErrorStrategy{
    @Override
    public String validRegistration() {
        return "Пароль должен быть длиннее 6 символов";
    }
}
