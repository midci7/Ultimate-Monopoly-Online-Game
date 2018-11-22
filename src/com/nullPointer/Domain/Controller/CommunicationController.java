package com.nullPointer.Domain.Controller;

import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.RegularDie;
import com.nullPointer.Domain.Model.SpeedDie;
import com.nullPointer.Domain.Server.Client;
import com.nullPointer.Domain.Server.GameServer;
import com.nullPointer.Domain.Server.ServerInfo;

import java.util.ArrayList;

public class CommunicationController {

    private static CommunicationController _instance;
    private GameServer gameServer;
    private Client client;
    private GameEngine gameEngine = GameEngine.getInstance();
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();

    private CommunicationController() {

    }

    public static CommunicationController getInstance() {
        if (_instance == null) {
            _instance = new CommunicationController();
        }
        return _instance;
    }

    public void startServer() {
        gameServer = new GameServer("SERVER");
        gameServer.start();
    }

    public void createClient(String IP) {
        client = new Client(IP, "Client");
        client.start();
    }

    public void createClient() {
        client = new Client("Client");
        client.start();
    }

    public void sendClientMessage(Object msg) {
        client.sendMessage(msg);
    }

    public void removeClient() {

    }

    public void processInput(Object objectInput) {
        if(objectInput instanceof String) {
            String input = (String) objectInput;
            if (input.contains("game")) {
                if (includes(rest(input), "start")) {
                    gameEngine.startGame();
                }
            } else if (input.contains("player")) {
                if (includes(rest(input), "create")) {

                }
            } else if (input.indexOf("message") != -1) {
                gameEngine.publishEvent("message/" + rest(input));
            } else if (input.contains("client")) {
                if (includes(rest(input), "create")) {
                    String clientId = (rest(rest(input)));
                    gameEngine.newClient(clientId);
                }
            } else if (input.contains("dice")) {
                ArrayList<Integer> regularDice = new ArrayList<>();
                ArrayList<Integer> speedDice = new ArrayList<>();
                String[] values = input.split("/");
                regularDice.add(Integer.parseInt(values[1]));
                regularDice.add(Integer.parseInt(values[2]));
                speedDice.add(Integer.parseInt(values[3]));
                regularDie.setLastValues(regularDice);
                speedDie.setLastValues(speedDice);
                gameEngine.movePlayer();
            } else if (input.contains("purchase")) {
                gameEngine.buy();
            } else if (input.contains("card")) {
                if (rest(input).contains("draw")) {
                    gameEngine.drawCard();
                } else if (rest(input).contains("play")) {
                    gameEngine.playCard();
                }
            } else if (input.contains("improveProperty")) {
                gameEngine.improveProperty();
            }
        }else if(objectInput instanceof ArrayList) {
            serverInfo.setClientList((ArrayList) objectInput);
        }else if(objectInput instanceof Player) {
            gameEngine.addPlayer((Player) objectInput);
        }
    }

    private String rest(String word) {
        int slashIndex = word.indexOf('/');
        if (slashIndex == -1)
            return word;
        return word.substring(slashIndex + 1);
    }

    private boolean includes(String sentence, String word) {
        return (sentence.indexOf(word) != -1);
    }
}