package backend.academy.solvers.costsolvers;

import backend.academy.maze.Maze;
import backend.academy.primitives.coordinate.Coordinate;
import backend.academy.solvers.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Класс AStartSolver реализует алгоритм A* для поиска кратчайшего пути в лабиринте.
 * Алгоритм использует приоритетную очередь для оценки узлов на основе их стоимости
 * (gScore) и эвристической оценки (fScore).
 * Основные шаги алгоритма:
 * 1. Инициализация структур данных: открытого и закрытого множеств,
 * а также карт для хранения стоимости пути и предшественников.
 * 2. Добавление начальной точки в открытое множество.
 * 3. Пока открытое множество не пусто:
 * - Извлечение узла с наименьшей стоимостью fScore.
 * - Если достигнута конечная точка, восстановление пути.
 * - Обработка соседей текущего узла:
 * вычисление временной стоимости gScore и обновление структур данных.
 * 4. Если путь не найден, возвращается пустой список.
 */

public final class AStarSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {

        int estimatedSize = maze.getHeight() * maze.getWidth();
        PriorityQueue<Node> openPriorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Node::getF));
        Set<Coordinate> closedSet = new HashSet<>(estimatedSize);
        Map<Coordinate, Double> gScore = new HashMap<>(estimatedSize);
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>(estimatedSize);

        gScore.put(start, 0.0);
        openPriorityQueue.add(new Node(start, heuristic(start, end)));

        while (!openPriorityQueue.isEmpty()) {
            Node currentNode = openPriorityQueue.poll();
            Coordinate current = currentNode.coordinate;
            if (current.equals(end)) {
                return reconstructPath(cameFrom, current);
            }
            closedSet.add(current);
            for (Coordinate neighbor : maze.getNeighbors(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double moveCost = maze.getCellCost(neighbor);
                double tentativeGScore = gScore.getOrDefault(current, Double.MAX_VALUE) + moveCost;

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    double fScore = tentativeGScore + heuristic(neighbor, end);
                    openPriorityQueue.add(new Node(neighbor, fScore));
                }
            }
        }

        return Collections.emptyList();

    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate current) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate curNode = current;
        while (curNode != null) {
            path.add(curNode);
            curNode = cameFrom.get(curNode);
        }
        Collections.reverse(path);
        return path;
    }

    private double heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.row() - b.row()) + Math.abs(a.col() - b.col());
    }

    static class Node {
        Coordinate coordinate;
        double fScore;

        Node(Coordinate coordinate, double fScore) {
            this.coordinate = coordinate;
            this.fScore = fScore;
        }

        double getF() {
            return fScore;
        }
    }

}
