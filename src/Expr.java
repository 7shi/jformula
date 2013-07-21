
abstract public class Expr {

    public abstract int eval() throws Exception;

    public Add add(int n) {
        return new Add(this, new Value(n));
    }

    public Add add(Expr x) {
        return new Add(this, x);
    }
}
