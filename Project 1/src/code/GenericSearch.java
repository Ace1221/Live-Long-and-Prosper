package code;
import code.constants.*;

import java.util.*;

public class GenericSearch {
    public static HashMap<ProblemConstants, Integer> problemMap = new HashMap<>();

    HashSet<Node> expandedNodes = new HashSet<>();
    int iterativeLevel = 0;
    long iterativeExpandedNodesCounter = 0;

    int priceToUseHeuristic1;
    int priceToUseHeuristic2;
    int prosperityIncreaseToUse;
    public boolean visualize = false;

    public void initializeHeuristicVariables(){

        int moneySpentFoodBuild1 = problemMap.get(ProblemConstants.foodUseBUILD1) * problemMap.get(ProblemConstants.unitPriceFood);
        int moneySpentMaterialsBuild1 = problemMap.get(ProblemConstants.materialsUseBUILD1) * problemMap.get(ProblemConstants.unitPriceMaterials);
        int moneySpentEnergyBuild1 = problemMap.get(ProblemConstants.energyUseBUILD1) * problemMap.get(ProblemConstants.unitPriceEnergy);
        int moneySpentFoodBuild2 = problemMap.get(ProblemConstants.foodUseBUILD2) * problemMap.get(ProblemConstants.unitPriceFood);
        int moneySpentMaterialsBuild2 = problemMap.get(ProblemConstants.materialsUseBUILD2) * problemMap.get(ProblemConstants.unitPriceMaterials);
        int moneySpentEnergyBuild2 = problemMap.get(ProblemConstants.energyUseBUILD2) * problemMap.get(ProblemConstants.unitPriceEnergy);

        int priceUseBuild1Heuristic1 = moneySpentEnergyBuild1 + moneySpentFoodBuild1 + moneySpentMaterialsBuild1;
        int priceUseBuild2Heuristic1 = moneySpentEnergyBuild2 + moneySpentFoodBuild2 + moneySpentMaterialsBuild2;


        int priceUseBuild1Heuristic2 = problemMap.get(ProblemConstants.priceBUILD1);
        int priceUseBuild2Heuristic2 = problemMap.get(ProblemConstants.priceBUILD2);

        int prosperityIncreaseBuild1 = problemMap.get(ProblemConstants.prosperityBUILD1);
        int prosperityIncreaseBuild2 = problemMap.get(ProblemConstants.prosperityBUILD2);

        this.prosperityIncreaseToUse = Math.max(prosperityIncreaseBuild1,prosperityIncreaseBuild2);
        this.priceToUseHeuristic1 = Math.min(priceUseBuild1Heuristic1,priceUseBuild2Heuristic1);
        this.priceToUseHeuristic2 = Math.min(priceUseBuild1Heuristic2,priceUseBuild2Heuristic2);
    }


    public State initState(String problem){
        return null;
    }

    public Node makeNode(State state, Node parentNode, OperatorTypes operator, int depth, int pathCost){
        return new Node(state, parentNode, operator, depth, pathCost);
    }

    public PriorityQueue<Node> makeQueue(Node node, String QingFunction){
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(node);
        return queue;
    }

    public boolean isGoalState(Node node){
        return node.getState().isGoalState();
    }

    public Node expand(Node node, OperatorTypes Operation){
        return null;
    }

    public int getHeuristic1(Node node){
        int prosperityLeft = Math.max(100 - node.getState().getProsperity(),0);
        int levelsLeft = prosperityLeft/prosperityIncreaseToUse;
        return -(priceToUseHeuristic1 * levelsLeft);
    }

    public int getHeuristic2(Node node){
        int prosperityLeft = Math.max(100 - node.getState().getProsperity(),0);
        int levelsLeft = prosperityLeft/prosperityIncreaseToUse;
        return -(priceToUseHeuristic2 * levelsLeft);
    }

    public void enqueue(PriorityQueue<Node> nodes, Node node, String strategy, Node initNode){
        List <OperatorTypes> operations = node.getState().getOperations();
        for (OperatorTypes operation: operations) {
            Node child = expand(node, operation);
            if (child != null && !expandedNodes.contains(child)) {
                expandedNodes.add(child);
                switch (strategy) {
                    case "BF":
                        child.priority = - child.getDepth();
                        break;

                    case "DF":
                        child.priority = child.getParentNode().priority + 1;
                        break;

                    case "ID":
                        child.priority = child.getParentNode().priority + 1;
                        break;

                    case "UC":
                        child.priority = -child.getState().getMoneySpent();
                        break;

                    case "GR1":
                        child.priority = getHeuristic1(child);
                        break;

                    case "GR2":
                        child.priority = getHeuristic2(child);
                        break;

                    case "AS1":
                        child.priority = -child.getState().getMoneySpent() + getHeuristic1(child);
                        break;

                    case "AS2":
                        child.priority = -child.getState().getMoneySpent() + getHeuristic2(child);
                        break;

                    default:
                        break;
                }
                nodes.add(child);

            }

        }
        if(strategy.equals("ID") && node.getDepth() >= iterativeLevel){
            iterativeExpandedNodesCounter += expandedNodes.size();
            nodes.clear();
            expandedNodes.clear();
            nodes.add(initNode);
            iterativeLevel ++;
            return;
        }
    }

    public Node generalSearch(String problem, String QingFunction){
        State initState = initState(problem);
        Node initNode = makeNode(initState, null, null, 0, 0);
        PriorityQueue<Node> nodes = makeQueue(initNode, QingFunction);
        Node node;
        int lastDepth = 0;
        while(!nodes.isEmpty()){
            node = nodes.poll();
            if(this.visualize)
            {
                if(node.getDepth()> lastDepth){
                    if(QingFunction.equals("BF"))
                    {
                        System.out.println("----------------------------------------------");
                    }
                    lastDepth = node.getDepth();
                }
                System.out.println("Pulled");
                System.out.println("Depth: " + node.getDepth());
                System.out.println("Resource Requested: " + node.isResourceRequested());
                System.out.println("Resource Requested Type: " + node.getResourceRequestedType());
                System.out.println("Resource Requested Amount: "+ node.getResourceRequestedAmount());
                System.out.println("Turns until resource available: " + node.getTurnsUntilResourceAvailable());
                System.out.println("Operator: " + node.getOperator());
                if(node.getParentNode()!= null){
                    System.out.println("Parent Node Operator: " + node.getParentNode().getOperator());
                }
                System.out.println(node.getState());
                System.out.println();
                System.out.println();
            }
            if(node.getState().isGoalState()){
                return node;
            }
            enqueue(nodes, node, QingFunction, initNode);
        }
        return null;  
    }






}   