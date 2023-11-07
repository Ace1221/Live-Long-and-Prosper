import java.util.ArrayList;
import java.util.List;

public class LLAPSearch extends GenericSearch{
    private static int moneyToSpend = 100000;
    private boolean resourceRequested = false;
    private int turnsUntilResourceAvailable = 0;
    private ResourceTypes resourceRequestedType;
    private int resourceRequestedAmount = 0;

    private List <String> OriginalOperations = new ArrayList<String>();

    @Override
    public State initState(String problem){
        problemMap = Parser.parseProblem(problem);
        State initState = new State(problemMap.get(ProblemConstants.initialProsperity),
                                    problemMap.get(ProblemConstants.initialFood),
                                    problemMap.get(ProblemConstants.initialMaterials),
                                    problemMap.get(ProblemConstants.initialEnergy), 0, OriginalOperations);
        return initState;
    }

    public LLAPSearch(){
        OriginalOperations.add("REQUESTFOOD");
        OriginalOperations.add("BUILD1");
        OriginalOperations.add("BUILD2");
        OriginalOperations.add("REQUESTMATERIALS");
        OriginalOperations.add("REQUESTENERGY");
    }

    public List <String> applyRequestAftermath(ResourceTypes resourceType, int amountRequested){
        List <String> newStateOperations = new ArrayList<String>();
        for(String operation: OriginalOperations){
            newStateOperations.add(operation);
        }
        newStateOperations.remove("REQUESTFOOD");
        newStateOperations.remove("REQUESTMATERIALS");
        newStateOperations.remove("REQUESTENERGY");
        newStateOperations.add("WAIT");

        resourceRequested = true;
        resourceRequestedType = resourceType;
        resourceRequestedAmount = amountRequested;
        switch(resourceType){
            case FOOD:
                turnsUntilResourceAvailable = problemMap.get(ProblemConstants.delayRequestFood);
                break;
            case MATERIALS:
                turnsUntilResourceAvailable = problemMap.get(ProblemConstants.delayRequestMaterials);
                break;
            case ENERGY:
                turnsUntilResourceAvailable = problemMap.get(ProblemConstants.delayRequestEnergy);
                break;
        }
        return newStateOperations;
    }

    // public void removeRequestEffect(){
    //     operations.remove("WAIT");
    //     operations.add("REQUESTFOOD");
    //     operations.add("REQUESTMATERIALS");
    //     operations.add("REQUESTENERGY");
    //     resourceRequested = false;
    //     resourceRequestedType = null;
    //     resourceRequestedAmount = 0;
    //     turnsUntilResourceAvailable = 0;
    // }


    @Override
    public Node expand(Node node,String operation){
        State oldState = node.getState();
        int newFood = 0, newMaterials = 0, newEnergy = 0, newProsperity, moneySpentFood,moneySpentEnergy,moneySpentMaterials,moneySpent, nodeCost;
        List <String> newOperations;
        if(resourceRequested && turnsUntilResourceAvailable == 0){
            switch(resourceRequestedType){
                case FOOD:
                    newFood = oldState.getFood() + resourceRequestedAmount;
                    break;
                case MATERIALS:
                    newMaterials = oldState.getMaterials() + resourceRequestedAmount;
                    break;
                case ENERGY:
                    newEnergy = oldState.getEnergy() + resourceRequestedAmount;
                    break;
                default:
                    break;
            }
            // removeRequestEffect();
        }else if (resourceRequested && turnsUntilResourceAvailable > 0){
            turnsUntilResourceAvailable--;
        }
        System.out.println(operation);
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
                moneySpent = node.getState().getMoneySpent() + nodeCost;
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, OriginalOperations);
                        Node child = makeNode(newState, node, operation, node.getDepth() + 1, node.getPathCost() + nodeCost);
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
                moneySpent = node.getState().getMoneySpent() + problemMap.get(ProblemConstants.priceBUILD2) + nodeCost;
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, OriginalOperations);
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
                moneySpent = node.getState().getMoneySpent() + nodeCost;
                newOperations = applyRequestAftermath(ResourceTypes.FOOD, problemMap.get(ProblemConstants.amountRequestFood));

                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, newOperations);
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
                newOperations = applyRequestAftermath(ResourceTypes.MATERIALS, problemMap.get(ProblemConstants.amountRequestMaterials));
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, newOperations);
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
                moneySpent = node.getState().getMoneySpent() + problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                newOperations = applyRequestAftermath(ResourceTypes.ENERGY, problemMap.get(ProblemConstants.amountRequestEnergy));
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent, newOperations);
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
                    State newState = new State(oldState.getProsperity(), newFood, newMaterials, newEnergy, moneySpent, OriginalOperations);
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

    public String solve(String initialState, String strategy, boolean visualize){
           return search(initialState, visualize, strategy);
    }

    public String search(String initialState, boolean visualize, String strategy){
        Node goal = generalSearch(initialState, strategy);
        int cost = goal.getPathCost();
        String plan = "";
        int nodesExpanded = 0;
        if(goal == null){
            return "No solution found";
        }
        else{
            while(goal.getParentNode() != null){
                System.out.println("Started "+strategy);
                nodesExpanded++;
                System.out.println(goal.getPathCost());
                plan = plan + ","  + goal.getOperator() ;
                goal = goal.getParentNode();
            }
        }
        return plan + ";" + cost + ";" + nodesExpanded;
    }
    

    public static void main(String[] args) {
        LLAPSearch search = new LLAPSearch();
        System.out.println("Started");
        String init =   "50;"+
                        "22,22,22;" +
                        "50,60,70;" +
                        "30,2;19,1;15,1;" +
                        "300,5,7,3,20;" +
                        "500,8,6,3,40;";
        // String init = "0;" +
        // "19,35,40;" +
        // "27,84,200;" +
        // "15,2;37,1;19,2;" +
        // "569,11,20,3,50;"+
        // "115,5,8,21,38;" ;
        String solution = search.solve(init, "AS2", false);
        System.out.println("Done");
        System.out.println(solution);
        
    }

}