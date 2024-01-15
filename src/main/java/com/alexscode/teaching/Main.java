package com.alexscode.teaching;

import com.alexscode.teaching.tap.*;

import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Instance A1 = Instance.readFile("./instances/f4_tap_0_20.dat", 330, 27);
        Instance A2 = Instance.readFile("./instances/f4_tap_1_400.dat", 6600, 540);
        Instance A3 = Instance.readFile("./instances/f4_tap_4_400.dat", 6600, 540);
        Instance A4 = Instance.readFile("./instances/f1_tap_3_400.dat", 6600, 540);
        Instance A5 = Instance.readFile("./instances/f1_tap_9_400.dat", 6600, 540);
        Instance A6 = Instance.readFile("./instances/tap_11_250.dat", 1200, 250);
        Instance A7 = Instance.readFile("./instances/tap_14_400.dat", 6600, 540);
        Instance A8 = Instance.readFile("./instances/tap_10_100.dat", 1200, 150);
        Instance A9 = Instance.readFile("./instances/tap_13_150.dat", 1200, 150);
        Instance A10 = Instance.readFile("./instances/tap_15_60.dat", 330, 27);

        // A1 A2 A3 A4 A5 A6 A7 A8 A9 A10
        Objectives obj = new Objectives(A1);

        // SecondSolution  FirstSolution  TestNaif
        TAPSolver solver = new TestNaif();
        List<Integer> solution = solver.solve(A1);

        System.out.println("Interet: " + obj.interest(solution));
        System.out.println("Temps: " + obj.time(solution));
        System.out.println("Distance: " + obj.distance(solution));
        System.out.println("Feasible ? " + isSolutionFeasible(A1, solution));
    }
    public static boolean isSolutionFeasible(Instance ist, List<Integer> sol){
        Objectives obj = new Objectives(ist);
        return obj.time(sol) <= ist.getTimeBudget() && obj.distance(sol) <= ist.getMaxDistance() && sol.size() == (new TreeSet<>(sol)).size();
    }
}