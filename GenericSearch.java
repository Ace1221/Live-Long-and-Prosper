import java.util.*;;

public class GenericSearch {
    HashMap<ProblemConstants, Integer> problemMap = new HashMap<ProblemConstants, Integer>();
    int iterativeLevel = 0;

    public State initState(String problem){
        return null;
    }

    public Node makeNode(State state, Node parentNode, String operator, int depth, int pathCost){
        Node node = new Node(state, parentNode, operator, depth, pathCost);
        return node;
    }

    public PriorityQueue<Node> makeQueue(Node node, String QingFunction){
        PriorityQueue<Node> queue = new PriorityQueue<Node>();

        queue.add(node);
        return queue;
    }

    public boolean isGoalState(Node node){
        return node.getState().isGoalState();
    }

    public Node expand(Node node, String Operation){
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
        return - (node.getState().getMoneySpent() / node.getState().getProsperity());
    }

    public void enque(PriorityQueue<Node> nodes, Node node, String strategy, Node initNode){
        List <String> ops = node.getState().getStateOperations();
        if(strategy=="ID" && node.getDepth() >= iterativeLevel){
            nodes.clear();
            nodes.add(initNode);
            iterativeLevel ++;
            return;
        }

        for (String operation : ops){
            Node child = expand(node, operation);
            if(child != null){
                switch (strategy) {
                    case "BF":
                        break;

                    case "DF":
                        child.priority = child.getParentNode().priority + 1;
                        break;

                    case "ID":
                        child.priority = child.getParentNode().priority + 1;
                        break;

                    case "UC":
                        child.priority = - child.getPathCost();
                        break;

                    case "GR1":
                        child.priority = getHeuristic1(child);
                        break;

                    case "GR2":                      
                        child.priority = getHeuristic2(child);
                        System.out.println(child.priority);
                        break;

                    case "AS1":
                        child.priority = - child.getPathCost() + getHeuristic1(child);
                        break;

                    case "AS2":
                        child.priority = - child.getPathCost() + getHeuristic2(child);
                        break;
                        
                
                    default:
                        break;
                }
                nodes.add(child);
                
            }
        }
    }

    public Node generalSearch(String problem, String QingFunction){
        State initState = initState(problem);
        Node initNode = makeNode(initState, null, null, 0, 0);
        PriorityQueue<Node> nodes = makeQueue(initNode, QingFunction);
        Node node = null;
        while(!nodes.isEmpty()){
            System.out.println("Pulled");
            node = nodes.poll();
            System.out.println(node.getState());
            if(node.getState().isGoalState()){
                System.out.println(node.getState());
                return node;
            }
            
            enque(nodes, node, QingFunction, initNode);

        }
        return null;  
    }






}   