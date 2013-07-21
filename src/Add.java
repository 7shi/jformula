
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Add extends Expr {
    
    public ArrayList<Expr> list = new ArrayList<>();
    
    public Add(Expr... args) {
        list.addAll(Arrays.asList(args));
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
    
    @Override
    public Add add(int n) {
        return add(new Value(n));
    }
    
    @Override
    public Add add(Expr x) {
        Add ret = new Add();
        ret.list.addAll(list);
        ret.list.add(x);
        return ret;
    }
    
    public Add sort() {
        Add ret = new Add();
        ret.list.addAll(list);
        Collections.sort(ret.list, new Comparator() {
            @Override
            public int compare(Object x, Object y) {
                int xx = x instanceof Var ? ((Var) x).n : 0;
                int yy = y instanceof Var ? ((Var) y).n : 0;
                return yy - xx;
            }
        });
        return ret;
    }
    
    public int getMax() {
        int max = 0;
        for (Expr x : list) {
            if (x instanceof Var) {
                int n = ((Var) x).n;
                if (max < n) {
                    max = n;
                }
            }
        }
        return max;
    }
    
    public Add simplify() {
        Add ret = new Add();
        ArrayList<Expr> etc = new ArrayList<>();
        int max = getMax();
        int[] as = new int[max + 1];
        for (Expr x : list) {
            if (x instanceof Var) {
                Var vx = (Var) x;
                as[vx.n] += vx.a;
            } else if (x instanceof Value) {
                as[0] += ((Value) x).n;
            } else {
                etc.add(x);
            }
        }
        for (int n = max; n >= 1; n--) {
            ret.list.add(new Var(as[n], n));
        }
        ret.list.add(new Value(as[0]));
        ret.list.addAll(etc);
        return ret;
    }
}
