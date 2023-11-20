package tests;

import code.LLAPSearch;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;


public class LLAPPlanChecker {
    int v0;
    int v1;
    int v2;
    int v3;
    HashMap<String, Integer> v4;
    ArrayList<Integer> v5;
    ArrayList<Integer> v6;
    ArrayList<Integer> v7;
    ArrayList<Integer> v8;
    ArrayList<Integer> v9;
    ArrayList<Integer> v10;

    int v11 =100000;
    int v12 =0;

    int v13 =0;
    int v14 =50;

    int v15 = -1;
    public LLAPPlanChecker(String str){

        String[] splitState = str.split(";");

        this.v0 = Integer.parseInt(splitState[0]);

        this.v1 = Integer.parseInt(splitState[1].split(",")[0]);
        this.v2 = Integer.parseInt(splitState[1].split(",")[1]);
        this.v3 = Integer.parseInt(splitState[1].split(",")[2]);

        this.v4 = new HashMap<String, Integer>();
        v4.put("A", Integer.parseInt(splitState[2].split(",")[0]));
        v4.put("B", Integer.parseInt(splitState[2].split(",")[1]));
        v4.put("C", Integer.parseInt(splitState[2].split(",")[2]));


        this.v5 = new ArrayList<Integer>();
        v5.add(0);
        v5.add(1);
        v5.add(1);
        v5.add(1);
        v5.add(0);
        v5.add(Integer.parseInt(splitState[3].split(",")[0]));
        v5.add(Integer.parseInt(splitState[3].split(",")[1]));
        v5.set(0, v5.get(0)+ v5.get(1)* v4.get("A")+ v5.get(2)* v4.get("B")+ v5.get(3)* v4.get("C"));
        this.v6 = new ArrayList<Integer>();
        v6.add(0);
        v6.add(1);
        v6.add(1);
        v6.add(1);
        v6.add(0);
        v6.add(Integer.parseInt(splitState[4].split(",")[0]));
        v6.add(Integer.parseInt(splitState[4].split(",")[1]));
        v6.set(0, v6.get(0)+ v6.get(1)* v4.get("A")+ v6.get(2)* v4.get("B")+ v6.get(3)* v4.get("C"));
        this.v7 = new ArrayList<Integer>();
        v7.add(0);
        v7.add(1);
        v7.add(1);
        v7.add(1);
        v7.add(0);
        v7.add(Integer.parseInt(splitState[5].split(",")[0]));
        v7.add(Integer.parseInt(splitState[5].split(",")[1]));
        v7.set(0, v7.get(0)+ v7.get(1)* v4.get("A")+ v7.get(2)* v4.get("B")+ v7.get(3)* v4.get("C"));
        this.v8 = new ArrayList<Integer>();
        v8.add(0);
        v8.add(1);
        v8.add(1);
        v8.add(1);
        v8.add(0);
        v8.add(0);
        v8.add(0);
        v8.set(0, v8.get(0)+ v8.get(1)* v4.get("A")+ v8.get(2)* v4.get("B")+ v8.get(3)* v4.get("C"));

        this.v9 =  new ArrayList<Integer>();
        this.v10 =  new ArrayList<Integer>();
        for(int i=0;i<5;i++){
            String par1= splitState[6].split(",")[i];
            v9.add(Integer.parseInt(par1));
            String par2= splitState[7].split(",")[i];
            v10.add(Integer.parseInt(par2));
        }
        v9.set(0, v9.get(0)+ v9.get(1)* v4.get("A")+ v9.get(2)* v4.get("B")+ v9.get(3)* v4.get("C"));
        v10.set(0, v10.get(0)+ v10.get(1)* v4.get("A")+ v10.get(2)* v4.get("B")+ v10.get(3)* v4.get("C"));

    }
    public boolean er(String y){
        ArrayList<Integer> x = new ArrayList<>();
        switch (y){
        case "A":
            x = v5;
            break;
        case "B":
            x = v6;
            break;
        case "C":
            x = v7;
            break;
        case "D":
            x = v8;
            break;
        case "E1":
            x = v9;
            break;
        case "E2":
            x = v10;
            break;
        default:
            x = new ArrayList<>();
            break;
        }
        return (this.v1 >= x.get(1) && this.v2 >= x.get(2) && this.v3 >= x.get(3) && this.v11 - this.v12 >= x.get(0));
    }


    public void ur(String y){


        ArrayList<Integer> x = new ArrayList<>();

        switch (y){
            case "A":
                x = v5;
                break;
                case "B":
                x = v6;
                break;
                case "C":
                x = v7;
                break;
            case "D":
                x = v8;
                break;
            case "E1":
                x = v9;
                break;
            case "E2":
                x = v10;
                break;
            default:
                x = new ArrayList<>();
                break;
        }

        this.v1 -= x.get(1);
        this.v2 -= x.get(2);
        this.v3 -= x.get(3);
        this.v12 += x.get(0);
        this.v0 += x.get(4);
    }

    void au(){
        if (v15 !=-1 && v13 >0){
            v13--;
        }
        if (this.v13 ==0 && this.v15 !=-1){

            if(this.v15 ==0){
                this.v1 +=this.v5.get(5);
            }
            if(this.v15 ==1){
                this.v2 +=this.v6.get(5);
            }
            if(this.v15 ==2){
                this.v3 +=this.v7.get(5);
            }
            this.v15 =-1;
            this.v13 =0;
        }
    }

    void mc(){
        if(v1 > v14){  v1 = v14;  }
        if(v2 > v14){  v2 = v14;  }
        if(v3 > v14){  v3 = v14;  }
    }
    boolean f1(String an){
        au();
        int i=-1;
       if(!er(an)){return false;}
       switch (an){
           case "A":
               if (this.v11 -this.v12 < this.v5.get(0)){return false;}i=0;
               v13 = v5.get(6) ;break;
           case "B":
               if (this.v11 -this.v12 < this.v6.get(0)){return false;}i=1;
               v13 = v6.get(6) ;break;
           case "C":
               if (this.v11 -this.v12 < this.v7.get(0)){return false;}i=2;
               v13 = v7.get(6) ;break;
           default: return false;
       }
        this.v15 =i;
        ur(an);
        mc();
    return true;
    }



    boolean f3(){
        au();
        if(!er("D")){return false;}
        ur("D");
        mc();
        return true;
    }

    boolean f2(int i){
        au();
        String an = "E"+i;
        if(!er(an)){ return false;}
        ur(an);
        mc();
        return true;
    }





    public boolean tryPlan(String[] actions, LLAPPlanChecker s) {
		boolean linkin = false;
		for (int i = 0; i < actions.length; i++) {

			switch (actions[i]) {
                case "requestfood":
				linkin = s.f1("A");
				break;
			case "requestenergy":
				linkin = s.f1("C");
				break;
			case "requestmaterials":
				linkin = s.f1("B");
				break;
			case "build1":
				linkin = s.f2(1);
				break;
			case "build2":
				linkin = s.f2(2);
				break;
			case "wait":
				linkin = s.f3();
				break;
			default: linkin = false;
            break;

			}
			if(!linkin) {
				System.out.println("action that failed: "+actions[i] +", order: "+i);
				return false;
				}
	}
		return true;
	}

    boolean cool(){
       return this.v0 >= 100;
    }
public boolean applyPlan(String grid, String solution){
	boolean linkin = true;
	solution = solution.toLowerCase();
    if (solution.equals("nosolution")) {
        return false;
    }
	String[] solutionArray  = solution.split(";");
	String plan = solutionArray[0];
	int blue = Integer.parseInt(solutionArray[1]);
	plan.replace(" ", "");
	plan.replace("\n", "");
	plan.replace("\r", "");
	plan.replace("\n\r", "");
	plan.replace("\t", "");

	String[] actions = plan.split(",");

	LLAPPlanChecker s = new LLAPPlanChecker(grid);
	linkin = tryPlan(actions,s);
	if(!linkin) {
		return false;
		}

    return s.cool() && s.v12 ==blue;
}

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)

    public static class LLAPPublicGrading {

        String initialState0 = "17;" +
                    "49,30,46;" +
                    "7,57,6;" +
                    "7,1;20,2;29,2;" +
                    "350,10,9,8,28;" +
                    "408,8,12,13,34;";
        String initialState1 = "50;" +
                    "12,12,12;" +
                    "50,60,70;" +
                    "30,2;19,2;15,2;" +
                    "300,5,7,3,20;" +
                    "500,8,6,3,40;";
        String initialState2 = "30;" +
                    "30,25,19;" +
                    "90,120,150;" +
                    "9,2;13,1;11,1;" +
                    "3195,11,12,10,34;" +
                    "691,7,8,6,15;";
        String initialState3 = "0;" +
                    "19,35,40;" +
                    "27,84,200;" +
                    "15,2;37,1;19,2;" +
                    "569,11,20,3,50;"+
                    "115,5,8,21,38;" ;

        String initialState4 = "21;" +
                    "15,19,13;" +
                    "50,50,50;" +
                    "12,2;16,2;9,2;" +
                    "3076,15,26,28,40;" +
                    "5015,25,15,15,38;";
        String initialState5 = "72;" +
                    "36,13,35;" +
                    "75,96,62;" +
                    "20,2;5,2;33,2;" +
                    "30013,7,6,3,36;" +
                    "40050,5,10,14,44;";
        String initialState6 = "29;" +
                    "14,9,26;" +
                    "650,400,710;" +
                    "20,2;29,2;38,1;" +
                    "8255,8,7,9,36;" +
                    "30670,12,12,11,36;";
        String initialState7= "1;" +
                "6,10,7;" +
                "2,1,66;" +
                "34,2;22,1;14,2;" +
                "1500,5,9,9,26;" +
                "168,13,13,14,46;";
        String initialState8 = "93;" +
                "46,42,46;" +
                "5,32,24;" +
                "13,2;24,1;20,1;" +
                "155,7,5,10,7;" +
                "5,5,5,4,4;";
        String initialState9 = "50;" +
                "20,16,11;" +
                "76,14,14;" +
                "7,1;7,1;7,1;" +
                "359,14,25,23,39;" +
                "524,18,17,17,38;";
        String initialState10= "32;" +
                "20,16,11;" +
                "76,14,14;" +
                "9,1;9,2;9,1;" +
                "358,14,25,23,39;" +
                "5024,20,17,17,38;";

        @Test(timeout = 120000)
        public void testa0() throws Exception {
            String solution = LLAPSearch.solve(initialState0, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState0);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState0, solution));
        }
        @Test(timeout = 120000)
        public void testa1() throws Exception {
            String solution = LLAPSearch.solve(initialState1, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
        }
        @Test(timeout = 120000)
        public void testa2() throws Exception {
            String solution = LLAPSearch.solve(initialState2, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState2);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState2, solution));
        }
        @Test(timeout = 120000)
        public void testa3() throws Exception {
            String solution = LLAPSearch.solve(initialState3, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState3);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState3, solution));
        }
        @Test(timeout = 120000)
        public void testa4() throws Exception {
            String solution = LLAPSearch.solve(initialState4, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState4);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState4, solution));
        }
        @Test(timeout = 120000)
        public void testa5() throws Exception {
            String solution = LLAPSearch.solve(initialState5, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState5);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState5, solution));
        }
        @Test(timeout = 120000)
        public void testa6() throws Exception {
            String solution = LLAPSearch.solve(initialState6, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState6);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState6, solution));
        }
        @Test(timeout = 120000)
        public void testa7() throws Exception {
            String solution = LLAPSearch.solve(initialState7, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState7);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState7, solution));
        }
        @Test(timeout = 120000)
        public void testa8() throws Exception {
            String solution = LLAPSearch.solve(initialState8, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState8);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState8, solution));
        }
        @Test(timeout = 120000)
        public void testa9() throws Exception {
            String solution = LLAPSearch.solve(initialState9, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState9);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState9, solution));
        }
        @Test(timeout = 120000)
        public void testa10() throws Exception {
            String solution = LLAPSearch.solve(initialState10, "BF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState10);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState10, solution));
        }


        @Test(timeout = 120000)
        public void testb0() throws Exception {
            String solution = LLAPSearch.solve(initialState0, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState0);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState0, solution));
        }
        @Test(timeout = 120000)
        public void testb1() throws Exception {
            String solution = LLAPSearch.solve(initialState1, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
        }
        @Test(timeout = 120000)
        public void testb2() throws Exception {
            String solution = LLAPSearch.solve(initialState2, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState2);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState2, solution));
        }
        @Test(timeout = 120000)
        public void testb3() throws Exception {
            String solution = LLAPSearch.solve(initialState3, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState3);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState3, solution));
        }
        @Test(timeout = 120000)
        public void testb4() throws Exception {
            String solution = LLAPSearch.solve(initialState4, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState4);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState4, solution));
        }
        @Test(timeout = 120000)
        public void testb5() throws Exception {
            String solution = LLAPSearch.solve(initialState5, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState5);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState5, solution));
        }
        @Test(timeout = 120000)
        public void testb6() throws Exception {
            String solution = LLAPSearch.solve(initialState6, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState6);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState6, solution));
        }
        @Test(timeout = 120000)
        public void testb7() throws Exception {
            String solution = LLAPSearch.solve(initialState7, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState7);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState7, solution));
        }
        @Test(timeout = 120000)
        public void testb8() throws Exception {
            String solution = LLAPSearch.solve(initialState8, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState8);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState8, solution));
        }
        @Test(timeout = 120000)
        public void testb9() throws Exception {
            String solution = LLAPSearch.solve(initialState9, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState9);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState9, solution));
        }
        @Test(timeout = 120000)
        public void testb10() throws Exception {
            String solution = LLAPSearch.solve(initialState10, "DF", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState10);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState10, solution));
        }
        @Test(timeout = 120000)
        public void testc0() throws Exception {
            String solution = LLAPSearch.solve(initialState0, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState0);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState0, solution));
        }
        @Test(timeout = 120000)
        public void testc1() throws Exception {
            String solution = LLAPSearch.solve(initialState1, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
        }
        @Test(timeout = 120000)
        public void testc2() throws Exception {
            String solution = LLAPSearch.solve(initialState2, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState2);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState2, solution));
        }
        @Test(timeout = 120000)
        public void testc3() throws Exception {
            String solution = LLAPSearch.solve(initialState3, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState3);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState3, solution));
        }
        @Test(timeout = 120000)
        public void testc4() throws Exception {
            String solution = LLAPSearch.solve(initialState4, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState4);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState4, solution));
        }
        @Test(timeout = 120000)
        public void testc5() throws Exception {
            String solution = LLAPSearch.solve(initialState5, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState5);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState5, solution));
        }
        @Test(timeout = 120000)
        public void testc6() throws Exception {
            String solution = LLAPSearch.solve(initialState6, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState6);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState6, solution));
        }
        @Test(timeout = 120000)
        public void testc7() throws Exception {
            String solution = LLAPSearch.solve(initialState7, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState7);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState7, solution));
        }
        @Test(timeout = 120000)
        public void testc8() throws Exception {
            String solution = LLAPSearch.solve(initialState8, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState8);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState8, solution));
        }
        @Test(timeout = 120000)
        public void testc9() throws Exception {
            String solution = LLAPSearch.solve(initialState9, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState9);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState9, solution));
        }
        @Test(timeout = 120000)
        public void testc10() throws Exception {
            String solution = LLAPSearch.solve(initialState10, "UC", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState10);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState10, solution));
        }


        @Test(timeout = 120000)
        public void testd0() throws Exception {
            String solution = LLAPSearch.solve(initialState0, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState0);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState0, solution));
        }
        @Test(timeout = 120000)
        public void testd1() throws Exception {
            String solution = LLAPSearch.solve(initialState1, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
        }
        @Test(timeout = 120000)
        public void testd2() throws Exception {
            String solution = LLAPSearch.solve(initialState2, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState2);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState2, solution));
        }
        @Test(timeout = 120000)
        public void testd3() throws Exception {
            String solution = LLAPSearch.solve(initialState3, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState3);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState3, solution));
        }
        @Test(timeout = 120000)
        public void testd4() throws Exception {
            String solution = LLAPSearch.solve(initialState4, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState4);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState4, solution));
        }
        @Test(timeout = 120000)
        public void testd5() throws Exception {
            String solution = LLAPSearch.solve(initialState5, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState5);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState5, solution));
        }
        @Test(timeout = 120000)
        public void testd6() throws Exception {
            String solution = LLAPSearch.solve(initialState6, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState6);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState6, solution));
        }
        @Test(timeout = 120000)
        public void testd7() throws Exception {
            String solution = LLAPSearch.solve(initialState7, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState7);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState7, solution));
        }
        @Test(timeout = 120000)
        public void testd8() throws Exception {
            String solution = LLAPSearch.solve(initialState8, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState8);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState8, solution));
        }
        @Test(timeout = 120000)
        public void testd9() throws Exception {
            String solution = LLAPSearch.solve(initialState9, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState9);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState9, solution));
        }
        @Test(timeout = 120000)
        public void testd10() throws Exception {
            String solution = LLAPSearch.solve(initialState10, "ID", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState10);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState10, solution));
        }

        @Test(timeout = 120000)
        public void teste0() throws Exception {
            String solution = LLAPSearch.solve(initialState0, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState0);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState0, solution));
        }
        @Test(timeout = 120000)
        public void teste1() throws Exception {
            String solution = LLAPSearch.solve(initialState1, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
        }
        @Test(timeout = 120000)
        public void teste2() throws Exception {
            String solution = LLAPSearch.solve(initialState2, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState2);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState2, solution));
        }
        @Test(timeout = 120000)
        public void teste3() throws Exception {
            String solution = LLAPSearch.solve(initialState3, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState3);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState3, solution));
        }
        @Test(timeout = 120000)
        public void teste4() throws Exception {
            String solution = LLAPSearch.solve(initialState4, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState4);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState4, solution));
        }
        @Test(timeout = 120000)
        public void teste5() throws Exception {
            String solution = LLAPSearch.solve(initialState5, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState5);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState5, solution));
        }
        @Test(timeout = 120000)
        public void teste6() throws Exception {
            String solution = LLAPSearch.solve(initialState6, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState6);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState6, solution));
        }
        @Test(timeout = 120000)
        public void teste7() throws Exception {
            String solution = LLAPSearch.solve(initialState7, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState7);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState7, solution));
        }
        @Test(timeout = 120000)
        public void teste8() throws Exception {
            String solution = LLAPSearch.solve(initialState8, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState8);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState8, solution));
        }
        @Test(timeout = 120000)
        public void teste9() throws Exception {
            String solution = LLAPSearch.solve(initialState9, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState9);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState9, solution));
        }
        @Test(timeout = 120000)
        public void teste10() throws Exception {
            String solution = LLAPSearch.solve(initialState10, "GR1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState10);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState10, solution));
        }


        @Test(timeout = 120000)
        public void testf0() throws Exception {
            String solution = LLAPSearch.solve(initialState0, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState0);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState0, solution));
        }
        @Test(timeout = 120000)
        public void testf1() throws Exception {
            String solution = LLAPSearch.solve(initialState1, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
        }
        @Test(timeout = 120000)
        public void testf2() throws Exception {
            String solution = LLAPSearch.solve(initialState2, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState2);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState2, solution));
        }
        @Test(timeout = 120000)
        public void testf3() throws Exception {
            String solution = LLAPSearch.solve(initialState3, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState3);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState3, solution));
        }
        @Test(timeout = 120000)
        public void testf4() throws Exception {
            String solution = LLAPSearch.solve(initialState4, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState4);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState4, solution));
        }
        @Test(timeout = 120000)
        public void testf5() throws Exception {
            String solution = LLAPSearch.solve(initialState5, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState5);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState5, solution));
        }
        @Test(timeout = 120000)
        public void testf6() throws Exception {
            String solution = LLAPSearch.solve(initialState6, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState6);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState6, solution));
        }
        @Test(timeout = 120000)
        public void testf7() throws Exception {
            String solution = LLAPSearch.solve(initialState7, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState7);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState7, solution));
        }
        @Test(timeout = 120000)
        public void testf8() throws Exception {
            String solution = LLAPSearch.solve(initialState8, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState8);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState8, solution));
        }
        @Test(timeout = 120000)
        public void testf9() throws Exception {
            String solution = LLAPSearch.solve(initialState9, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState9);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState9, solution));
        }
        @Test(timeout = 120000)
        public void testf10() throws Exception {
            String solution = LLAPSearch.solve(initialState10, "GR2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState10);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState10, solution));
        }


        @Test(timeout = 120000)
        public void testg0() throws Exception {
            String solution = LLAPSearch.solve(initialState0, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState0);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState0, solution));
        }
        @Test(timeout = 120000)
        public void testg1() throws Exception {
            String solution = LLAPSearch.solve(initialState1, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
        }
        @Test(timeout = 120000)
        public void testg2() throws Exception {
            String solution = LLAPSearch.solve(initialState2, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState2);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState2, solution));
        }
        @Test(timeout = 120000)
        public void testg3() throws Exception {
            String solution = LLAPSearch.solve(initialState3, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState3);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState3, solution));
        }
        @Test(timeout = 120000)
        public void testg4() throws Exception {
            String solution = LLAPSearch.solve(initialState4, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState4);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState4, solution));
        }
        @Test(timeout = 120000)
        public void testg5() throws Exception {
            String solution = LLAPSearch.solve(initialState5, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState5);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState5, solution));
        }
        @Test(timeout = 120000)
        public void testg6() throws Exception {
            String solution = LLAPSearch.solve(initialState6, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState6);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState6, solution));
        }
        @Test(timeout = 120000)
        public void testg7() throws Exception {
            String solution = LLAPSearch.solve(initialState7, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState7);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState7, solution));
        }
        @Test(timeout = 120000)
        public void testg8() throws Exception {
            String solution = LLAPSearch.solve(initialState8, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState8);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState8, solution));
        }
        @Test(timeout = 120000)
        public void testg9() throws Exception {
            String solution = LLAPSearch.solve(initialState9, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState9);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState9, solution));
        }
        @Test(timeout = 120000)
        public void testg10() throws Exception {
            String solution = LLAPSearch.solve(initialState10, "AS1", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState10);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState10, solution));
        }


        @Test(timeout = 120000)
        public void testh0() throws Exception {
            String solution = LLAPSearch.solve(initialState0, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState0);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState0, solution));
        }
        @Test(timeout = 120000)
        public void testh1() throws Exception {
            String solution = LLAPSearch.solve(initialState1, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState1);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState1, solution));
        }
        @Test(timeout = 120000)
        public void testh2() throws Exception {
            String solution = LLAPSearch.solve(initialState2, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState2);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState2, solution));
        }
        @Test(timeout = 120000)
        public void testh3() throws Exception {
            String solution = LLAPSearch.solve(initialState3, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState3);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState3, solution));
        }
        @Test(timeout = 120000)
        public void testh4() throws Exception {
            String solution = LLAPSearch.solve(initialState4, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState4);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState4, solution));
        }
        @Test(timeout = 120000)
        public void testh5() throws Exception {
            String solution = LLAPSearch.solve(initialState5, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState5);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState5, solution));
        }
        @Test(timeout = 120000)
        public void testh6() throws Exception {
            String solution = LLAPSearch.solve(initialState6, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState6);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState6, solution));
        }
        @Test(timeout = 120000)
        public void testh7() throws Exception {
            String solution = LLAPSearch.solve(initialState7, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState7);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState7, solution));
        }
        @Test(timeout = 120000)
        public void testh8() throws Exception {
            String solution = LLAPSearch.solve(initialState8, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState8);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState8, solution));
        }
        @Test(timeout = 120000)
        public void testh9() throws Exception {
            String solution = LLAPSearch.solve(initialState9, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState9);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState9, solution));
        }
        @Test(timeout = 120000)
        public void testh10() throws Exception {
            String solution = LLAPSearch.solve(initialState10, "AS2", false);
            solution = solution.replace(" ", "");
            LLAPPlanChecker pc = new LLAPPlanChecker(initialState10);
            assertTrue("The output actions do not lead to a goal state.", pc.applyPlan(initialState10, solution));
        }
    }

    public static class LLAPPlanChecker {
        int v0;
        int v1;
        int v2;
        int v3;
        HashMap<String, Integer> v4;
        ArrayList<Integer> v5;
        ArrayList<Integer> v6;
        ArrayList<Integer> v7;
        ArrayList<Integer> v8;
        ArrayList<Integer> v9;
        ArrayList<Integer> v10;

        int v11 =100000;
        int v12 =0;

        int v13 =0;
        int v14 =50;

        int v15 = -1;
        public LLAPPlanChecker(String str){

            String[] splitState = str.split(";");

            this.v0 = Integer.parseInt(splitState[0]);

            this.v1 = Integer.parseInt(splitState[1].split(",")[0]);
            this.v2 = Integer.parseInt(splitState[1].split(",")[1]);
            this.v3 = Integer.parseInt(splitState[1].split(",")[2]);

            this.v4 = new HashMap<String, Integer>();
            v4.put("A", Integer.parseInt(splitState[2].split(",")[0]));
            v4.put("B", Integer.parseInt(splitState[2].split(",")[1]));
            v4.put("C", Integer.parseInt(splitState[2].split(",")[2]));


            this.v5 = new ArrayList<Integer>();
            v5.add(0);
            v5.add(1);
            v5.add(1);
            v5.add(1);
            v5.add(0);
            v5.add(Integer.parseInt(splitState[3].split(",")[0]));
            v5.add(Integer.parseInt(splitState[3].split(",")[1]));
            v5.set(0, v5.get(0)+ v5.get(1)* v4.get("A")+ v5.get(2)* v4.get("B")+ v5.get(3)* v4.get("C"));
            this.v6 = new ArrayList<Integer>();
            v6.add(0);
            v6.add(1);
            v6.add(1);
            v6.add(1);
            v6.add(0);
            v6.add(Integer.parseInt(splitState[4].split(",")[0]));
            v6.add(Integer.parseInt(splitState[4].split(",")[1]));
            v6.set(0, v6.get(0)+ v6.get(1)* v4.get("A")+ v6.get(2)* v4.get("B")+ v6.get(3)* v4.get("C"));
            this.v7 = new ArrayList<Integer>();
            v7.add(0);
            v7.add(1);
            v7.add(1);
            v7.add(1);
            v7.add(0);
            v7.add(Integer.parseInt(splitState[5].split(",")[0]));
            v7.add(Integer.parseInt(splitState[5].split(",")[1]));
            v7.set(0, v7.get(0)+ v7.get(1)* v4.get("A")+ v7.get(2)* v4.get("B")+ v7.get(3)* v4.get("C"));
            this.v8 = new ArrayList<Integer>();
            v8.add(0);
            v8.add(1);
            v8.add(1);
            v8.add(1);
            v8.add(0);
            v8.add(0);
            v8.add(0);
            v8.set(0, v8.get(0)+ v8.get(1)* v4.get("A")+ v8.get(2)* v4.get("B")+ v8.get(3)* v4.get("C"));

            this.v9 =  new ArrayList<Integer>();
            this.v10 =  new ArrayList<Integer>();
            for(int i=0;i<5;i++){
                String par1= splitState[6].split(",")[i];
                v9.add(Integer.parseInt(par1));
                String par2= splitState[7].split(",")[i];
                v10.add(Integer.parseInt(par2));
            }
            v9.set(0, v9.get(0)+ v9.get(1)* v4.get("A")+ v9.get(2)* v4.get("B")+ v9.get(3)* v4.get("C"));
            v10.set(0, v10.get(0)+ v10.get(1)* v4.get("A")+ v10.get(2)* v4.get("B")+ v10.get(3)* v4.get("C"));

        }
        public boolean er(String y){
            ArrayList<Integer> x = new ArrayList<>();
            switch (y){
            case "A":
                x = v5;
                break;
            case "B":
                x = v6;
                break;
            case "C":
                x = v7;
                break;
            case "D":
                x = v8;
                break;
            case "E1":
                x = v9;
                break;
            case "E2":
                x = v10;
                break;
            default:
                x = new ArrayList<>();
                break;
            }
            return (this.v1 >= x.get(1) && this.v2 >= x.get(2) && this.v3 >= x.get(3) && this.v11 - this.v12 >= x.get(0));
        }


        public void ur(String y){


            ArrayList<Integer> x = new ArrayList<>();

            switch (y){
                case "A":
                    x = v5;
                    break;
                    case "B":
                    x = v6;
                    break;
                    case "C":
                    x = v7;
                    break;
                case "D":
                    x = v8;
                    break;
                case "E1":
                    x = v9;
                    break;
                case "E2":
                    x = v10;
                    break;
                default:
                    x = new ArrayList<>();
                    break;
            }

            this.v1 -= x.get(1);
            this.v2 -= x.get(2);
            this.v3 -= x.get(3);
            this.v12 += x.get(0);
            this.v0 += x.get(4);
        }

        void au(){
            if (v15 !=-1 && v13 >0){
                v13--;
            }
            if (this.v13 ==0 && this.v15 !=-1){

                if(this.v15 ==0){
                    this.v1 +=this.v5.get(5);
                }
                if(this.v15 ==1){
                    this.v2 +=this.v6.get(5);
                }
                if(this.v15 ==2){
                    this.v3 +=this.v7.get(5);
                }
                this.v15 =-1;
                this.v13 =0;
            }
        }

        void mc(){
            if(v1 > v14){  v1 = v14;  }
            if(v2 > v14){  v2 = v14;  }
            if(v3 > v14){  v3 = v14;  }
        }
        boolean f1(String an){
            au();
            int i=-1;
           if(!er(an)){return false;}
           switch (an){
               case "A":
                   if (this.v11 -this.v12 < this.v5.get(0)){return false;}i=0;
                   v13 = v5.get(6) ;break;
               case "B":
                   if (this.v11 -this.v12 < this.v6.get(0)){return false;}i=1;
                   v13 = v6.get(6) ;break;
               case "C":
                   if (this.v11 -this.v12 < this.v7.get(0)){return false;}i=2;
                   v13 = v7.get(6) ;break;
               default: return false;
           }
            this.v15 =i;
            ur(an);
            mc();
        return true;
        }



        boolean f3(){
            au();
            if(!er("D")){return false;}
            ur("D");
            mc();
            return true;
        }

        boolean f2(int i){
            au();
            String an = "E"+i;
            if(!er(an)){return false;}
            ur(an);
            mc();
            return true;
        }





        public boolean tryPlan(String[] actions, LLAPPlanChecker s) {
            boolean linkin = false;
            for (int i = 0; i < actions.length; i++) {

                switch (actions[i]) {
                    case "requestfood":
                    linkin = s.f1("A");
                    break;
                case "requestenergy":
                    linkin = s.f1("C");
                    break;
                case "requestmaterials":
                    linkin = s.f1("B");
                    break;
                case "build1":
                    linkin = s.f2(1);
                    break;
                case "build2":
                    linkin = s.f2(2);
                    break;
                case "wait":
                    linkin = s.f3();
                    break;
                default: linkin = false;
                break;

                }
                if(!linkin) {
                    System.out.println("action that failed: "+actions[i] +", order: "+i);
                    return false;
                    }
        }
            return true;
        }

        boolean cool(){
           return this.v0 >= 100;
        }
    public boolean applyPlan(String grid, String solution){
        boolean linkin = true;
        solution = solution.toLowerCase();
        if (solution.equals("nosolution")) {
            return false;
        }
    //    System.out.println(solution);
        String[] solutionArray  = solution.split(";");
        String plan = solutionArray[0];
        int blue = Integer.parseInt(solutionArray[1]);
        plan.replace(" ", "");
        plan.replace("\n", "");
        plan.replace("\r", "");
        plan.replace("\n\r", "");
        plan.replace("\t", "");

        String[] actions = plan.split(",");

        LLAPPlanChecker s = new LLAPPlanChecker(grid);
        linkin = tryPlan(actions,s);
        if(!linkin) {
            return false;
            }

        return s.cool() && s.v12 ==blue;
    }
    }
}
