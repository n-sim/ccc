package util;

import java.util.Objects;
import java.util.StringJoiner;

public class Tuple<X extends Comparable<X>, Y extends Comparable<Y>> implements Comparable<Tuple<X, Y>>{
    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(x, tuple.x) && Objects.equals(y, tuple.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", x, y);
    }

    @Override
    public int compareTo(Tuple<X, Y> o) {
        if(this.x != o.x){
            return this.x.compareTo(o.x);
        }else{
            return this.y.compareTo(o.y);
        }
    }
}
