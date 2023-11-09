package code;
public class Node implements Comparable {

    private State state;
    private Node parentNode;
    private String operator;
    private int pathCost;
    private int depth;
    public double priority = 0;
    public boolean requestReadyFlag = false;

    public Node(State state, Node parentNode, String operator, int depth, int pathCost) {
        if(parentNode!= null )
        {
            State oldState = parentNode.getState();
            if(parentNode.requestReadyFlag){
                switch(oldState.getResourceRequestedType()) {
                    case FOOD:
                        state.setFood(state.getFood() + oldState.getResourceRequestedAmount());
                        break;
                    case MATERIALS:
                        state.setMaterials(state.getMaterials() + oldState.getResourceRequestedAmount());
                        break;
                    case ENERGY:
                        state.setEnergy(state.getEnergy() + oldState.getResourceRequestedAmount());
                        break;
                    default:
                        break;
                }
                state.removeRequestEffect();
            }
            else{
                if (oldState.isResourceRequested() && oldState.getTurnsUntilResourceAvailable() > 0){
                    state.setTurnsUntilResourceAvailable(oldState.getTurnsUntilResourceAvailable()-1);
                }
            }
        }

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
