package code;
import code.constants.*;

import java.util.*;

public class GenericSearch {
    public static HashMap<ProblemConstants, Integer> problemMap = new HashMap<>();

    HashSet<String> expandedNodes = new HashSet<>();
    int iterativeLevel = 0;
    long iterativeExpandedNodesCounter = 0;

    public State initState(String problem){
        return null;
    }

    public Node makeNode(State state, Node parentNode, OperatorTypes operator, int depth, int pathCost){
        return new Node(state, parentNode, operator, depth, pathCost);
    }

    public PriorityQueue<Node> makeQueue(Node node, String QingFunction){
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(node);
        return queue;
    }

    public boolean isGoalState(Node node){
        return node.getState().isGoalState();
    }

    public Node expand(Node node, OperatorTypes Operation){
        return null;
    }

    public int getHeuristic1(Node node){
        if(node.getState().isGoalState()){
            return 0;
        }
        return - (100 - node.getState().getProsperity());
    }

    public double getHeuristic2(Node node){
        if(node.getState().isGoalState()){
            return 0;
        }
        return - ((double) node.getState().getMoneySpent() / node.getState().getProsperity());
    }

    public void enqueue(PriorityQueue<Node> nodes, Node node, String strategy, Node initNode){
        List <OperatorTypes> operations = node.getState().getOperations();
        for (OperatorTypes operation: operations) {
            Node child = expand(node, operation);
            if (child != null && !expandedNodes.contains(child.toString())) {
                expandedNodes.add(child.toString());
                switch (strategy) {
                    case "BF":
                        child.priority = - child.getDepth();
                        break;

                    case "DF":
                        child.priority = child.getParentNode().priority + 1;
                        break;

                    case "ID":
                        child.priority = child.getParentNode().priority + 1;
                        break;

                    case "UC":
                        child.priority = -child.getPathCost();
                        break;

                    case "GR1":
                        child.priority = getHeuristic1(child);
                        break;

                    case "GR2":
                        child.priority = getHeuristic2(child);
                        break;

                    case "AS1":
                        child.priority = -child.getPathCost() + getHeuristic1(child);
                        break;

                    case "AS2":
                        child.priority = -child.getPathCost() + getHeuristic2(child);
                        break;

                    default:
                        break;
                }
                nodes.add(child);

            }

        }
        if(strategy.equals("ID") && node.getDepth() >= iterativeLevel){
            iterativeExpandedNodesCounter += expandedNodes.size();
            nodes.clear();
            expandedNodes.clear();
            nodes.add(initNode);
            iterativeLevel ++;
            return;
        }
    }

    public Node generalSearch(String problem, String QingFunction){
        State initState = initState(problem);
        Node initNode = makeNode(initState, null, null, 0, 0);
        PriorityQueue<Node> nodes = makeQueue(initNode, QingFunction);
        Node node;
        int lastDepth = 0;
        while(!nodes.isEmpty()){
            node = nodes.poll();
            if(node.getDepth()> lastDepth){
//                System.out.println("----------------------------------------------");
//                if(node.getDepth()>=5){
//                    return null;
//                }
                lastDepth = node.getDepth();
            }
//            System.out.println("Pulled");
//            System.out.println("Depth: " + node.getDepth());
//            System.out.println("Resource Requested: " + node.isResourceRequested());
//            System.out.println("Resource Requested Type: " + node.getResourceRequestedType());
//            System.out.println("Resource Requested Amount: "+ node.getResourceRequestedAmount());
//            System.out.println("Turns until resource available: " + node.getTurnsUntilResourceAvailable());
//            System.out.println("Operator: " + node.getOperator());
//            if(node.getParentNode()!= null){
//                System.out.println("Parent Node Operator: " + node.getParentNode().getOperator());
//            }
//            System.out.println(node.getState());
//            System.out.println();
//            System.out.println();
            if(node.getState().isGoalState()){
//                System.out.println("Goal State");
//                System.out.println(node.getState());
                return node;
            }
//            System.out.println("Size before expanding");
//            System.out.println(nodes.size());
            enqueue(nodes, node, QingFunction, initNode);
//            System.out.println("Size after expanding");
//            System.out.println(nodes.size());
        }
        return null;  
    }






}   