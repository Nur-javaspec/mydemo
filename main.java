import java.util.*;

public class HangmanGame {
    // Список слов для игры
    private static final List<String> WORDS = Arrays.asList(
            "программирование", "компьютер", "алгоритм", "интерфейс", "сервер"
    );

    // Максимальное количество попыток
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Выбираем случайное слово из списка
        String word = getRandomWord();
        Set<Character> guessedLetters = new HashSet<>();
        Set<Character> wrongLetters = new HashSet<>();
        int attemptsLeft = MAX_ATTEMPTS;

        System.out.println("Добро пожаловать в игру 'Виселица'!");

        // Основной игровой цикл
        while (attemptsLeft > 0) {
            // Отображаем текущее состояние слова
            System.out.println("\nТекущее состояние слова:");
            System.out.println(getWordState(word, guessedLetters));

            // Отображаем количество оставшихся попыток и список неверных букв
            System.out.println("Осталось попыток: " + attemptsLeft);
            if (!wrongLetters.isEmpty()) {
                System.out.println("Неверные буквы: " + wrongLetters);
            }

            // Проверяем, угадано ли слово полностью
            if (isWordGuessed(word, guessedLetters)) {
                System.out.println("Поздравляем! Вы угадали слово: " + word);
                break;
            }

            // Запрашиваем ввод буквы от игрока
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();

            // Проверяем корректность ввода
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Пожалуйста, введите одну букву.");
                continue;
            }

            char letter = input.charAt(0);

            // Проверяем, была ли буква уже введена
            if (guessedLetters.contains(letter) || wrongLetters.contains(letter)) {
                System.out.println("Вы уже вводили эту букву. Попробуйте другую.");
                continue;
            }

            // Проверяем, есть ли буква в слове
            if (word.indexOf(letter) >= 0) {
                guessedLetters.add(letter);
                System.out.println("Правильно!");
            } else {
                wrongLetters.add(letter);
                attemptsLeft--;
                System.out.println("Неверно! У вас осталось " + attemptsLeft + " попыток.");
            }
        }

        // Если попытки закончились
        if (attemptsLeft == 0) {
            System.out.println("\nК сожалению, вы проиграли. Загаданное слово было: " + word);
        }

        scanner.close();
    }

    // Метод для выбора случайного слова
    private static String getRandomWord() {
        Random random = new Random();
        return WORDS.get(random.nextInt(WORDS.size()));
    }

    // Метод для отображения текущего состояния слова
    private static String getWordState(String word, Set<Character> guessedLetters) {
        StringBuilder state = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (guessedLetters.contains(c)) {
                state.append(c).append(" ");
            } else {
                state.append("_ ");
            }
        }
        return state.toString().trim();
    }

    // Метод для проверки, угадано ли слово полностью
    private static boolean isWordGuessed(String word, Set<Character> guessedLetters) {
        for (char c : word.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
