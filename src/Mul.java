
import java.util.ArrayList;

public class Mul extends Expr {

    public ArrayList<Expr> list = new ArrayList<>();

    public Mul(Expr... args) {
        for (Expr x : args) {
            if (x instanceof Mul) {
                list.addAll(((Mul) x).list);
            } else {
                list.add(x);
            }
        }
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
