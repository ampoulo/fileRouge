package extraction;

import java.util.*;


public class Test {

public static void main(String[] args) {

  // variables
  Set<String> variables = new HashSet<String>();
  variables.add("A");
  variables.add("B");
  variables.add("C");
  variables.add("D");
  variables.add("E");
  // Transactions
  HashMap<String,String> t1 = new HashMap<String,String>();
  t1.put("A","1");
  t1.put("B","1");
  t1.put("C","1");
  t1.put("D","1");
  t1.put("E","1");
  HashMap<String,String> t2 = new HashMap<String,String>();
  t2.put("A","1");
  t2.put("B","0");
  t2.put("C","1");
  t2.put("D","0");
  t2.put("E","0");
  HashMap<String,String> t3 = new HashMap<String,String>();
  t3.put("A","1");
  t3.put("B","1");
  t3.put("C","1");
  t3.put("D","1");
  t3.put("E","0");
  HashMap<String,String> t4 = new HashMap<String,String>();
  t4.put("A","0");
  t4.put("B","1");
  t4.put("C","1");
  t4.put("D","0");
  t4.put("E","0");
  HashMap<String,String> t5 = new HashMap<String,String>();
  t5.put("A","1");
  t5.put("B","1");
  t5.put("C","1");
  t5.put("D","0");
  t5.put("E","0");
  HashMap<String,HashMap<String,String>> listeTransactions = new HashMap <String,HashMap<String,String>>();
  listeTransactions.put("1",t1);
  listeTransactions.put("2",t2);
  listeTransactions.put("3",t3);
  listeTransactions.put("4",t4);
  listeTransactions.put("5",t5);
  // System.out.println("listeTransactions = " +listeTransactions);
  BooleanDataBase b = new BooleanDataBase(variables,listeTransactions);
  FrequentItemSetMiner_bis fbis = new FrequentItemSetMiner_bis(b);
  /*System.out.println(fbis.frequentItemSets(4));
  System.out.println(fbis.frequentItemSets((5)));
  System.out.println(fbis.frequentItemSets((2)));*/

  FrequentItemSetMiner f = new FrequentItemSetMiner(b);
  System.out.println(f.frequentItemSets(4));
  System.out.println(f.frequentItemSets((5)));
  System.out.println(f.frequentItemSets((2)));


}


}
