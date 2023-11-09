package code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LLAPSearch extends GenericSearch{
    private final static int moneyToSpend = 100000;
    public HashMap<ProblemConstants,Integer> problemMap;

    @Override
    public State initState(String problem){
        problemMap = Parser.parseProblem(problem);
        ArrayList<String> OriginalOperations = new ArrayList<>();
        OriginalOperations.add("REQUESTFOOD");
        OriginalOperations.add("BUILD1");
        OriginalOperations.add("BUILD2");
        OriginalOperations.add("REQUESTMATERIALS");
        OriginalOperations.add("REQUESTENERGY");
        State initState = new State(problemMap.get(ProblemConstants.initialProsperity),
                                    problemMap.get(ProblemConstants.initialFood),
                                    problemMap.get(ProblemConstants.initialMaterials),
                                    problemMap.get(ProblemConstants.initialEnergy), 0,OriginalOperations);
        return initState;
    }

    public LLAPSearch(){

    }

    private void setStateRequestVariables(State state, ResourceTypes resourceType, int amountRequested){
        state.setResourceRequested(true);
        state.setResourceRequestedType(resourceType);
        state.setResourceRequestedAmount(amountRequested);
        switch(resourceType){
            case FOOD:
                state.setTurnsUntilResourceAvailable(problemMap.get(ProblemConstants.delayRequestFood));
                break;
            case MATERIALS:
                state.setTurnsUntilResourceAvailable(problemMap.get(ProblemConstants.delayRequestMaterials));
                break;
            case ENERGY:
                state.setTurnsUntilResourceAvailable(problemMap.get(ProblemConstants.delayRequestEnergy));
                break;
            default:
                break;
        }
    }
    @Override
    public Node expand(Node node,String operation){
//        System.out.println(operation);
//        System.out.println("D5lt el expand");
        State oldState = node.getState();
        int newFood = 0, newMaterials = 0, newEnergy = 0, newProsperity, moneySpentFood,moneySpentEnergy,moneySpentMaterials,moneySpent, nodeCost;
        List <String> newOperations;
        if(oldState.isResourceRequested() && oldState.getTurnsUntilResourceAvailable() == 0){
            node.requestReadyFlag = true;
        }
        switch(operation){
            case "BUILD1":
                newFood += oldState.getFood() - problemMap.get(ProblemConstants.foodUseBUILD1);
                newMaterials += oldState.getMaterials() - problemMap.get(ProblemConstants.materialsUseBUILD1);
                newEnergy += oldState.getEnergy() - problemMap.get(ProblemConstants.energyUseBUILD1);
                newProsperity = oldState.getProsperity() + problemMap.get(ProblemConstants.prosperityBUILD1);
                moneySpentFood = problemMap.get(ProblemConstants.foodUseBUILD1) * problemMap.get(ProblemConstants.unitPriceFood);
                moneySpentMaterials = problemMap.get(ProblemConstants.materialsUseBUILD1) * problemMap.get(ProblemConstants.unitPriceMaterials);
                moneySpentEnergy = problemMap.get(ProblemConstants.energyUseBUILD1) * problemMap.get(ProblemConstants.unitPriceEnergy);
                nodeCost = problemMap.get(ProblemConstants.priceBUILD1) + moneySpentEnergy + moneySpentFood + moneySpentMaterials;
                moneySpent = oldState.getMoneySpent() + nodeCost;
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, oldState.getStateOperations());
                        Node child = makeNode(newState, node, operation, node.getDepth() + 1, node.getPathCost() + nodeCost);
//                    System.out.println("5lst el expand");

                    return child;
                    }
                else{
                    return null;
                }
            case "BUILD2":
                newFood += oldState.getFood() - problemMap.get(ProblemConstants.foodUseBUILD2);
                newMaterials += oldState.getMaterials() - problemMap.get(ProblemConstants.materialsUseBUILD2);
                newEnergy += oldState.getEnergy() - problemMap.get(ProblemConstants.energyUseBUILD2);
                newProsperity = oldState.getProsperity() + problemMap.get(ProblemConstants.prosperityBUILD2);
                moneySpentFood = problemMap.get(ProblemConstants.foodUseBUILD2) * problemMap.get(ProblemConstants.unitPriceFood);
                moneySpentMaterials = problemMap.get(ProblemConstants.materialsUseBUILD2) * problemMap.get(ProblemConstants.unitPriceMaterials);
                moneySpentEnergy = problemMap.get(ProblemConstants.energyUseBUILD2) * problemMap.get(ProblemConstants.unitPriceEnergy);
                nodeCost =  moneySpentEnergy + moneySpentFood + moneySpentMaterials;
                moneySpent = oldState.getMoneySpent() + problemMap.get(ProblemConstants.priceBUILD2) + nodeCost;
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, oldState.getStateOperations());
                        Node child = makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                        return child;
                    }
                else{
                    return null;
                }
            case "REQUESTFOOD":
                newMaterials += oldState.getMaterials() - 1;
                newEnergy += oldState.getEnergy() - 1;
                newProsperity = oldState.getProsperity();
                newFood += oldState.getFood() - 1;
                nodeCost = problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                moneySpent = oldState.getMoneySpent() + nodeCost;
                newOperations = oldState.applyRequestAftermath();

                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, newOperations);
                        setStateRequestVariables(newState,ResourceTypes.FOOD, problemMap.get(ProblemConstants.amountRequestFood));
                        Node child = makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                        return child;
                    }
                else{
                    return null;
                }
            case "REQUESTMATERIALS":
                newFood += oldState.getFood() - 1;
                newEnergy += oldState.getEnergy() - 1;
                newMaterials = oldState.getMaterials() - 1;

                newProsperity = oldState.getProsperity();
                moneySpent = oldState.getMoneySpent() + problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                newOperations = oldState.applyRequestAftermath();
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, newOperations);
                        setStateRequestVariables(newState,ResourceTypes.MATERIALS, problemMap.get(ProblemConstants.amountRequestMaterials));
                        Node child = makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                        return child;
                    }
                else{
                    return null;
                }
            case "REQUESTENERGY":
                newFood += oldState.getFood() - 1;
                newMaterials += oldState.getMaterials() - 1;
                newEnergy += oldState.getEnergy() - 1;

                newProsperity = oldState.getProsperity();
                moneySpent = oldState.getMoneySpent() + problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                newOperations = oldState.applyRequestAftermath();
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, newOperations);
                        setStateRequestVariables(newState,ResourceTypes.ENERGY, problemMap.get(ProblemConstants.amountRequestEnergy));
                        Node child = makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                        return child;
                    }
                else{
                    return null;
                }
            case "WAIT":
                newFood += oldState.getFood() - 1;
                newMaterials += oldState.getMaterials() - 1;
                newEnergy += oldState.getEnergy() - 1;
                moneySpent = oldState.getMoneySpent() + problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                    State newState = new State(oldState.getProsperity(), newFood, newMaterials, newEnergy, moneySpent, oldState.getStateOperations());
                    newState.setTurnsUntilResourceAvailable(oldState.getTurnsUntilResourceAvailable());
                    newState.setResourceRequestedType(oldState.getResourceRequestedType());
                    newState.setResourceRequested(oldState.isResourceRequested());
                    newState.setResourceRequestedAmount(oldState.getResourceRequestedAmount());
                    Node child = makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                    return child;
                }
                else{
                    return null;
                }
            default:
                return null;
                
        }
    }

    public String search(String initialState, boolean visualize, String strategy){
        Node goal = generalSearch(initialState, strategy);
        String plan = "";
        int nodesExpanded = 0;
        if(goal == null){
            return "NOSOLUTION";
        }
        else{
            int cost = goal.getPathCost();
            String temp = "";
            while(goal.getParentNode() != null){
                System.out.println("Started "+strategy);
                nodesExpanded++;
                System.out.println(goal.getPathCost());
                plan = goal.getOperator() + temp + plan;
                goal = goal.getParentNode();
                temp = ",";
            }
            return plan + ";" + cost + ";" + nodesExpanded;
        }
    }

    public static String solve(String initialState, String strategy, boolean visualize){
        LLAPSearch LLAPSearchInstance = new LLAPSearch();
        return LLAPSearchInstance.search(initialState, visualize, strategy);
    }

    public static void main(String[] args) {
        LLAPSearch search = new LLAPSearch();
        System.out.println("Started");
        String initialState0 = "17;" +
                "49,30,46;" +
                "7,57,6;" +
                "7,1;20,2;29,2;" +
                "350,10,9,8,28;" +
                "408,8,12,13,34;";
        // String init = "0;" +
        // "19,35,40;" +
        // "27,84,200;" +
        // "15,2;37,1;19,2;" +
        // "569,11,20,3,50;"+
        // "115,5,8,21,38;" ;
        String solution = search.solve(initialState0, "BF", false);
        System.out.println("Done");
        System.out.println(solution);

    }

}