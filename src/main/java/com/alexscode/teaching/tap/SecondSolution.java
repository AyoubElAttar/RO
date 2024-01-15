package com.alexscode.teaching.tap;
import java.util.ArrayList;
import java.util.List;

public class SecondSolution implements TAPSolver {

    @Override
    public List<Integer> solve(Instance ist) {
        List<Integer> demo = new ArrayList<>();
        Objectives obj = new Objectives(ist);

        int q_idx = 0;

        // ajout de la première requete qui maximise l'interet
        demo.add(q_idx);

        //Ajout des requete qui maximisent l'interet tout en respectant les contraintes
        while (obj.distance(demo) < ist.getMaxDistance() && obj.time(demo) < ist.getTimeBudget()) {
            q_idx = FindRequeteProchain(q_idx, demo, ist);
            if (q_idx != -1) {
                demo.add(q_idx);
            }
            // Aucune requete supplementaire ne peut etre ajoutee sans violer les contrainte
            else {
                break;
            }
        }

        //supprime la derniere requete si elle viole les contrainte
        if (!demo.isEmpty()) {
            demo.remove(demo.size() - 1);
        }
        return demo;
    }

    //methode pour trouver la requete optimale suivante à ajouter
    private int FindRequeteProchain(int ReqAct, List<Integer> demo, Instance ist) {
        int ReqPro = -1;
        double EstimationMax = 0.0;

        // parcouri toutes les requetes possibles
        for (int i = 0; i < ist.getSize(); i++) {
            if (!demo.contains(i)) {
                double EstimatiionInitial = Estimation(ReqAct, i, ist);
                if (EstimatiionInitial > EstimationMax) {
                    EstimationMax = EstimatiionInitial;
                    ReqPro = i;
                }
            }
        }
        return ReqPro;
    }


    //calcule la relation entre l'interet de la requete et le résultat de la multiplication de la distance par le temps
    private double Estimation(int ReqAct, int ReqNou, Instance ist) {
        double distance = ist.getDistances()[ReqAct][ReqNou];
        // evite la division par 0
        if (distance == 0) return 0;
        return ist.getInterest()[ReqNou] / (distance * ist.getTimeBudget());
    }

}
