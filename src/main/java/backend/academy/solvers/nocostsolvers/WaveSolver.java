package backend.academy.solvers.nocostsolvers;

import backend.academy.maze.Maze;
import backend.academy.primitives.coordinate.Coordinate;
import backend.academy.solvers.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * Класс WaveSolver реализует алгоритм волнового поиска для решения лабиринта.
 *
 * 1. Создается очередь (Queue) для хранения координат, которые нужно обработать.
 * 2. Создается карта (Map) для хранения предшественника каждой клетки.
 * 3. Создается множество (Set) для хранения уже посещенных клеток.
 * 4. В очередь добавляется стартовая клетка.
 * 5. Пока очередь не пуста:
 *   - Извлекается клетка из очереди.
 *   - Если извлеченная клетка является конечной клеткой, то она "реконструируется"
 *     путь и возвращается результат.
 *   - Для каждой соседней клетки, которая не была посещена:
 *     - Клетка помечается как посещенная.
 *     - В карту записывается, что предшественником текущей клетки является
 *       извлеченная клетка.
 *     - Текущая клетка добавляется в очередь.
 * 6. Если очередь становится пустой, то путь не найден и возвращается пустой список.
 */

public final class WaveSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();
        Set<Coordinate> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(end)) {
                return reconstructPath(cameFrom, current);
            }

            for (Coordinate neighbor : maze.getNeighbors(current)) {
                if (visited.add(neighbor)) {
                    cameFrom.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate current) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate currentNode = current;
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = cameFrom.get(currentNode);
        }
        Collections.reverse(path);
        return path;
    }

}
