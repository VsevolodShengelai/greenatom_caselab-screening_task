package greenatom.internship.task2;

public class Main {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        System.out.println("До обмена:");
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        int[] result = swapWithoutTemp(a, b);
        a = result[0];
        b = result[1];

        System.out.println("После обмена:");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    public static int[] swapWithoutTemp(int a, int b) {
        // Меняем значения a и b, используя XOR
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        // Создаем массив для возврата обоих значений
        return new int[]{ a, b };
    }
}
