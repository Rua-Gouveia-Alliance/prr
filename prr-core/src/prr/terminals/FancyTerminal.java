package prr.terminals;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String key, Client owner) {
        super(key, owner);
    }

    @Override
    public String toString() {
        return "FANCY|" + super.toString();
    }

}
