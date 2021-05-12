package MorozIA.decoratorMorozIA;

public class Decorator5 extends Decorator{
    InterestingFact b;

    public Decorator5(InterestingFact box){
        this.b = box;
    }

    public String getFact() {
        return b.getFact() + "Самым дорогим автомобилем, когда-либо проданным на публичных торгах, был гоночный " +
                "автомобиль Формулы 1 Mercedes-Benz W196R 1954 года. " +
                "В 2013 году эта сумма составила 30 миллионов долларов.";
    }
}
