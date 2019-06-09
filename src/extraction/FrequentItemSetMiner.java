package extraction;

import java.util.*;

public class FrequentItemSetMiner {

    private BooleanDataBase bdb;
    public HashMap<String, String> dicoFreq;
    public ArrayList<String> clesFreq;
    public ArrayList<ArrayList<String>> motifs;
    ArrayList<ArrayList<String>> motifs2;
    public int occurenceA;
    public int occurenceB;
    public int occurenceC;
    public int occurenceD;
    public int occurenceE;
    public HashMap<String, Integer> itemTaille1;

    // Constructeur de la classe
    public FrequentItemSetMiner(BooleanDataBase bDatabase) {
        this.bdb = bDatabase;
    }

    public BooleanDataBase getBdb() {
        return bdb;
    }

    public void setBdb(BooleanDataBase bdb) {
        this.bdb = bdb;
    }

    /**
     * Fonction qui va retourner tous les combinaisons possibles d'un Ensemble
     */
    public ArrayList<ArrayList<String>> getSousEnsemble(String[] set, int index) {
        ArrayList<ArrayList<String>> allSousEnsemble = new ArrayList<ArrayList<String>>();

        if (index < 0) {
            allSousEnsemble.add(new ArrayList<String>());
        } else {
            allSousEnsemble = getSousEnsemble(set, index - 1);
            ///System.out.println("allSousEnsemble : " + allSousEnsemble);
            String item = set[index];
            ArrayList<ArrayList<String>> moreSousEnsemble = new ArrayList<ArrayList<String>>();
            for (ArrayList<String> subset : allSousEnsemble) {
                ArrayList<String> nouvelEnsemble = new ArrayList<String>();
                nouvelEnsemble.addAll(subset);
                if (!nouvelEnsemble.contains(item) && !moreSousEnsemble.contains(nouvelEnsemble)) {
                    nouvelEnsemble.add(item);
                    // System.out.println("nouvelEnsemble : " + nouvelEnsemble);
                    moreSousEnsemble.add(nouvelEnsemble);
                }

            }
            if (!allSousEnsemble.contains(moreSousEnsemble)) {
                allSousEnsemble.addAll(moreSousEnsemble);
            }

        }

        return allSousEnsemble;
    }

    /**
     * Méthode qui compte les occurences des éléments de lst qui se trouvent
     * dans souslist
     */
    public Integer compteOccurences(List<ArrayList<String>> lst, ArrayList<String> souslist) {
        int compteur = 0;
        for (ArrayList al : lst) {
            if (al.equals(souslist)) {
                compteur++;
            }
        }
        return compteur;
    }

    public HashMap<String, Integer> frequentItemSets(int minfr) {
        dicoFreq = new HashMap<String, String>();
        clesFreq = new ArrayList<String>();
        itemTaille1 = new HashMap<String, Integer>();
        motifs2 = new ArrayList<ArrayList<String>>();
        HashMap<String, String> dicoCles;
        dicoCles = new HashMap<String, String>();

        // clesFreq de toutes tailles
        ArrayList<String> clesFreqT;
        clesFreqT = new ArrayList<String>();
        ArrayList<String> clesComb;
        clesComb = new ArrayList<String>();
        occurenceA = 0;
        occurenceB = 0;
        occurenceC = 0;
        occurenceD = 0;
        occurenceE = 0;
        //  System.out.println(bdb.getListeTransactions().entrySet());
        String cles;
        // On compte le nombre d'items dans la transcations
        int nb_AB = 0;
        int nb_AC = 0;
        int nb_AD = 0;
        int nb_AE = 0;
        int nb_BC = 0;
        int nb_BD = 0;
        int nb_BE = 0;
        int nb_CD = 0;
        int nb_CE = 0;
        int nb_DE = 0;
        int nb_ABC = 0;
        int nb_ABD = 0;
        int nb_ABE = 0;
        int nb_BCD = 0;
        int nb_BCE = 0;
        int nb_CDE = 0;

        // HashMap de doublons
        HashMap<String, Integer> doublon;
        doublon = new HashMap<String, Integer>();
        // HashMap items de taille3s
        HashMap<String, Integer> taille3;
        taille3 = new HashMap<String, Integer>();
        // Hashmap qui contient toures kes items fréquents
        HashMap<String, Integer> itemFreq = new HashMap<String, Integer>();

        // Parcours transcations
        for (Map.Entry<String, HashMap<String, String>> entry : bdb.getListeTransactions().entrySet()) {
            cles = entry.getKey();

            ArrayList<ArrayList<String>> motifsClesFreq;
            for (Map.Entry<String, String> dico : entry.getValue().entrySet()) {
                if (dico.getValue().equals("1")) {
                    // On récupère toutes les clés qui sont à un
                    clesFreq.add(dico.getKey());
                    for (String item : clesFreq) {
                        // On compte les occcurences de chaque item dans clesFreq
                        occurenceA = Collections.frequency(clesFreq, "A");
                        occurenceB = Collections.frequency(clesFreq, "B");
                        occurenceC = Collections.frequency(clesFreq, "C");
                        occurenceD = Collections.frequency(clesFreq, "D");
                        occurenceE = Collections.frequency(clesFreq, "E");

                    }
                }
            }
            //System.out.println(" Transaction :  " +entry.getValue());
            for (Map.Entry<String, String> dico : entry.getValue().entrySet()) {
                if (dico.getValue().equals("1")) {
                    if (!clesFreqT.contains(dico.getKey())) {
                        clesFreqT.add(dico.getKey());

                    }
                }
            }
            // On fait le parcours pour les éléments par paire
            for (Map.Entry<String, String> dico : entry.getValue().entrySet()) {
                if (dico.getValue().equals("1")) {
                    //System.out.println("dicocles" + dico.getKey());
                    if (dico.getKey().equals("A")) {
                        for (Map.Entry<String, String> dico2 : entry.getValue().entrySet()) {
                            if (dico2.getValue().equals("1")) {
                                if (dico2.getKey().equals("B")) {

                                    nb_AB += 1;
                                    //System.out.println("nb_AB" + nb_AB);
                                    doublon.put("AB", nb_AB);

                                } else if (dico2.getKey().equals("C")) {
                                    nb_AC += 1;
                                    //  System.out.println("nb_AB" + nb_AC);
                                    doublon.put("AC", nb_AC);
                                } else if (dico2.getKey().equals("D")) {
                                    nb_AD += 1;
                                    //System.out.println("nb_AB" + nb_AD);
                                    doublon.put("AD", nb_AD);
                                } else if (dico2.getKey().equals("E")) {
                                    nb_AE += 1;
                                    //  System.out.println("nb_AB" + nb_AE);
                                    doublon.put("AE", nb_AE);

                                }
                            }
                        }
                    } // fin A
                    else if (dico.getKey().equals("B")) {
                        for (Map.Entry<String, String> dico2 : entry.getValue().entrySet()) {
                            if (dico2.getValue().equals("1")) {
                                if (dico2.getKey().equals("E")) {
                                    nb_BE += 1;
                                    //  System.out.println("nb_BE" + nb_BE);
                                    doublon.put("BE", nb_BE);
                                } else if (dico2.getKey().equals("C")) {
                                    nb_BC += 1;
                                    //  System.out.println("nb_BC" + nb_BC);
                                    doublon.put("BC", nb_BC);
                                } else if (dico2.getKey().equals("D")) {
                                    nb_BD += 1;
                                    //  System.out.println("nb_BB" + nb_BD);
                                    doublon.put("BD", nb_BD);
                                }
                            }
                        }
                    }// fin B
                    else if (dico.getKey().equals("C")) {
                        for (Map.Entry<String, String> dico2 : entry.getValue().entrySet()) {
                            if (dico2.getValue().equals("1")) {
                                if (dico2.getKey().equals("E")) {
                                    nb_CE += 1;
                                    //System.out.println("nb_CE" + nb_CE);
                                    doublon.put("CE", nb_CE);
                                } else if (dico2.getKey().equals("D")) {
                                    nb_CD += 1;
                                    //System.out.println("nb_CD" + nb_CD);
                                    doublon.put("CD", nb_CD);
                                }
                            }
                        }
                    } // fin C
                    else if (dico.getKey().equals("D")) {
                        for (Map.Entry<String, String> dico2 : entry.getValue().entrySet()) {
                            if (dico2.getValue().equals("1")) {
                                if (dico2.getKey().equals("E")) {
                                    nb_DE += 1;
                                    //  System.out.println("nb_DE" + nb_DE);
                                    doublon.put("DE", nb_DE);
                                }
                            }

                        }
                    }//fin D

                }// fin grand if

            }

        }
        itemTaille1.put("A", occurenceA);
        itemTaille1.put("B", occurenceB);
        itemTaille1.put("C", occurenceC);
        itemTaille1.put("D", occurenceD);
        itemTaille1.put("E", occurenceE);
        itemFreq.putAll(itemTaille1);
        itemFreq.putAll(doublon);

        // ItemFreq dico
        //  return itemFreq;
        Set<String> clesEns = new HashSet<String>();
        for (Map.Entry<String, Integer> entry : itemFreq.entrySet()) {
            String key = entry.getKey();
            int valeur = entry.getValue();
            // On supprime toutes clés qui ont
            // une valeur < minfr
            if (valeur < minfr) {
                clesEns.add(key);
            }

        }
        itemFreq.keySet().removeAll(clesEns);
        return itemFreq;

    }

    public String toString() {
        return "BooleanDataBase : " + this.getBdb();
    }


}
