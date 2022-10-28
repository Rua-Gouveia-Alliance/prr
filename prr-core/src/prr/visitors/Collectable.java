package prr.visitors;

public interface Collectable<T> {
    public void accept(Collector<T> visitor);
}
