import java.util.HashMap;

public class Parser {

    public static HashMap<ProblemConstants,Integer> parseProblem(String problem ){
         HashMap<ProblemConstants, Integer> problemMap = new HashMap<ProblemConstants, Integer>();
        // parse problem
        String[] problemSections = problem.split(";");

        for(int i = 0; i< problemSections.length; i++){
            switch(i){
                case 0:
                    int initialProsperity = Integer.parseInt(problemSections[i]);
                    problemMap.put(ProblemConstants.initialProsperity, initialProsperity);
                break;
                case 1:
                    String[] initialResources = problemSections[i].split(",");
                    int initialFood = Integer.parseInt(initialResources[0]);
                    int initialMaterials = Integer.parseInt(initialResources[1]);
                    int initialEnergy = Integer.parseInt(initialResources[2]);
                    problemMap.put(ProblemConstants.initialFood, initialFood);
                    problemMap.put(ProblemConstants.initialMaterials, initialMaterials);
                    problemMap.put(ProblemConstants.initialEnergy, initialEnergy);
                break;
                case 2:
                    String[] unitPrices = problemSections[i].split(",");
                    int unitPriceFood = Integer.parseInt(unitPrices[0]);
                    int unitPriceMaterials = Integer.parseInt(unitPrices[1]);
                    int unitPriceEnergy = Integer.parseInt(unitPrices[2]);
                    problemMap.put(ProblemConstants.unitPriceFood, unitPriceFood);
                    problemMap.put(ProblemConstants.unitPriceMaterials, unitPriceMaterials);
                    problemMap.put(ProblemConstants.unitPriceEnergy, unitPriceEnergy);  
                break;
                case 3:
                    String[] foodParams = problemSections[i].split(",");
                    int amountRequestFood = Integer.parseInt(foodParams[0]);
                    int delayRequestFood = Integer.parseInt(foodParams[1]);
                    problemMap.put(ProblemConstants.amountRequestFood, amountRequestFood);
                    problemMap.put(ProblemConstants.delayRequestFood, delayRequestFood);
                break;
                case 4:
                    String[] materialsParams = problemSections[i].split(",");
                    int amountRequestMaterials = Integer.parseInt(materialsParams[0]);
                    int delayRequestMaterials = Integer.parseInt(materialsParams[1]);
                    problemMap.put(ProblemConstants.amountRequestMaterials, amountRequestMaterials);
                    problemMap.put(ProblemConstants.delayRequestMaterials, delayRequestMaterials);
                break;
                case 5:
                    String[] energyParams = problemSections[i].split(",");
                    int amountRequestEnergy = Integer.parseInt(energyParams[0]);
                    int delayRequestEnergy = Integer.parseInt(energyParams[1]);
                    problemMap.put(ProblemConstants.amountRequestEnergy, amountRequestEnergy);
                    problemMap.put(ProblemConstants.delayRequestEnergy, delayRequestEnergy);
                break;
                case 6:
                    String[] build1Params = problemSections[i].split(",");
                    int priceBUILD1 = Integer.parseInt(build1Params[0]);
                    int foodUseBUILD1 = Integer.parseInt(build1Params[1]);
                    int materialsUseBUILD1 = Integer.parseInt(build1Params[2]);
                    int energyUseBUILD1 = Integer.parseInt(build1Params[3]);
                    int prosperityBUILD1 = Integer.parseInt(build1Params[4]);
                    problemMap.put(ProblemConstants.priceBUILD1, priceBUILD1);
                    problemMap.put(ProblemConstants.foodUseBUILD1, foodUseBUILD1);
                    problemMap.put(ProblemConstants.materialsUseBUILD1, materialsUseBUILD1);
                    problemMap.put(ProblemConstants.energyUseBUILD1, energyUseBUILD1);
                    problemMap.put(ProblemConstants.prosperityBUILD1, prosperityBUILD1);
                break;
                case 7:
                    String[] build2Params = problemSections[i].split(",");
                    int priceBUILD2 = Integer.parseInt(build2Params[0]);
                    int foodUseBUILD2 = Integer.parseInt(build2Params[1]);
                    int materialsUseBUILD2 = Integer.parseInt(build2Params[2]);
                    int energyUseBUILD2 = Integer.parseInt(build2Params[3]);
                    int prosperityBUILD2 = Integer.parseInt(build2Params[4]);
                    problemMap.put(ProblemConstants.priceBUILD2, priceBUILD2);
                    problemMap.put(ProblemConstants.foodUseBUILD2, foodUseBUILD2);
                    problemMap.put(ProblemConstants.materialsUseBUILD2, materialsUseBUILD2);
                    problemMap.put(ProblemConstants.energyUseBUILD2, energyUseBUILD2);
                    problemMap.put(ProblemConstants.prosperityBUILD2, prosperityBUILD2);
                break;
                default:
                break;
            }
        }
        return problemMap;

    }
    
}
