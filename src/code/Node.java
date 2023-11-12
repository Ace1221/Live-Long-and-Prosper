package code;

import code.constants.OperatorTypes;
import code.constants.ProblemConstants;
import code.constants.ResourceTypes;

import java.util.ArrayList;
import java.util.Objects;

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
            else{
                if(this.parentNode.isResourceRequested()){
                    this.resourceRequested = true;
                    this.turnsUntilResourceAvailable = this.parentNode.getTurnsUntilResourceAvailable() - 1;
                    this.resourceRequestedType = this.parentNode.getResourceRequestedType();
                    this.resourceRequestedAmount = this.parentNode.getResourceRequestedAmount();
                }
            }

            if(this.isResourceRequested()) {
                if (this.getTurnsUntilResourceAvailable() == 0) {
                    // add the requested resource and set flags to false if operator is not a requested material
                    switch (this.getResourceRequestedType()) {
                        case FOOD:
                            this.state.setFood(state.getFood() + getResourceRequestedAmount());
                            break;
                        case MATERIALS:
                            this.state.setMaterials(state.getMaterials() + getResourceRequestedAmount());
                            break;
                        case ENERGY:
                            this.state.setEnergy(state.getEnergy() + getResourceRequestedAmount());
                            break;
                        default:
                            break;
                    }
                    this.resourceRequested = false;
                    this.turnsUntilResourceAvailable = 0;
                    this.resourceRequestedType = null;
                    this.resourceRequestedAmount = 0;
                    state.endRequestOperations();
                }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node otherNode = (Node) o;
        boolean equal = true;
        if(resourceRequestedType!= null){
            equal = (this.resourceRequestedType.equals(otherNode.resourceRequestedType));
        }
        State currentNodeState = this.getState();
        State otherNodeState = otherNode.getState();

        equal &= currentNodeState.getProsperity() == otherNodeState.getProsperity() &
                currentNodeState.getEnergy() == otherNodeState.getEnergy() &
                currentNodeState.getMaterials() == otherNodeState.getMaterials() &
                currentNodeState.getFood() == otherNodeState.getFood() &
                currentNodeState.getMoneySpent() == otherNodeState.getMoneySpent();
        return equal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceRequestedType, state.getProsperity(),state.getEnergy(),state.getMaterials(),state.getFood(),state.getMoneySpent());
    }



    
}
