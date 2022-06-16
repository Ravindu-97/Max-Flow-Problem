// Doing necessary imports

import java.util.LinkedList;
import java.util.Scanner;
import java.lang.*;

// Creating a class which has the main method in it
public class MaxFlow{

    // Initializing Variables

    static int number = 0;
    static int source = 0;
    static int sink = 0;


    // Main Method in which all the instructions are performed
    public static void main(String[] args) throws java.lang.Exception {

        // Initializing a string variable
        String reply = "";

        // Intro Message and some instructions for the user
        System.out.println("\n\t\t\t\t\t\t\t\t\tWelcome To The World Of Networks!");
        System.out.println("\t\t\t\t\t\t\t\t\t*********************************");
        System.out.println("\nInstructions:");
        System.out.println("\n* Please consider that your flow network always starts with node 0.");
        System.out.println("* For example, if you have 6 nodes in your flow network, the order of the nodes would be like 0,1,2,3,4,5.");
        System.out.println("* You can update or delete any edge at anytime. In order delete any edge, just give the capacity of that edge as '0'.");

        // A Do While loop to execute the program
        do {

            boolean runProgram = true;    // Boolean Value = true


            //Initializing a Scanner to get the input
            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);

            // Asking the user the number of nodes he/she wants
            System.out.println("\nEnter how many nodes/vertices you want in your flow network : ");

            while (runProgram) {

                // Using a while loop to repeat the question in the case user inputs wrong data
                while (runProgram) {
                    // Using a Try catch block to get only numbers
                    try {
                        number = sc.nextInt();
                        runProgram = false; // if only numbers the boolean becomes false

                    } catch (Exception e) {    // else catch an exception and put out an error message
                        System.out.print("Please enter a valid number rather than letters or special characters! ");
                        sc.next();
                    }
                }


                // Getting the source node from the user
                System.out.print("Enter the source node of your network flow : ");

                // Using a Try catch block to get only numbers
                try {
                    source = sc1.nextInt();
                    runProgram = false; // if only numbers the boolean becomes false

                } catch (Exception e) {    // else catch an exception and put out an error message
                    System.out.println("Please enter a valid number rather than letters or special characters! ");
                    sc.next();
                }


                // Getting the sink node from the user
                System.out.print("Enter the sink node of your network flow : ");

                // Using a Try catch block to get only numbers
                try {
                    sink = sc1.nextInt();
                    runProgram = false; // if only numbers the boolean becomes false

                } catch (Exception e) {    // else catch an exception and put out an error message
                    System.out.print("Please enter a valid number rather than letters or special characters! ");
                    sc.next();
                }

                // Creating a 2D array and storing the capacities in the array after getting them from the user
                int[][] graph = new int[number][number];

                for (int row = 0; row < number; row++) {

                    for (int column = 0; column < number; column++) {

                        System.out.print("Enter the capacity of the edge from node " + (row ) + " to node " + (column ) + ": ");
                        graph[row][column] = sc1.nextInt();

                    }
                }

                // Displaying the adjacency matrix to the user
                System.out.println("\n****************************************************************");
                System.out.println("The adjacency matrix for your flow network would look like this:\n");

                for (int row = 0; row < graph.length; row++) {

                    for (int column = 0; column < graph[row].length; column++) {

                        System.out.print(graph[row][column] + "\t");

                    }
                    System.out.println();

                }

                // Creating an object of algorithm class
                algorithm m = new algorithm();

                // Stopwatch will start here ('stdlib.jar' has been used here)
                Stopwatch timer = new Stopwatch();

                // Printing the maximum flow by calling fordFulkerson() method
                System.out.println("\nThe maximum possible flow from source node " + m.s + " to sink node " + m.t + " is : " +
                        m.fordFulkerson(graph,m.s , m.t));



                // Stopwatch will stop here ('stdlib.jar' has been used here)
                StdOut.println("Elapsed time for the calculation : " + timer.elapsedTime() + " seconds");

                // Using a Do While loop to get the locations and capacities in the created adjacency matrix and updating that adjacency matrix with new capacity values
                do {
                    System.out.print("\nEnter the total number of capacities that you will change if you want to change any : ");
                    int numOfCapacities = sc.nextInt();

                    if(numOfCapacities > 0 && numOfCapacities < (number * number)){

                        System.out.println("Please enter the correct row number and column number which represent the edge that will be updated with your new capacity in the above generated adjacency matrix.");

                        for (int i = 0; i < numOfCapacities; i++) {
                            System.out.print("\nEnter the row number where the edge begins: ");
                            int updatedRow = sc.nextInt();

                            System.out.print("Enter the column number where the edge ends: ");
                            int updatedColumn = sc.nextInt();

                            System.out.print("Enter the new capacity: ");
                            int updatedCapacity = sc.nextInt();

                            graph[updatedRow][updatedColumn] = updatedCapacity;
                        }

                        // Displaying the updated adjacency matrix
                        System.out.println("\n************************************************************************");
                        System.out.println("The updated adjacency matrix for your flow network would look like this:\n");

                        for (int row = 0; row < graph.length; row++) {

                            for (int column = 0; column < graph[row].length; column++) {

                                System.out.print(graph[row][column] + "\t");

                            }
                            System.out.println();

                        }

                        // The procedure if the user doesn't want to change or delete any capacities
                    } else{
                        System.out.println("You should enter a valid number of nodes which your flow network has to update!");
                        System.out.println("Since, the capacities won't be changed and the maximum flow will be recalculated according to the previously given capacities.");

                    }

                    // Calculating the max flow after the capacities of edges have changed (or not changed)
                } while (runProgram);


                // Stopwatch will start here
                Stopwatch timer2 = new Stopwatch();

                System.out.println("\nThe maximum possible flow from source node " + m.s + " to sink node " + m.t + " is : " +
                        m.fordFulkerson(graph,m.s , m.t));


                // Stopwatch will stop here
                StdOut.println("Elapsed time for the calculation : " + timer2.elapsedTime() + " seconds");
            }


            // Asking whether to run again or not
            System.out.print("\nDo you wish to enter a new flow network? (y/n) : ");
            reply = sc1.next();   // getting user's  answer


        } while (reply.equalsIgnoreCase("y"));  // if input == y then the program will run again

        System.out.println("Bye then.Have a great day! ");  // else it will greet and exit


    }

    // Creating a class to implement the necessary algorithms and methods
    public static class algorithm{

        // Initializing variables
        int V = number;   // Number of vertices in graph
        int s = source;   // Source node of the network flow
        int t = sink;    // Sink node of the network flow


        /* Returns true if there is a path from source 's' to sink
          't' in residual graph. Also fills parent[] to store the
          path */
        boolean bfs(int rGraph[][], int s, int t, int parent[])  // Breadth first search has been used to find augmenting paths
        {
            // Create a visited array and mark all vertices as not visited
            boolean visited[] = new boolean[V];
            for(int i=0; i<V; ++i)
                visited[i]=false;

            // Create a queue, enqueue source vertex and mark source vertex as visited
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(s);
            visited[s] = true;
            parent[s]=-1;

            // Standard BFS Loop
            while (queue.size()!=0)
            {
                int u = queue.poll();

                for (int v=0; v<V; v++)
                {
                    if (visited[v]==false && rGraph[u][v] > 0)
                    {
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }

            // If we reached sink in BFS starting from source, then return true, else false
            return (visited[t] == true);
        }

        // Method to return the maximum flow from s to t in the given graph
        int fordFulkerson(int graph[][], int s, int t)
        {
            int u, v;

            /* Create a residual graph and fill the residual graph
               with given capacities in the original graph as
               residual capacities in residual graph

               Residual graph where rGraph[i][j] indicates
               residual capacity of edge from i to j (if there
               is an edge. If rGraph[i][j] is 0, then there is
               not) */

            int rGraph[][] = new int[V][V]; // 2D array has used here

            for (u = 0; u < V; u++)
                for (v = 0; v < V; v++)
                    rGraph[u][v] = graph[u][v];

            // This array is filled by BFS and to store path
            int parent[] = new int[V];

            int max_flow = 0;  // There is no flow initially

            // Augment the flow while there is a path from source to sink
            while (bfs(rGraph, s, t, parent))
            {
                /* Find minimum residual capacity of the edges
                   along the path filled by BFS. Or we can say
                   find the maximum flow through the path found. */

                int path_flow = Integer.MAX_VALUE;
                for (v=t; v!=s; v=parent[v])
                {
                    u = parent[v];
                    path_flow = Math.min(path_flow, rGraph[u][v]);
                }

                // update residual capacities of the edges and reverse edges along the path
                for (v=t; v != s; v=parent[v])
                {
                    u = parent[v];
                    rGraph[u][v] -= path_flow;
                    rGraph[v][u] += path_flow;
                }

                // Add path flow to overall flow
                max_flow += path_flow;

            }

            // Return the overall flow (max flow)
            return max_flow;


        }

    }

}
