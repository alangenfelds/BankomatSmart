package com.javarush.test.level26.BankomatSmart;

import com.javarush.test.level24.lesson04.home01.HasHeight;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by alangenfelds on 16.03.2016.
 */
public class BankomatSmart
{
    public static void main(String[] args)
    {
        Map<Integer, Integer> denominations=new HashMap<Integer,Integer>();
        TreeMap<Integer, Integer> sortedDenominations = new TreeMap<Integer,Integer>();

        //denominations.put(100,2);
        denominations.put(50,10);
        denominations.put(20,10);
        //denominations.put(10,20);

        int withdrawAmount = 600;


        sortedDenominations.putAll(denominations);      // skladivaem nominali v otsortirovannij map
        System.out.println("Nominali v bankomate pered snjatiem: "+sortedDenominations.descendingMap());

        Map<Integer,Integer> resultMap = new HashMap<Integer,Integer>();    // sozdaem map dla vidachi kupjur


            for (Integer i : sortedDenominations.descendingKeySet()) //from max to min
            {
                int nominal = i;
                if (nominal <= withdrawAmount)  //esli nominal tekusej banknoti < zaprosennoj summi - znachit on podhodit i mozno s nim rabotatj
                {
                    int count = withdrawAmount / nominal; // opredelili skolko kupjur tekusego nominala neobhodimo vidatj
                    while (count > denominations.get(i))  //esli kolvo neobhodimih kupjur bolse, cem estj v bankomate, to umenjshaem
                    {
                        count--;
                    }
                    withdrawAmount = withdrawAmount - nominal * count; // vichitaem snjatuju summu
                    resultMap.put(nominal, count); //zanosim snjatuju summu v rezult map

                    if (withdrawAmount == 0) break; //esli vsja neobhodimaja summa snjalas - zakanchivaem cikl
                }
            } //end for sortedDenominations

            if (withdrawAmount > 0)
            {
                System.out.println("Ne mozem vidatj etu summu!!!"); //esli posle cikla ne udalosj sobratj nuzhnuju summu

            }
            else  //esli smogli vidatj summu, to
            {
                // teperj otnimaem vidannoe kol-vo banknot iz ishodnogo map'a
                HashMap<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
                for (Map.Entry<Integer, Integer> entry : denominations.entrySet())  //kopiruem v novij map tekushuju situaciju
                {
                    Integer key = entry.getKey();
                    Integer value = entry.getValue();

                    if (resultMap.containsKey(key))  //esli v vidannih nominalah estj tekushij iz map(denominations)
                    {
                        if (value - resultMap.get(key) != 0)
                            tempMap.put(key, value - resultMap.get(key));  //esli posle vichitanija kolicestva snjatih kupjur ih ne stalo 0
                        // to dobavljaem novoe kolicestvo v tempMap
                    }
                    else tempMap.put(key,value); //esli net, to prosto zapisivaem to cto bilo
                } //end for
                denominations=tempMap;
            }
        System.out.println("Nominali v bankomate posle snjatija: "+denominations);



    } //end of main
}

