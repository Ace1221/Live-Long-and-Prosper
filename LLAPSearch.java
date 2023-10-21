public class LLAPSearch extends GenericSearch{
    private static int moneyToSpend = 100000;
    private boolean resourceRequested = false;
    private int turnsUntilResourceAvailable = 0;
    private ResourceTypes resourceRequestedType;
    private int resourceRequestedAmount = 0;


    @Override
    public State initState(String problem){
        problemMap = Parser.parseProblem(problem);
        State initState = new State(problemMap.get(ProblemConstants.initialProsperity),
                                    problemMap.get(ProblemConstants.initialFood),
                                    problemMap.get(ProblemConstants.initialMaterials),
                                    problemMap.get(ProblemConstants.initialEnergy), 0);
        return initState;
    }

    public LLAPSearch(){
        operations.add("BUILD1");
        operations.add("BUILD2");
        operations.add("REQUESTFOOD");
        operations.add("REQUESTMATERIALS");
        operations.add("REQUESTENERGY");
    }

    public void applyRequestAftermath(ResourceTypes resourceType, int amountRequested){
        operations.remove("REQUESTFOOD");
        operations.remove("REQUESTMATERIALS");
        operations.remove("REQUESTENERGY");
        operations.add("WAIT");

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


    @Override
    public Node expand(Node node,String operation){
        State oldState = node.getState();
        int newFood = 0, newMaterials = 0, newEnergy = 0, newProsperity, moneySpentFood,moneySpentEnergy,moneySpentMaterials,moneySpent, nodeCost;

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
            removeRequestEffect();
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
                moneySpent = node.getState().getMoneySpent() + nodeCost;
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0 && moneySpent <= moneyToSpend){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent);
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
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent);
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
                applyRequestAftermath(ResourceTypes.FOOD, problemMap.get(ProblemConstants.amountRequestFood));

                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent);
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
                moneySpent = node.getState().getMoneySpent() + problemMap.get(ProblemConstants.unitPriceFood) + problemMap.get(ProblemConstants.unitPriceMaterials) + problemMap.get(ProblemConstants.unitPriceEnergy);
                applyRequestAftermath(ResourceTypes.MATERIALS, problemMap.get(ProblemConstants.amountRequestMaterials));
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent);
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
                applyRequestAftermath(ResourceTypes.ENERGY, problemMap.get(ProblemConstants.amountRequestEnergy));
                if(newFood >= 0 && newMaterials >= 0 && newEnergy >= 0){
                        State newState = new State(newProsperity, newFood, newMaterials, newEnergy, moneySpent);
                        Node child = makeNode(newState, node, operation, node.getDepth() + 1, moneySpent);
                        return child;
                    }
                else{
                    return null;
                }
            case "WAIT":
                newFood += oldState.getFood();
                newMaterials += oldState.getMaterials();
                newEnergy += oldState.getEnergy();
                State newState = new State(oldState.getProsperity(), newFood, newMaterials, newEnergy, oldState.getMoneySpent());
                Node child = makeNode(newState, node, operation, node.getDepth() + 1, node.getPathCost());
                return child;
            default:
                return null;
        }

    }

    public String solve(String initialState, String strategy, boolean visualize){
        switch(strategy){
            case "BF":
                return breadthFirstSearch(initialState,visualize);
            case "DF":
                return depthFirstSearch(initialState, visualize);
            case "ID":
                return iterativeDeepeningSearch(initialState, visualize);
            case "UC":
                return uniformCostSearch(initialState, visualize);
            case "GR1":
                return greedySearch(initialState, 1,visualize);
            case "GR2":
                return greedySearch(initialState, 2,visualize);
            case "AS1":
                return aStarSearch(initialState, 1,visualize);
            case "AS2":
                return aStarSearch(initialState, 2,visualize);
            default:
                return null;
        }

    }

    public String breadthFirstSearch(String initialState, boolean visualize){
        Node goal = generalSearch(initialState, "FIFO");
        String plan = "";
        int nodesExpanded = 0;
        if(goal == null){
            return "No solution found";
        }
        else{
            while(goal.getParentNode() != null){
                nodesExpanded++;
                plan = plan + ","  + goal.getOperator() ;
                goal = goal.getParentNode();
            }
        }
        return plan + ";" + goal.getPathCost() + ";" + nodesExpanded;
    }
    public String depthFirstSearch(String initialState, boolean visualize){
        return null;

    }
    public String iterativeDeepeningSearch(String initialState, boolean visualize){
        return null;

    }
    public String uniformCostSearch(String initialState, boolean visualize){
        return null;

    }
    public String greedySearch(String initialState, int heuristic, boolean visualize){
        return null;

    }
    public String aStarSearch(String initialState, int heuristic, boolean visualize){
        return null;

    }

    public static void main(String[] args) {
        LLAPSearch search = new LLAPSearch();
   
        String init =   "50;"+
                        "22,22,22;" +
                        "50,60,70;" +
                        "30,2;19,1;15,1;" +
                        "300,5,7,3,20;" +
                        "500,8,6,3,40;";

        search.solve(init, "BF", false);

    }

}