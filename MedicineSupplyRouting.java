class Edge {
    int source, destination, weight;

    Edge(int s, int d, int w) {
        source = s;
        destination = d;
        weight = w;
    }
}

public class MedicineSupplyRouting {

    public static void main(String[] args) {

        int V = 5;
        int E = 7;

        Edge[] edges = new Edge[E];

        edges[0] = new Edge(0, 1, 6);
        edges[1] = new Edge(0, 2, 7);
        edges[2] = new Edge(1, 2, 8);
        edges[3] = new Edge(1, 3, 5);
        edges[4] = new Edge(1, 4, -4);
        edges[5] = new Edge(2, 3, -3);
        edges[6] = new Edge(3, 4, 9);

        int[] distance = new int[V];

        for (int i = 0; i < V; i++)
            distance[i] = Integer.MAX_VALUE;

        distance[0] = 0;

        // Bellman-Ford Algorithm
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges[j].source;
                int v = edges[j].destination;
                int w = edges[j].weight;

                if (distance[u] != Integer.MAX_VALUE &&
                        distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                }
            }
        }

        System.out.println("MEDICINE SUPPLY ROUTE OPTIMIZATION");
        System.out.println("----------------------------------");

        System.out.println("\nDistribution Centers:");
        System.out.println("A, B, C, D, E");

        System.out.println("\nRoutes (Cost):");
        System.out.println("A -> B = 6");
        System.out.println("A -> C = 7");
        System.out.println("B -> D = 5");
        System.out.println("B -> E = -4");
        System.out.println("C -> D = -3");

        System.out.println("\nShortest Supply Cost from Center A:");

        for (int i = 0; i < V; i++) {
            char center = (char) ('A' + i);
            System.out.println(
                    "A to " + center +
                            " = " + distance[i]);
        }

        System.out.println("\nBellman-Ford Optimization Completed");
    }
}