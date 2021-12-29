/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football_project;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author ahmed
 */
public class team15 extends Agent{
    
    @Override
    protected void setup() {

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg= receive();
        
                if(msg!= null)
                {
                    String knowlage = msg.getContent();
                    System.out.println(knowlage);
                    int score=(int)(Math.random()*10);
                    
                    ACLMessage reply =msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent(String.valueOf(score));
                    send(reply);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    doDelete();
                }
                else {
                    block();
                }
            }
        } );
    }
}
