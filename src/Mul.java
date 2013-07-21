
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
                sb.append("(" + x + ")");
            } else {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    @Override
    public int eval() throws Exception {
        int ret = 0;
        for (Expr x : list) {
            if (ret == 0) {
                ret = x.eval();
            } else {
                ret *= x.eval();
            }
        }
        return ret;
    }
}
