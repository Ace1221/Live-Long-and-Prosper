:-include('KB.pl').

getCurrentFood(Situation,OutputFood):-
    food(InitialFood),
    getCurrentFoodHelper(Situation,InitialFood,OutputFood).

getCurrentFoodHelper(s0, CurrentFood, CurrentFood).

getCurrentFoodHelper(result(Action,Situation),CurrentFood,OutputFood):-
    Action == reqf,
    NewFood is CurrentFood + 1,
    getCurrentFoodHelper(Situation,NewFood,OutputFood).

getCurrentFoodHelper(result(Action,Situation),CurrentFood,OutputFood):-
    Action == b1,
    build1(RequiredFood,_,_),
    NewFood is CurrentFood - RequiredFood,
    getCurrentFoodHelper(Situation,NewFood,OutputFood).

getCurrentFoodHelper(result(Action,Situation),CurrentFood,OutputFood):-
    Action == b2,
    build2(RequiredFood,_,_),
    NewFood is CurrentFood - RequiredFood,
    getCurrentFoodHelper(Situation,NewFood,OutputFood).

getCurrentFoodHelper(result(Action,Situation),CurrentFood,OutputFood):-
    Action \= reqf,
    Action \= b1,
    Action \= b2,
    getCurrentFoodHelper(Situation,CurrentFood,OutputFood).


getCurrentEnergy(Situation,OutputEnergy):-
    energy(InitialEnergy),
    getCurrentEnergyHelper(Situation,InitialEnergy,OutputEnergy).

getCurrentEnergyHelper(s0, CurrentEnergy, CurrentEnergy).

getCurrentEnergyHelper(result(Action,Situation),CurrentEnergy,OutputEnergy):-
    Action == reqe,
    NewEnergy is CurrentEnergy + 1,
    getCurrentEnergyHelper(Situation,NewEnergy,OutputEnergy).

getCurrentEnergyHelper(result(Action,Situation),CurrentEnergy,OutputEnergy):-
    Action == b1,
    build1(_,_,RequiredEnergy),
    NewEnergy is CurrentEnergy - RequiredEnergy,
    getCurrentEnergyHelper(Situation,NewEnergy,OutputEnergy).

getCurrentEnergyHelper(result(Action,Situation),CurrentEnergy,OutputEnergy):-
    Action == b2,
    build2(_,_,RequiredEnergy),
    NewEnergy is CurrentEnergy - RequiredEnergy,
    getCurrentEnergyHelper(Situation,NewEnergy,OutputEnergy).

getCurrentEnergyHelper(result(Action,Situation),CurrentEnergy,OutputEnergy):-
    Action \= reqe,
    Action \= b1,
    Action \= b2,
    getCurrentEnergyHelper(Situation,CurrentEnergy,OutputEnergy).



getCurrentMaterials(Situation,OutputMaterials):-
    materials(InitialMaterials),
    getCurrentMaterialsHelper(Situation,InitialMaterials,OutputMaterials).

getCurrentMaterialsHelper(s0, CurrentMaterials, CurrentMaterials).

getCurrentMaterialsHelper(result(Action,Situation),CurrentMaterials,OutputMaterials):-
    Action == reqm,
    NewMaterials is CurrentMaterials + 1,
    getCurrentMaterialsHelper(Situation,NewMaterials,OutputMaterials).

getCurrentMaterialsHelper(result(Action,Situation),CurrentMaterials,OutputMaterials):-
    Action == b1,
    build1(_,RequiredMaterials,_),
    NewMaterials is CurrentMaterials - RequiredMaterials,
    getCurrentMaterialsHelper(Situation,NewMaterials,OutputMaterials).

getCurrentMaterialsHelper(result(Action,Situation),CurrentMaterials,OutputMaterials):-
    Action == b2,
    build2(_,RequiredMaterials,_),
    NewMaterials is CurrentMaterials - RequiredMaterials,
    getCurrentMaterialsHelper(Situation,NewMaterials,OutputMaterials).

getCurrentMaterialsHelper(result(Action,Situation),CurrentMaterials,OutputMaterials):-
    Action \= reqm,
    Action \= b1,
    Action \= b2,
    getCurrentMaterialsHelper(Situation,CurrentMaterials,OutputMaterials).


max(X, Y, X) :- X >= Y, !.
max(X, Y, Y) :- X < Y.

getCurrentBuild1Count(Situation,OutputBuild1Count):-
    getCurrentBuild1CountHelper(Situation,0,OutputBuild1Count).

getCurrentBuild1CountHelper(s0, CurrentBuild1Count, CurrentBuild1Count).

getCurrentBuild1CountHelper(result(Action,Situation),CurrentBuild1Count,OutputBuild1Count):-
    Action == b1,
    NewBuild1Count is CurrentBuild1Count + 1,
    getCurrentBuild1CountHelper(Situation,NewBuild1Count,OutputBuild1Count).

getCurrentBuild1CountHelper(result(Action,Situation),CurrentBuild1Count,OutputBuild1Count):-
    Action \= b1,
    getCurrentBuild1CountHelper(Situation,CurrentBuild1Count,OutputBuild1Count).

getCurrentBuild2Count(Situation,OutputBuild2Count):-
    getCurrentBuild2CountHelper(Situation,0,OutputBuild2Count).

getCurrentBuild2CountHelper(s0, CurrentBuild2Count, CurrentBuild2Count).

getCurrentBuild2CountHelper(result(Action,Situation),CurrentBuild2Count,OutputBuild2Count):-
    Action == b2,
    NewBuild2Count is CurrentBuild2Count + 1,
    getCurrentBuild2CountHelper(Situation,NewBuild2Count,OutputBuild2Count).

getCurrentBuild2CountHelper(result(Action,Situation),CurrentBuild2Count,OutputBuild2Count):-
    Action \= b2,
    getCurrentBuild2CountHelper(Situation,CurrentBuild2Count,OutputBuild2Count).


applyAction(Action, Situation, result(Action,Situation)):-
    getCurrentFood(Situation,CurrentFood),
    getCurrentMaterials(Situation,CurrentMaterials),
    getCurrentEnergy(Situation,CurrentEnergy),
    getCurrentBuild1Count(Situation,CurrentBuild1Count),
    CurrentBuild1Count == 0,
    build1(RequiredFood,RequiredMaterials,RequiredEnergy),
    CurrentFood >= RequiredFood,
    CurrentMaterials >= RequiredMaterials,
    CurrentEnergy >= RequiredEnergy,
    Action = b1.

applyAction(Action, Situation, result(Action,Situation)):-
    getCurrentFood(Situation,CurrentFood),
    getCurrentMaterials(Situation,CurrentMaterials),
    getCurrentEnergy(Situation,CurrentEnergy),
    getCurrentBuild2Count(Situation,CurrentBuild2Count),
    CurrentBuild2Count == 0,
    build2(RequiredFood,RequiredMaterials,RequiredEnergy),
    CurrentFood >= RequiredFood,
    CurrentMaterials >= RequiredMaterials,
    CurrentEnergy >= RequiredEnergy,
    Action = b2.

applyAction(Action, Situation, result(Action,Situation)):-
    Action = reqf,
    getCurrentFood(Situation,CurrentFood),
    build1(RequiredFood1,_,_),
    build2(RequiredFood2,_,_),
    max(RequiredFood1,RequiredFood2,RequiredFood),
    CurrentFood < RequiredFood.
    

applyAction(Action, Situation, result(Action,Situation)):-
    Action = reqm,
    getCurrentMaterials(Situation,CurrentMaterials),
    build1(_,RequiredMaterials1,_),
    build2(_,RequiredMaterials2,_),
    max(RequiredMaterials1,RequiredMaterials2,RequiredMaterials),
    CurrentMaterials < RequiredMaterials.

applyAction(Action, Situation, result(Action,Situation)):-
    Action = reqe,
    getCurrentEnergy(Situation,CurrentEnergy),
    build1(_,_,RequiredEnergy1),
    build2(_,_,RequiredEnergy2),
    max(RequiredEnergy1,RequiredEnergy2,RequiredEnergy),
    CurrentEnergy < RequiredEnergy.

goalState(Situation):-
    goalStateHelper(Situation, 0,0).

goalStateHelper(s0, Build1Counter, Build2Counter):- 
    Build1Counter>0, 
    Build2Counter>0.
goalStateHelper(Situation, Build1Counter, Build2Counter):-
    Situation = result(b1, newSituation),
    NewBuild1Counter is Build1Counter + 1,
    goalStateHelper(newSituation, NewBuild1Counter, Build2Counter).
goalStateHelper(Situation, Build1Counter, Build2Counter):-
    Situation = result(b2, newSituation),
    NewBuild2Counter is Build2Counter + 1,
    goalStateHelper(newSituation, Build1Counter, NewBuild2Counter).
goalStateHelper(Situation, Build1Counter, Build2Counter):-
    Situation = result(Action, newSituation),
    Action \= b1,
    Action \= b2,
    goalStateHelper(newSituation, Build1Counter, Build2Counter).

goal(Situation):-
    ids(Situation,1).

ids(Situation,Length):-
    (call_with_depth_limit(goalHelper(Situation),Length,R), number(R));
    (call_with_depth_limit(goalHelper(Situation),Length,R), R=depth_limit_exceeded,
    Length1 is Length+1, ids(Situation,Length1)).

goalHelper(Situation):-
    goalState(Situation).

goalHelper(Situation):-
    applyAction(Action, Situation, ResultingSituation),
    goalHelper(ResultingSituation).