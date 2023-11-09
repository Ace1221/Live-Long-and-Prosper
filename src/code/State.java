package code;
import code.constants.OperatorTypes;

import java.util.ArrayList;
import java.util.List;

public class State {
    private final int prosperity;
    private int food;
    private int materials;
    private int energy;
    private final int moneySpent;
    List <OperatorTypes> operations = new ArrayList<OperatorTypes>();

    public State(int prosperity, int food, int materials, int energy, int moneySpent, List <OperatorTypes> operations){
        this.prosperity = prosperity;
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.moneySpent = moneySpent;
        this.operations = operations;
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


    public boolean isGoalState(){
        return this.prosperity >= 100;
    }



    public List <OperatorTypes> getOperations(){
        return operations;
    }


    public void setOperations(ArrayList<OperatorTypes> operations){
        this.operations = operations;
    }

    public void removeOperation(OperatorTypes operation)
    {
        operations.remove(operation);
    }


    public void addOperation(OperatorTypes operation)
    {
        operations.add(operation);
    }

    public void initializeRequestOperations()
    {
        ArrayList<OperatorTypes> operations = new ArrayList<>();
        operations.add(OperatorTypes.WAIT);
        operations.add(OperatorTypes.BUILD1);
        operations.add(OperatorTypes.BUILD2);
        setOperations(operations);
    }

    public void endRequestOperations()
    {
        ArrayList<OperatorTypes> operations = new ArrayList<>();
        operations.add(OperatorTypes.REQUESTENERGY);
        operations.add(OperatorTypes.REQUESTFOOD);
        operations.add(OperatorTypes.REQUESTMATERIALS);
        operations.add(OperatorTypes.BUILD1);
        operations.add(OperatorTypes.BUILD2);
        setOperations(operations);
    }

    public String toString(){
        return "Food "+ getFood() + " Prop " + getProsperity() + " Materials " + getMaterials() + " Energy " + getEnergy() + " Money Spent "+ getMoneySpent();
    }



}