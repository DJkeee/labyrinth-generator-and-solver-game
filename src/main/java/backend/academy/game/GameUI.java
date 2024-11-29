package backend.academy.game;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

class GameUI {
    private static final PrintStream PRINT = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    private GameUI() {
    }

    public static void askAboutMazeSize() {
        PRINT.println("Выберите размер лабиринта:");
        PRINT.println("1: 15 x 15");
        PRINT.println("2: 20 x 20");
        PRINT.println("3: 25 x 25\n");
    }

    public static void askAboutGenerationAlgorithm() {
        PRINT.println("Выберите тип лабиринта, что будет создан:");
        PRINT.println("1: Классический лабиринт с равномерной заполненностью проходами и стенами");
        PRINT.println("2: Лабиринт, похожий на пещеры из игр\n");
    }

    public static void askAboutSolveAlgorithm() {
        PRINT.println("Выберите тип поиска");
        PRINT.println("1: поиск маршрута без учета типа клеток(бонусы не влияют на цену маршрута)");
        PRINT.println("2: поиск маршрута с учетом типа клетки(бонусы влияют на цену маршрута)\n");
    }

    public static void askAboutStart() {
        PRINT.println("Введите начальную точку поиска пути в лабиринте\n");
    }

    public static void askAboutEnd() {
        PRINT.println("Введите конечную точку поиска пути в лабиринте\n");
    }

    public static void sayAboutCoordinatesInterval(int start, int end) {
        PRINT.println("Введите координаты в диапазоне от " + start + " до " + end);
    }

    public static void sayGameIntro() {
        PRINT.println("Добро пожаловать в игру, позволяющую вам создавать лабиринты"
            + "и искать в них пути разными способами.");
    }

    public static void sayAboutMazeSymbols() {
        PRINT.println("Условные обозначения: ");
        PRINT.println("Вход и выход из лабиринта - \uD83D\uDEB4");
        PRINT.println("Стены - ⬛");
        PRINT.println("Проходы - ⬜️\n");
        PRINT.println("Замедлюящие путь бонусы: ");
        PRINT.println("Ловушки - ⚠️");
        PRINT.println("Деревья - \uD83D\uDFE2\n");
        PRINT.println("Ускоряющие путь бонусы: ");
        PRINT.println("Монеты - \uD83D\uDFE8");
    }
}
