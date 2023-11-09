package code;
import java.util.ArrayList;
import java.util.List;

public class State {
    private int prosperity;
    private int food;
    private int materials;
    private int energy;
    private int moneySpent;
    List <String> operations = new ArrayList<String>();
    private boolean resourceRequested = false;
    private int turnsUntilResourceAvailable = 0;
    private ResourceTypes resourceRequestedType;
    private int resourceRequestedAmount = 0;

    public State(int prosperity, int food, int materials, int energy, int moneySpent, List <String> operations){
        this.prosperity = prosperity;
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.moneySpent = moneySpent;
        this.operations = operations;
    }

    public void removeRequestEffect(){
        operations.remove("WAIT");
        operations.add("REQUESTFOOD");
        operations.add("REQUESTMATERIALS");
        operations.add("REQUESTENERGY");
        resourceRequested = false;
        resourceRequestedType = null;
        resourceRequestedAmount = 0;
        turnsUntilResourceAvailable = 0;
    }

    public List <String> applyRequestAftermath(){
        List<String> newStateOperations = new ArrayList<String>(operations);
        newStateOperations.remove("REQUESTFOOD");
        newStateOperations.remove("REQUESTMATERIALS");
        newStateOperations.remove("REQUESTENERGY");
        newStateOperations.add("WAIT");
        return newStateOperations;
    }
    public int getProsperity() {
        return prosperity;
    }

    public int getFood() {
        return food;
    }

    public int getMaterials() {
        return materials;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setMaterials(int materials) {
        this.materials = materials;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
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

    public boolean isGoalState(){
        if(this.prosperity >= 100){
            return true;
        }
        return false;
    }



    public List <String> getStateOperations(){
        return operations;
    }

    public String toString(){
        return "Food "+ getFood() + " Prop " + getProsperity() + " Materials " + getMaterials() + " Energy " + getEnergy() + " Money Spent "+ getMoneySpent();
    }

}