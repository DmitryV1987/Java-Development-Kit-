// 1. Вот пример реализации класса Калькулятор:

public class Calculator {
    public static <T extends Number> T sum(T num1, T num2) {
        if (num1 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() + num2.intValue());
        } else if (num1 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() + num2.doubleValue());
        }
        return null;
    }

    public static <T extends Number> T multiply(T num1, T num2) {
        if (num1 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() * num2.intValue());
        } else if (num1 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() * num2.doubleValue());
        }
        return null;
    }

    public static <T extends Number> T divide(T num1, T num2) {
        if (num1 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() / num2.intValue());
        } else if (num1 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() / num2.doubleValue());
        }
        return null;
    }

    public static <T extends Number> T subtract(T num1, T num2) {
        if (num1 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() - num2.intValue());
        } else if (num1 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() - num2.doubleValue());
        }
        return null;
    }
}

// 2. Вот пример реализации обобщенного метода compareArrays():

public class Main {
    public static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3};
        Integer[] arr3 = {1, 2, 4};

        System.out.println(compareArrays(arr1, arr2)); // true
        System.out.println(compareArrays(arr1, arr3)); // false
    }
}

// 3. Вот пример реализации обобщенного класса Pair:

public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair1 = new Pair<>(1, "one");
        Pair<String, Double> pair2 = new Pair<>("two", 2.0);

        System.out.println(pair1.toString()); // (1, one)
        System.out.println(pair2.toString()); // (two, 2.0)
    }
}
