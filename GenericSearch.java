import java.util.*;;

public class GenericSearch {
    HashMap<ProblemConstants, Integer> problemMap = new HashMap<ProblemConstants, Integer>();
    List <String> operations = new ArrayList<String>();

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

    public Node generalSearch(String problem, String QingFunction){
        State initState = initState(problem);
        Node initNode = makeNode(initState, null, null, 0, 0);
        PriorityQueue<Node> nodes = makeQueue(initNode, QingFunction);
        Node node = null;
        while(!nodes.isEmpty()){
            node = nodes.poll();
            if(node.getState().isGoalState()){
                return node;
            }
            for (String operation : operations){
                Node child = expand(node, operation);
                nodes.add(child);
            }

        }
        return null;  
    }






}   