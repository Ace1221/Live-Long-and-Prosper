:-include('KB.pl').

goal(Situation):-
    ids(Situation,20).

ids(Situation,Length):-
    (call_with_depth_limit(goalHelper(Situation),Length,R), number(R));
    (call_with_depth_limit(goalHelper(Situation),Length,R), R=depth_limit_exceeded,
    Length1 is Length+1, ids(Situation,Length1)).

goalHelper(Situation):-
    goalLogic(_,BuildStatus,Situation),
    BuildStatus = build(Build1Counter,Build2Counter),
    Build1Counter > 0,
    Build2Counter > 0.

goalLogic(InitialResources,InitialBuildStatus,s0):-
    food(InitialFood),
    materials(InitialMaterials),
    energy(InitialEnergy),
    InitialResources = resource(InitialFood,InitialMaterials,InitialEnergy),
    InitialBuildStatus = build(0,0).

goalLogic(Resources,build(Build1Counter,Build2Counter),result(b1,Situation)):-
    goalLogic(PreviousResources,BuildStatus,Situation),
    build(PreviousBuild1Counter,Build2Counter) = BuildStatus,
    resource(PreviousFood,PreviousMaterials,PreviousEnergy) = PreviousResources,
    build1(RequiredBuild1Food,RequiredBuild1Materials,RequiredBuild1Energy),
    RequiredBuild1Food =< PreviousFood,
    RequiredBuild1Materials =< PreviousMaterials,
    RequiredBuild1Energy =< PreviousEnergy,
    Build1Counter is PreviousBuild1Counter + 1,
    ResultingFood is PreviousFood - RequiredBuild1Food,
    ResultingMaterials is PreviousMaterials - RequiredBuild1Materials,
    ResultingEnergy is PreviousEnergy - RequiredBuild1Energy,
    Resources = resource(ResultingFood, ResultingMaterials,ResultingEnergy).

goalLogic(Resources,build(Build1Counter,Build2Counter),result(b2,Situation)):-
    goalLogic(PreviousResources,BuildStatus,Situation),
    build(Build1Counter,PreviousBuild2Counter) = BuildStatus,
    resource(PreviousFood,PreviousMaterials,PreviousEnergy) = PreviousResources,
    build2(RequiredBuild2Food,RequiredBuild2Materials,RequiredBuild2Energy),
    RequiredBuild2Food =< PreviousFood,
    RequiredBuild2Materials =< PreviousMaterials,
    RequiredBuild2Energy =< PreviousEnergy,
    Build2Counter is PreviousBuild2Counter + 1,
    ResultingFood is PreviousFood - RequiredBuild2Food,
    ResultingMaterials is PreviousMaterials - RequiredBuild2Materials,
    ResultingEnergy is PreviousEnergy - RequiredBuild2Energy,
    Resources = resource(ResultingFood, ResultingMaterials,ResultingEnergy).

goalLogic(Resources,BuildStatus,result(reqf,Situation)):-
    goalLogic(PreviousResources,BuildStatus,Situation),
    resource(PreviousFood,PreviousMaterials,PreviousEnergy) = PreviousResources,
    ResultingFood is PreviousFood + 1,
    Resources = resource(ResultingFood, PreviousMaterials,PreviousEnergy).

goalLogic(Resources,BuildStatus,result(reqm,Situation)):-
    goalLogic(PreviousResources,BuildStatus,Situation),
    resource(PreviousFood,PreviousMaterials,PreviousEnergy) = PreviousResources,
    ResultingMaterials is PreviousMaterials + 1,
    Resources = resource(PreviousFood, ResultingMaterials ,PreviousEnergy).

goalLogic(Resources,BuildStatus,result(reqe,Situation)):-
    goalLogic(PreviousResources,BuildStatus,Situation),
    resource(PreviousFood,PreviousMaterials,PreviousEnergy) = PreviousResources,
    ResultingEnergy is PreviousEnergy + 1,
    Resources = resource(PreviousFood, PreviousMaterials,ResultingEnergy).

