/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football_project;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.ContainerController;

import java.util.*;

/**
 *
 * @author ahmed
 */
public class Invironment {
    public static jade.core.Runtime r = jade.core.Runtime.instance();
    public static Profile p = new ProfileImpl("localhost",2021,"football-invironment");
    public static ContainerController main= r.createMainContainer(p);
    public static List<Integer> teams = new ArrayList<Integer>();
    public static List<Integer> winnerTeams = new ArrayList<Integer>();
    public static Set<Integer> playingTeams = new HashSet<Integer>();
    public static HashMap<Integer, Integer> teamsInRound = new HashMap<>();
    public static         int[] teamsScore = new int[17];
    public static String playground[]={
            "Camp Nou",
            "Wembley",
            "Signal Iduna Park",
            "Estadio Santiago Bernabéu",
            "Stade De France",
            "San Siro",
            "Atatürk Olympic Stadium",
            "Allianz Arena",
            "Old Trafford"
    };
    public static String Referee[]={
            "Felix Brych",
            "Cüneyt Çakır",
            "Carlos Del Cerro Grande",
            "Andreas Ekberg",
            "Ovidiu Alin Hategan",
            "Sergei Karasev",
            "Istvan Kovacs ",
            "Bjorn Kuipers ",
            "Danny Makkelie",
            "Antonio Miguel Mateu Lahoz ",
            "Michael Oliver",
            "Artur Manuel Ribeiro Soares Dias",
            "Daniel Siebert",
            "Anthony Taylor"
    };

}
