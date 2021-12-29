package football_project;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScorePanel extends Application {

    public static TextArea tx1=new TextArea("");
    public static TextArea tx2=new TextArea("");

    public static TextArea  lb1=new TextArea("here we will show each much detail");
    Stage smallStage=new Stage();

    public static void change_label_content(String x){
        lb1.setText(x);
    }



    @Override
    public void start(Stage stage) throws Exception {
        ///////////////////////////////////////////////////////////////////
        Pane spane=new Pane();
        spane.getChildren().add(lb1);
        Scene sscene=new Scene(spane);
        smallStage.setScene(sscene);
        smallStage.show();
        smallStage.setTitle("smal pane");

        //////////////////////////////////////////////////////////////////
        AgentController GUI =Invironment.main.createNewAgent("GUI", "jade.tools.rma.rma", null);
        GUI.start();


        for (int i=1;i<=16;i++) {
            Invironment.teams.add(i);
            Invironment.teamsScore[i]=0;
        }



        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        GridPane pane=new GridPane();
        BorderPane Bpane=new BorderPane();


        Stage primaryStage=new Stage();

        Button button=new Button("play");

        tx1.setEditable(false);
        tx2.setEditable(false);
//////////////////////////////-------------//////////////////////////-----------///////////////////////////////////////////////////////
        button.setOnAction(event -> {

            try{
            if(!Invironment.teams.isEmpty())
            {
                tx2.setText("");
                while(Invironment.teams.size() > 0){
                    int team1, team2, team1Demo, team2Demo;
                    /**
                     * team1,team2            is index in list
                     * team1Demo , team2Demo  is the values of teams
                     */


                    //detect first player
                    while (true) {//بتختار من العناصر الي معاك

                        team1 = (int)(Math.random()*Invironment.teams.size());
                        team1Demo = Invironment.teams.get(team1);

                        if (!Invironment.playingTeams.contains(team1Demo)) {
                            Invironment.playingTeams.add(team1Demo);
                            Invironment.teams.remove(team1);
                            break;
                        }
                    }

                    //detect second player
                    while (true) {

                        team2 = (int)(Math.random()*Invironment.teams.size());
                        team2Demo = Invironment.teams.get(team2);

                        if (!Invironment.playingTeams.contains(team2Demo)) {
                            Invironment.playingTeams.add(team2Demo);
                            Invironment.teams.remove(team2);
                            break;
                        }
                    }

                    Invironment.teamsInRound.put(team2Demo, team1Demo);

                }

            }

            String pacageName="football_project", agentStander= "team" , result1="", result2="" , message="";
            int playGround ,count=0;
/////////
            tx1.setText("");
            for (Map.Entry<Integer, Integer> currentTeam :Invironment.teamsInRound.entrySet()) {
                tx1.setText(tx1.getText()+"\n"+agentStander+currentTeam.getKey()+"    "+
                        agentStander+currentTeam.getValue());
            }
            // map throw teams to start matches
            //currentTeam for teams key for first team    value for second team
                AgentController league = Invironment.main.createNewAgent("league", "football_project.league", null);
                league.start();



            }catch (Exception ex) {
                System.out.println(ex);
                System.out.println("Score panel exception");
            }


        });
        //////////////////////////////////////////////////////////////////////////////////////////////////
        Bpane.setTop(tx1);
        Bpane.setCenter(button);
        Bpane.setBottom(tx2);

        Scene scene=new Scene(Bpane);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Score pane");






    }

    public static  void main(String [] args)
    {
        launch(args);
    }


}
