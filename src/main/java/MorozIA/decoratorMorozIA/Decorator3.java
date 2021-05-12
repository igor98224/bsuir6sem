package MorozIA.decoratorMorozIA;

public class Decorator3 extends Decorator{
    InterestingFact b;

    public Decorator3(InterestingFact box){
        this.b = box;
    }

    public String getFact() {
        return b.getFact() +"В 2017 году Rolls-Royce представил уникальную модель под названием Sweptail, " +
                "стоимостью около 13 миллионов долларов. " +
                "Он считается самым дорогим современным автомобилем за всю историю.";
    }
}
