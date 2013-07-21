
public class JFormula {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int a = 1;
            int b = 2;
            int c = a + b;
            System.out.println(c);
            System.out.println(a + " + " + b + " = " + c);

            Expr a2 = new Value(1);
            Expr b2 = new Value(2);
            Expr c2 = new Add(a2, b2);
            System.out.println(a2 + " + " + b2 + " = " + c2);
            System.out.println("c2: " + c2 + " = " + c2.eval());

            Expr f1 = new Add(
                    new Value(1),
                    new Value(2));
            System.out.println("f1: " + f1 + " = " + f1.eval());

            Expr f2 = new Add(
                    new Add(new Value(1), new Value(2)),
                    new Value(3));
            System.out.println("f2: " + f2 + " = " + f2.eval());

            Expr f3a = new Add(
                    new Value(1),
                    new Mul(new Value(2), new Value(3)));
            Expr f3b = new Mul(
                    new Add(new Value(1), new Value(2)),
                    new Value(3));
            System.out.println("f3a: " + f3a);
            System.out.println("f3b: " + f3b);

            Expr f4a = new Add(new Var(), new Value(1));
            System.out.println("f4a: " + f4a);
            Expr f4b = x().add(1);
            System.out.println("f4a: " + f4b);

            // f5 = x + 2 + 3*x + 4
            Add f5a = x().add(2).add(x(3)).add(4);
            System.out.println("f5a: " + f5a);

            // x^2 + 2*x + 3 + 4*x + 5*x^2 + 6;
            Add f6a = x(1, 2).add(x(2)).add(3).add(x(4)).add(x(5, 2)).add(6);
            System.out.println("f6a: " + f6a);

            Add f5b = f5a.sort();
            Add f6b = f6a.sort();
            System.out.println("f5b: " + f5b);
            System.out.println("f6b: " + f6b);

            f5a.simplify();
            f6a.simplify();
            System.out.println("f5a: " + f5a);
            System.out.println("f6a: " + f6a);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public static Var x() {
        return new Var();
    }

    public static Var x(int a) {
        return new Var(a, 1);
    }

    public static Var x(int a, int n) {
        return new Var(a, n);
    }
}
