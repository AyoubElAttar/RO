package com.alexscode.teaching.tap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstSolution implements TAPSolver {

    @Override
    public List<Integer> solve(Instance ist) {
        List<Integer> demo = new ArrayList<>();
        Objectives obj = new Objectives(ist);

        //cree une liste d'interets et les trier de maniere decroissant
        List<Double> ResultatInteret = new ArrayList<>();
        for (int i = 0; i < ist.getSize(); i++) {
            ResultatInteret.add(ist.getInterest()[i]);
        }
        Collections.sort(ResultatInteret, Collections.reverseOrder());

        //selectionne les requetes en fonction de l'interet trie
        for (double interest : ResultatInteret) {
            for (int q_idx = 0; q_idx < ist.getSize(); q_idx++) {

                // verifier si la requete a cet interet et n'est pas déjà dans la liste principale
                if (ist.getInterest()[q_idx] == interest && !demo.contains(q_idx)) {
                    List<Integer> DemoTemporaire = new ArrayList<>(demo);
                    DemoTemporaire.add(q_idx);

                    //verifie les contraintes de distance et de temps avec une liste temporaire
                    if (obj.distance(DemoTemporaire) <= ist.getMaxDistance() && obj.time(DemoTemporaire) <= ist.getTimeBudget()) {
                        demo.add(q_idx);
                        break;
                    }
                }
            }
        }

        return demo;
    }
}
