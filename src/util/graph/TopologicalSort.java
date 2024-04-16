package util.graph;

import java.util.*;


/**
 * Use Case:
 * - Find a valid order of steps (find a schedule) with dependencies
 * - Identify if graph is cyclic
 * Definition:
 * The topological sort algorithm takes a directed graph and returns an array of the nodes
 * where each node appears before all the nodes it points to
 */
public class TopologicalSort {
    public static <T> List<T> topologicalSort(Map<T, List<T>> graph) {
        Map<T, Integer> indegrees = getIndegreesOfGraph(graph);
        List<T> topologicalOrdering = getTopologicalOrder(graph, indegrees);

        if (topologicalOrdering.size() != graph.size()) {
            throw new IllegalArgumentException("Graph has a cycle! No topological ordering exists.");
        }

        return topologicalOrdering;
    }

    private static <T> Map<T, Integer> getIndegreesOfGraph(Map<T, List<T>> graph) {
        Map<T, Integer> indegrees = initIndegreesWithZeros(graph);

        updateIndegreesByGraph(graph, indegrees);
        return indegrees;
    }

    private static <T> List<T> getTopologicalOrder(Map<T, List<T>> graph, Map<T, Integer> indegrees) {
        List<T> topologicalOrdering = new ArrayList<>();
        Queue<T> nodesWithNoIncomingEdges = getAllNodesWithNoIncomingEdges(indegrees);

        while (!nodesWithNoIncomingEdges.isEmpty()) {
            T node = nodesWithNoIncomingEdges.poll();
            topologicalOrdering.add(node);

            updateNeighborsIndegreesAndNodesWithNoIncomingEdges(graph, nodesWithNoIncomingEdges, indegrees, node);
        }
        return topologicalOrdering;
    }

    private static <T> void updateNeighborsIndegreesAndNodesWithNoIncomingEdges(Map<T, List<T>> graph, Queue<T> nodesWithNoIncomingEdges, Map<T, Integer> indegrees, T node) {
        for (T neighbor : graph.get(node)) {
            indegrees.put(neighbor, indegrees.get(neighbor) - 1);
            if (indegrees.get(neighbor) == 0) {
                nodesWithNoIncomingEdges.add(neighbor);
            }
        }
    }

    private static <T> Queue<T> getAllNodesWithNoIncomingEdges(Map<T, Integer> indegrees) {
        Queue<T> nodesWithNoIncomingEdges = new ArrayDeque<>();
        for (var entry : indegrees.entrySet()) {
            if (entry.getValue() == 0) {
                nodesWithNoIncomingEdges.add(entry.getKey());
            }
        }
        return nodesWithNoIncomingEdges;
    }

    private static <T> void updateIndegreesByGraph(Map<T, List<T>> graph, Map<T, Integer> indegrees) {
        for (var entry : graph.entrySet()) {
            T node = entry.getKey();
            for (T neighbor : entry.getValue()) {
                indegrees.put(neighbor, indegrees.get(neighbor) + 1);
            }
        }
    }

    private static <T> Map<T, Integer> initIndegreesWithZeros(Map<T, List<T>> graph) {
        Map<T, Integer> indegrees = new HashMap<>();
        for (T node : graph.keySet()) {
            indegrees.put(node, 0);
        }
        return indegrees;
    }
}
