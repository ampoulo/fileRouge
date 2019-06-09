/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning.test;
import example.*;
/*import static planning.example.AssemblyLine.INSTALL_PIECES;
import static planning.example.AssemblyLine.LARGE_EFFECT_PAINT;
import static planning.example.AssemblyLine.PAINT_ROOF;
import static planning.example.AssemblyLine.WHEEL_PARALLEL_INSTALL;*/

/**
 *
 * @author nokenn
 */
//cette classe permet de tester tout ce qui dans AssemblyLines
public class MainAssemblyLine {


    public static void main(String[] args ){
        AssemblyLine assemblyLine = new AssemblyLine();
        //affiche la liste de toutes les actions
        System.out.println("Toutes les actions possible dans AssemblyLine\n "+assemblyLine.toStringActions(assemblyLine.actionListe));
        //affiche l'etat initial
        System.out.println("Etat initial : \n"+assemblyLine.conditionInitiale().toString());
        //affiche l'etat final
        System.out.println("Etat Final : \n"+assemblyLine.genereEtatCoherent().toString());



        //Actions d'installation d'une piece
        System.out.println("actions d'installation d'une piece :\n"+assemblyLine.toStringActions(INSTALL_PIECES()));
        //Actions pour une installation parallele des pneus
        System.out.println("Actions d'installations parallele des pneus :\n"+assemblyLine.toStringActions(WHEEL_PARALLEL_INSTALL()));
        //Actions pour une peinture précise du toit si la carosserie est installée
        System.out.println("Actions pour une  peinture précise du toit :\n"+assemblyLine.toStringActions(PAINT_ROOF()));
        //Actions de peinture à large effet
        System.out.println("Actions de peinture à large effets :\n"+assemblyLine.toStringActions(LARGE_EFFECT_PAINT()));



    }

}
