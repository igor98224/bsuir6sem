package MorozIA.decoratorMorozIA;

public class Decorator2 extends Decorator{
    InterestingFact b;

    public Decorator2(InterestingFact box){
        this.b = box;
    }

    public String getFact() {
        return b.getFact() + "Самым часто угоняемым автомобилем в Соединенных Штатах является Honda Accord. " +
                "В пятерку лидеров входят Honda Civic, Ford Pickup, Chevrolet Pickup и Toyota Camry. " +
                "В Соединенных Штатах автомобиль угоняют каждые 45 секунд." ;
    }
}
