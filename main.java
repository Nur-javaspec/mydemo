import java.util.Scanner;

class Main {
    public static String calc(String input) throws Exception {
        // Убираем лишние пробелы
        input = input.trim();

        // Разделяем строку на части по пробелам
        String[] parts = input.split(" ");

        // Проверяем, что в строке ровно 3 части: число, оператор, число
        if (parts.length != 3) {
            throw new Exception("Неправильный формат");
        }

        // Преобразуем числа из строк в целые
        int a, b;
        try {
            a = Integer.parseInt(parts[0]);
            b = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new Exception("Числа должны быть целыми");
        }

        // Оператор
        String operator = parts[1];

        // Выполнение операции
        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    throw new Exception("Деление на ноль невозможно");
                }
                result = a / b;  // целочисленное деление
                break;
            default:
                throw new Exception("Некорректный оператор");
        }

        // Проверяем диапазон чисел
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите математическое выражение (или 'exit' для завершения):");

        while (true) {
            System.out.print("Ввод: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Завершение программы.");
                break;
            }

            try {
                String result = calc(input);
                System.out.println("Результат: " + result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
