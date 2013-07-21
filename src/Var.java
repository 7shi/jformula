
public class Var extends Expr {

    public int a, n;

    public Var() {
        a = n = 1;
    }

    public Var(int a, int n) {
        this.a = a;
        this.n = n;
    }

    @Override
    public String toString() {
        return (a == 1 ? "" : Integer.toString(a))
                + "x" + (n == 1 ? "" : "^" + n);
    }

    @Override
    public int eval() throws Exception {
        throw new Exception();
    }
}
