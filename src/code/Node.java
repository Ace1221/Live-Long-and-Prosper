package code;

import code.constants.OperatorTypes;
import code.constants.ProblemConstants;
import code.constants.ResourceTypes;

import java.util.ArrayList;

public class Node implements Comparable {

    private State state;
    private Node parentNode;
    private OperatorTypes operator;
    private int pathCost;
    private int depth;
    public double priority = 0;
    private boolean resourceRequested = false;
    private int turnsUntilResourceAvailable = 0;
    private ResourceTypes resourceRequestedType;
    private int resourceRequestedAmount = 0;

    public Node(State state, Node parentNode, OperatorTypes operator, int depth, int pathCost) {
        this.state = state;
        this.parentNode = parentNode;
        this.operator = operator;
        this.pathCost = pathCost;
        this.depth = depth;
        handleRequestedResources();
    }

    public String toString()
    {
        String output = state.toString();
        if(resourceRequestedType!= null){
           output+= resourceRequestedType.toString();
        }
        return output;
    }
    private void handleRequestedResources()
    {
        if(this.parentNode!=null){
            if(this.parentNode.isResourceRequested()) {
                if (this.parentNode.getTurnsUntilResourceAvailable() == 1) {
                    // add the requested resource and set flags to false if operator is not a requested material
                    switch (parentNode.getResourceRequestedType()) {
                        case FOOD:
                            this.state.setFood(state.getFood() + parentNode.getResourceRequestedAmount());
                            break;
                        case MATERIALS:
                            this.state.setMaterials(state.getMaterials() + parentNode.getResourceRequestedAmount());
                            break;
                        case ENERGY:
                            this.state.setEnergy(state.getEnergy() + parentNode.getResourceRequestedAmount());
                            break;
                        default:
                            break;
                    }
                    state.endRequestOperations();
                }
                else{
                        // pass flags to new state and set turns to -1
                        this.resourceRequested = true;
                        this.turnsUntilResourceAvailable = this.parentNode.getTurnsUntilResourceAvailable() - 1;
                        this.resourceRequestedType = this.parentNode.getResourceRequestedType();
                        this.resourceRequestedAmount = this.parentNode.getResourceRequestedAmount();
                }
            }

            if(this.operator.equals(OperatorTypes.REQUESTENERGY) || this.operator.equals(OperatorTypes.REQUESTMATERIALS) || this.operator.equals(OperatorTypes.REQUESTFOOD)){
                setResourceRequested(true);
                switch(operator){
                    case REQUESTENERGY:
                        setResourceRequestedType(ResourceTypes.ENERGY);
                        setResourceRequestedAmount(LLAPSearch.problemMap.get(ProblemConstants.amountRequestEnergy));
                        setTurnsUntilResourceAvailable(LLAPSearch.problemMap.get(ProblemConstants.delayRequestEnergy));
                        break;
                    case REQUESTMATERIALS:
                        setResourceRequestedType(ResourceTypes.MATERIALS);
                        setResourceRequestedAmount(LLAPSearch.problemMap.get(ProblemConstants.amountRequestMaterials));
                        setTurnsUntilResourceAvailable(LLAPSearch.problemMap.get(ProblemConstants.delayRequestMaterials));
                        break;
                    case REQUESTFOOD:
                        setResourceRequestedType(ResourceTypes.FOOD);
                        setResourceRequestedAmount(LLAPSearch.problemMap.get(ProblemConstants.amountRequestFood));
                        setTurnsUntilResourceAvailable(LLAPSearch.problemMap.get(ProblemConstants.delayRequestFood));
                        break;
                    default:
                        break;
                }
                state.initializeRequestOperations();
            }
        }

    }

    public State getState() {
        return state;
    }


    public Node getParentNode() {
        return parentNode;
    }

    public OperatorTypes getOperator() {
        return operator;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isResourceRequested() {
        return resourceRequested;
    }

    public int getTurnsUntilResourceAvailable() {
        return turnsUntilResourceAvailable;
    }

    public ResourceTypes getResourceRequestedType() {
        return resourceRequestedType;
    }

    public int getResourceRequestedAmount() {
        return resourceRequestedAmount;
    }

    public void setResourceRequested(boolean resourceRequested) {
        this.resourceRequested = resourceRequested;
    }

    public void setTurnsUntilResourceAvailable(int turnsUntilResourceAvailable) {
        this.turnsUntilResourceAvailable = turnsUntilResourceAvailable;
    }

    public void setResourceRequestedType(ResourceTypes resourceRequestedType) {
        this.resourceRequestedType = resourceRequestedType;
    }

    public void setResourceRequestedAmount(int resourceRequestedAmount) {
        this.resourceRequestedAmount = resourceRequestedAmount;
    }

    @Override
    public int compareTo(Object o) {
        Node n = (Node) o;
        return Double.compare(n.priority, this.priority);
    }



    
}
