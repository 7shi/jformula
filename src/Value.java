
public class Value extends Expr {

    public int n;

    public Value(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }

    @Override
    public int eval() {
        return n;
    }
}
