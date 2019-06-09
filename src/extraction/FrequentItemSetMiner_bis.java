package extraction;

import java.util.*;

class FrequentItemSetMiner_bis {
    private BooleanDataBase bdb;
    protected HashMap<String,Integer> itemFreq;
    // item de taille 1
    protected HashMap<String,Integer> singleton;
    protected ArrayList<String> listeSingleton;
    protected Set<String> setSingleton;
    protected int nbA;
    protected int nbB;
    protected int nbC;
    protected int nbD;
    protected int nbE;
    //


    // Constructeur de la classe
    public FrequentItemSetMiner_bis(BooleanDataBase bDatabase){
        this.bdb=bDatabase;
    }

    public BooleanDataBase getBdb() {
      return bdb;
    }
    public void setBdb(BooleanDataBase bdb) {
      this.bdb = bdb;
    }

    @Override
    public String toString() {
      return "BooleanDataBase : " + this.getBdb();
    }

    public HashMap<String,Integer> frequentItemSets(int minfr) {
      String cles;
      HashMap<String,String> valeurs;
      valeurs = new HashMap<String,String>();
      listeSingleton = new ArrayList<String>();
      setSingleton = new HashSet<String>();
      singleton = new HashMap<String,Integer>();
      ArrayList<Integer> valeurSingleton;
      valeurSingleton = new ArrayList<Integer>();
      Set<String> cleSingleton =  new HashSet<String>();
      ArrayList<String> clesTransactions = new ArrayList<String>();
      ArrayList<ArrayList<String>> tabClesFreqComb = new ArrayList<ArrayList<String>>();


      // HahsMap contenant toutes les combinaisons
    //  HashMap<ArrayList<String>,Integer> HashMapFrequent = new HashMap<ArrayList<String>,Integer> ();
     HashMap<String,Integer> HashMapFrequent = new HashMap<String,Integer>();
      nbA=0;
      nbB=0;
      nbC=0;
      nbD=0;
      nbE=0;

      for (Map.Entry<String,HashMap<String,String>> entry: bdb.getListeTransactions().entrySet()){
        cles = entry.getKey();
        valeurs = entry.getValue();

        System.out.println("HashMap = " +  entry.getValue());
        for(Map.Entry<String,String> hashmap : valeurs.entrySet()){
        //  System.out.println("clesTransactions = " + hashmap.getKey());

            if (hashmap.getValue().equals("1")){
              //System.out.println("cle HashMap à 1 = " + hashmap.getKey());
              // liste des singletons
              clesTransactions.add(hashmap.getKey());
              listeSingleton.add(hashmap.getKey());
              nbA= CompteSingleTon(listeSingleton,"A");
              nbB= CompteSingleTon(listeSingleton,"B");
              nbC= CompteSingleTon(listeSingleton,"C");
              nbD= CompteSingleTon(listeSingleton,"D");
              nbE= CompteSingleTon(listeSingleton,"E");
              setSingleton.addAll(listeSingleton);
              for (String item : setSingleton){
                //System.out.println("item = "+ item);
                singleton.put(item,0);
                switch(item){
                  case "A":
                    singleton.put(item,nbA);
                    break;
                  case "B":
                    singleton.put(item,nbB);
                    break;
                  case "C":
                    singleton.put(item,nbC);
                    break;
                  case "D":
                    singleton.put(item,nbD);
                    break;
                  case "E":
                    singleton.put(item,nbE);
                    break;
                }
              }

            }
        }
      System.out.println("clesTransactions = " + clesTransactions);
      String[] tabClesFreq = new String[clesTransactions.size()];
      tabClesFreq = clesTransactions.toArray(tabClesFreq);
      tabClesFreqComb = getSousEnsemble(tabClesFreq,clesTransactions.size()-1);
      tabClesFreqComb.remove(tabClesFreqComb.remove(tabClesFreqComb.get(0)));

      }
      System.out.println("HashMapSingleton : " + singleton);
      for (Map.Entry<String,Integer> mapSingleton : singleton.entrySet()){
        int valeur =mapSingleton.getValue();
        String cle = mapSingleton.getKey();
      // élagage par rapport aux singleton
      if (valeur < minfr){
        cleSingleton.add(cle);
      }

      }

     singleton.keySet().removeAll(cleSingleton);
     System.out.println("minfr= " + minfr);
      return singleton;
    }

    /* Focntion qui compte le nombre d'occurences dans un Singleton */
    public int CompteSingleTon(ArrayList<String> liste,String item){
      int occItem=0;
      for(String element : liste){
        occItem = Collections.frequency(liste,item);
      }
      return occItem;
    }

    // Fonction qui génère toutes les combinaisons possibles d'un ensemble
    public ArrayList<ArrayList<String>> getSousEnsemble(String[] set, int index) {
       ArrayList<ArrayList<String>> allSousEnsemble= new ArrayList<ArrayList<String>>();

            if (index < 0) {
                allSousEnsemble.add(new ArrayList<String>());
            }
            else {
                allSousEnsemble = getSousEnsemble(set, index - 1);
                ///System.out.println("allSousEnsemble : " + allSousEnsemble);
                String item = set[index];
                ArrayList<ArrayList<String>> moreSousEnsemble= new ArrayList<ArrayList<String> >();
                for (ArrayList<String> subset : allSousEnsemble) {
                    ArrayList<String> nouvelEnsemble = new ArrayList<String>();
                    nouvelEnsemble.addAll(subset);
                  if (!nouvelEnsemble.contains(item) && ! moreSousEnsemble.contains(nouvelEnsemble)){
                    nouvelEnsemble.add(item);
                   // System.out.println("nouvelEnsemble : " + nouvelEnsemble);
                    moreSousEnsemble.add(nouvelEnsemble);
                  }

                }
                if (!allSousEnsemble.contains(moreSousEnsemble)){
                     allSousEnsemble.addAll(moreSousEnsemble);
                }

            }

            return allSousEnsemble;
    }


}
