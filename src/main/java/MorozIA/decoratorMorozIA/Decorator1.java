package MorozIA.decoratorMorozIA;

public class Decorator1 extends Decorator{
    InterestingFact b;

    public Decorator1(InterestingFact box){
        this.b = box;
    }

    public String getFact() {
        return b.getFact() + "Названия всех автомобилей Lamborghini происходят из мира корриды. " +
                "Diablo и Murcielago — это имена известных быков, а Estoque — шпага, которую используют Матадоры.";
    }
}
