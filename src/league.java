package football_project;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;

import java.util.Map;

public class league extends Agent {

    @Override
    protected void setup() {
        try {

            String pacageName = "football_project", agentStander = "team", result1 = "", result2 = "", message = "";
            int playGround, count = 0;

            for (Map.Entry<Integer, Integer> currentTeam : Invironment.teamsInRound.entrySet()) {

                count++;
                System.out.println(currentTeam.getKey() + "   " + currentTeam.getValue());
                //start first agent
                result1 = pacageName + '.' + agentStander + currentTeam.getKey();
                result2 = agentStander + currentTeam.getKey();
                AgentController Team1 = Invironment.main.createNewAgent(result2, result1, null);
                Team1.start();

                //start second agent
                result1 = pacageName + '.' + agentStander + currentTeam.getValue();
                result2 = agentStander + currentTeam.getValue();
                AgentController Team2 = Invironment.main.createNewAgent(result2, result1, null);
                Team2.start();


                /**
                 * massage format
                 * match referee
                 * match playground ( it will be random number from 1-9 )
                 * + match Time (it will be (1) refer to 60 minutes)
                 * + Opponent Team
                 */
                playGround = (int) (Math.random() * 8 );
                int referee = (int) (Math.random() * 8 );
                message="match play ground is  ";
                message += Invironment.playground[playGround];

                message = message + "   \nand referee name is   ";
                message+=Invironment.Referee[referee];

                String matchtime=( "\n match time is   "+9+"clock --day "+(int)(Math.random()*30+1)+" - "+(int)(Math.random()*12+1));
                message+=matchtime;

                ScorePanel.change_label_content(("match information  "+message+"\n \t between "+
                        agentStander + currentTeam.getKey()+"   "+agentStander + currentTeam.getValue()));


                ACLMessage msgToTeam1 = new ACLMessage(ACLMessage.INFORM);
                msgToTeam1.setContent(message + " team " +currentTeam.getValue());
                msgToTeam1.addReceiver(new AID(agentStander + currentTeam.getKey(), AID.ISLOCALNAME));
                send(msgToTeam1);

                ACLMessage msgToTeam2 = new ACLMessage(ACLMessage.INFORM);
                msgToTeam2.setContent(message + " team " +currentTeam.getKey());
                msgToTeam2.addReceiver(new AID(agentStander + currentTeam.getValue(), AID.ISLOCALNAME));
                send(msgToTeam2);

                /**
                 * here to get the score to update GUI
                 *
                 */
                addBehaviour(new CyclicBehaviour() {
                    @Override
                    public void action() {
                        ACLMessage msg = receive();
                        if (msg != null) {
                            //System.out.println(msg.getSender().getName());
                            String ms = msg.getSender().getName();
                            int index = 0, c = 10;
                            for (int i = 4; i < ms.length(); i++) {
                                if (ms.charAt(i) == '@') {
                                    break;
                                }
                                index = (index * c) + (ms.charAt(i) - '0');
                            }
                            Invironment.teamsScore[index] += Integer.parseInt(msg.getContent());
                            System.out.println("current score of team" + index + " is =>" + Invironment.teamsScore[index]);
                        } else {
                            block();
                        }
                    }
                });
                Invironment.teamsScore[currentTeam.getKey()]=(int)(Math.random()*10);
                Invironment.teamsScore[currentTeam.getValue()]=(int)(Math.random()*10);
                if(Invironment.teamsScore[currentTeam.getKey()]==Invironment.teamsScore[currentTeam.getValue()])
                {
                    Invironment.teamsScore[currentTeam.getValue()]+=1;
                }

                ScorePanel.tx2.setText(ScorePanel.tx2.getText()+"\n"+
                        "  team"+currentTeam.getKey()+"  "+
                        Invironment.teamsScore[currentTeam.getKey()]
                        +"   -   "+Invironment.teamsScore[currentTeam.getValue()]+"  team"+currentTeam.getValue());


                if (Invironment.teamsScore[currentTeam.getKey()] > Invironment.teamsScore[currentTeam.getValue()]) {
                    Invironment.winnerTeams.add(currentTeam.getKey());
                    ScorePanel.tx2.setText(ScorePanel.tx2.getText()+" ===> team "+currentTeam.getKey());
                }
                else {
                    Invironment.winnerTeams.add(currentTeam.getValue());
                    ScorePanel.tx2.setText(ScorePanel.tx2.getText()+" ===> team "+currentTeam.getValue());
                }

               Thread.sleep(1000);


            }

            if(Invironment.teamsInRound.size() == 1){
                System.out.println("it,s the end and the winner team is: " + Invironment.winnerTeams.get(0));
                ScorePanel.tx2.setText(ScorePanel.tx2.getText()+"\n"+"it,s the end and the winner team is: " + Invironment.winnerTeams.get(0));
                Invironment.teams.clear();
                Invironment.teams.clear();
                Invironment.winnerTeams.clear();
                Invironment.playingTeams.clear();
                Invironment.teamsInRound.clear();
                this.doDelete();
                return;
            }

            Invironment.teams.clear();
            Invironment.teams.addAll(Invironment.winnerTeams);
            Invironment.winnerTeams.clear();
            Invironment.playingTeams.clear();
            Invironment.teamsInRound.clear();

            this.doDelete();

        }catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
