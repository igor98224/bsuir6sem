package MorozIA.decoratorMorozIA;

public class Decorator4 extends Decorator{
    InterestingFact b;

    public Decorator4(InterestingFact box){
        this.b = box;
    }

    public String getFact() {
        return b.getFact() +"Мировой рекорд по снятию и замене автомобильного двигателя составляет 42 секунды. " +
                "Этот рекорд был установлен 21 ноября 1985 года механиками, работающими над Ford Escort."
                ;
    }
}
