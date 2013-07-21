
import java.util.ArrayList;
import java.util.Arrays;

public class Mul extends Expr {

    public ArrayList<Expr> list = new ArrayList<>();

    public Mul(Expr... args) {
        list.addAll(Arrays.asList(args));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Expr x : list) {
            if (sb.length() > 0) {
                sb.append("*");
            }
            if (x instanceof Add) {
                sb.append("(").append(x).append(")");
            } else {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    @Override
    public int eval() throws Exception {
        int ret = 1;
        for (Expr x : list) {
            ret *= x.eval();
        }
        return ret;
    }
}
