import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(5, 1);
        int c = calc.devide.apply(6, 0);
        int c1 = calc.devide.apply(6, 2);
        int cExample = calc.devide.apply(a, b);
        int d = calc.multiply.apply(6, 3);
        int e = calc.pow.apply(3);
        int f = calc.abs.apply(5);
        int f1 = calc.abs.apply(-5);
        System.out.println(f);
        System.out.println(f1);
        System.out.println(cExample);
        System.out.println(c1);
    }
}
// cExample(из примера) не работает т.к. BinaryOperator devide принимает и выполняет операции над переменнами типа Integer
// abs работает