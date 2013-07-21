
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Add extends Expr {

    public ArrayList<Expr> list = new ArrayList<>();

    public Add(Expr... args) {
        for (Expr x : args) {
            if (x instanceof Add) {
                list.addAll(((Add) x).list);
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
                sb.append("+");
            }
            sb.append(x);
        }
        return sb.toString();
    }

    @Override
    public int eval() throws Exception {
        int ret = 0;
        for (Expr x : list) {
            ret += x.eval();
        }
        return ret;
    }

    public void sort() {
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object x, Object y) {
                int xx = x instanceof Var ? ((Var) x).n : 0;
                int yy = y instanceof Var ? ((Var) y).n : 0;
                return yy - xx;
            }
        });
    }

    public void simplify() {
        sort();
        ArrayList<Expr> newlist = new ArrayList<>();
        Expr last = null;
        for (Expr x : list) {
            if (last == null) {
                last = x;
            } else if (last instanceof Value && x instanceof Value) {
                last = new Value(((Value) last).n + ((Value) x).n);
            } else {
                Var v1 = last instanceof Var ? (Var) last : null;
                Var v2 = x instanceof Var ? (Var) x : null;
                if (v1 != null && v2 != null && v1.n == v2.n) {
                    last = new Var(v1.a + v2.a, v1.n);
                } else {
                    newlist.add(last);
                    last = x;
                }
            }
        }
        if (last != null) {
            newlist.add(last);
        }
        list = newlist;
    }
}