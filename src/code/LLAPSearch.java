package code;
import code.constants.OperatorTypes;
import code.constants.ProblemConstants;

import java.util.ArrayList;
import java.util.HashMap;

public class LLAPSearch extends GenericSearch{
    private final static int moneyToSpend = 100000;

    @Override
    public State initState(String problem){
        problemMap = Parser.parseProblem(problem);
        ArrayList<OperatorTypes> OriginalOperations = new ArrayList<>();
        OriginalOperations.add(OperatorTypes.REQUESTENERGY); OriginalOperations.add(OperatorTypes.REQUESTMATERIALS);
        OriginalOperations.add(OperatorTypes.REQUESTFOOD);
        OriginalOperations.add(OperatorTypes.BUILD1); OriginalOperations.add(OperatorTypes.BUILD2);
        return new State(problemMap.get(ProblemConstants.initialProsperity),
                        problemMap.get(ProblemConstants.initialFood),
                        problemMap.get(ProblemConstants.initialMaterials),
                        problemMap.get(ProblemConstants.initialEnergy), 0,OriginalOperations);
    }


    @Override
    public Node expand(Node node,OperatorTypes operation){
        State oldState = node.getState();
        int newFood = 0, newMaterials = 0, newEnergy = 0, newProsperity, moneySpentFood,moneySpentEnergy,moneySpentMaterials,moneySpent, nodeCost;
        switch(operation){
            case BUILD1:
                newFood = oldState.getFood() - problemMap.get(ProblemConstants.foodUseBUILD1);
                newMaterials = oldState.getMaterials() - problemMap.get(ProblemConstants.materialsUseBUILD1);
                newEnergy = oldState.getEnergy() - problemMap.get(ProblemConstants.energyUseBUILD1);

                moneySpentFood = problemMap.get(ProblemConstants.foodUseBUILD1) * problemMap.get(ProblemConstants.unitPriceFood);
                moneySpentMaterials = problemMap.get(ProblemConstants.materialsUseBUILD1) * problemMap.get(ProblemConstants.unitPriceMaterials);
                moneySpentEnergy = problemMap.get(ProblemConstants.energyUseBUILD1) * problemMap.get(ProblemConstants.unitPriceEnergy);

                newProsperity = oldState.getProsperity() + problemMap.get(ProblemConstants.prosperityBUILD1);

                nodeCost = problemMap.get(ProblemConstants.priceBUILD1) + moneySpentEnergy + moneySpentFood + moneySpentMaterials;
                moneySpent = oldState.getMoneySpent() + nodeCost;

                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, new ArrayList<>(oldState.getOperations()));
                        return makeNode(newState, node, operation, node.getDepth() + 1, node.getPathCost() + nodeCost);
                    }
                else{
                    return null;
                }
            case BUILD2:
                newFood = oldState.getFood() - problemMap.get(ProblemConstants.foodUseBUILD2);
                newMaterials = oldState.getMaterials() - problemMap.get(ProblemConstants.materialsUseBUILD2);
                newEnergy = oldState.getEnergy() - problemMap.get(ProblemConstants.energyUseBUILD2);
                newProsperity = oldState.getProsperity() + problemMap.get(ProblemConstants.prosperityBUILD2);
                moneySpentFood = problemMap.get(ProblemConstants.foodUseBUILD2) * problemMap.get(ProblemConstants.unitPriceFood);
                moneySpentMaterials = problemMap.get(ProblemConstants.materialsUseBUILD2) * problemMap.get(ProblemConstants.unitPriceMaterials);
                moneySpentEnergy = problemMap.get(ProblemConstants.energyUseBUILD2) * problemMap.get(ProblemConstants.unitPriceEnergy);
                nodeCost =  moneySpentEnergy + moneySpentFood + moneySpentMaterials;
                moneySpent = oldState.getMoneySpent() + problemMap.get(ProblemConstants.priceBUILD2) + nodeCost;
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, new ArrayList<>(oldState.getOperations()));
                        return makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                    }
                else{
                    return null;
                }
            case REQUESTFOOD:
                newMaterials += oldState.getMaterials() - 1;
                newEnergy += oldState.getEnergy() - 1;
                newProsperity = oldState.getProsperity();
                newFood += oldState.getFood() - 1;
                nodeCost = problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                moneySpent = oldState.getMoneySpent() + nodeCost;

                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, new ArrayList<>(oldState.getOperations()));
                        return makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                    }
                else{
                    return null;
                }
            case REQUESTMATERIALS:
                newFood += oldState.getFood() - 1;
                newEnergy += oldState.getEnergy() - 1;
                newMaterials = oldState.getMaterials() - 1;

                newProsperity = oldState.getProsperity();
                moneySpent = oldState.getMoneySpent() + problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, new ArrayList<>(oldState.getOperations()));
                        return makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                    }
                else{
                    return null;
                }
            case REQUESTENERGY:
                newFood += oldState.getFood() - 1;
                newMaterials += oldState.getMaterials() - 1;
                newEnergy += oldState.getEnergy() - 1;

                newProsperity = oldState.getProsperity();
                moneySpent = oldState.getMoneySpent() + problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, new ArrayList<>(oldState.getOperations()));
                        return makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                    }
                else{
                    return null;
                }
            case WAIT:
                newFood += oldState.getFood() - 1;
                newMaterials += oldState.getMaterials() - 1;
                newEnergy += oldState.getEnergy() - 1;
                moneySpent = oldState.getMoneySpent() + problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                    State newState = new State(oldState.getProsperity(), newFood, newMaterials, newEnergy, moneySpent, new ArrayList<>(oldState.getOperations()));
                    return makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
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
        StringBuilder plan = new StringBuilder();
        int nodesExpanded = 0;
        if(goal == null){
            return "NOSOLUTION";
        }
        else{
            int cost = goal.getPathCost();
            String temp = "";

            while(goal.getParentNode() != null){
//                System.out.println("Started "+strategy);
                nodesExpanded++;
//                System.out.println(goal.getPathCost());
                plan.insert(0, goal.getOperator() + temp);
                goal = goal.getParentNode();
                temp = ",";
            }
//            System.out.println(goal.getParentNode().getState());
            System.out.println(plan + ";" + cost + ";" + nodesExpanded);
            return plan + ";" + cost + ";" + nodesExpanded;
        }
    }

    public static String solve(String initialState, String strategy, boolean visualize){
        LLAPSearch LLAPSearchInstance = new LLAPSearch();
        return LLAPSearchInstance.search(initialState, visualize, strategy);
    }

//    public static void main(String[] args) {
//        System.out.println("Started");
//        String initialState0 = "17;" +
//                "49,30,46;" +
//                "7,57,6;" +
//                "7,1;20,2;29,2;" +
//                "350,10,9,8,28;" +
//                "408,8,12,13,34;";
//        // String init = "0;" +
//        // "19,35,40;" +
//        // "27,84,200;" +
//        // "15,2;37,1;19,2;" +
//        // "569,11,20,3,50;"+
//        // "115,5,8,21,38;" ;
//        String solution = solve(initialState0, "BF", false);
//        System.out.println("Done");
//        System.out.println(solution);
//
//    }

}