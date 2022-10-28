package prr.visitors;

public interface Printable {
    public String accept(Printer visitor);
}
