public class Node implements Comparable {

    private State state;
    private Node parentNode;
    private String operator;
    private int pathCost;
    private int depth;
    public double priority = 0;
    public Node(State state, Node parentNode, String operator, int depth, int pathCost) {
        this.state = state;
        this.parentNode = parentNode;
        this.operator = operator;
        this.pathCost = pathCost;
        this.depth = depth;
    }

    public State getState() {
        return state;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public String getOperator() {
        return operator;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public int compareTo(Object o) {
        Node n = (Node) o;
        return Double.compare(n.priority, this.priority);
    }



    
}
