package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.UtilitySquare;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {

    private String name;
    private int ClientID;
    private int position = 0;
    private int layer = 1;
    private int targetPosition = 0;
    private int money = 3200;
    private HashMap<String, ArrayList<PropertySquare>> propertyCardsMap;
    private ArrayList<PropertySquare> propertySquares;
    private ArrayList<UtilitySquare> utilityList;
    private ArrayList<Card> cardList;
    private boolean inJail;
    private boolean direction = true;

    public Player(String name, int ClientID) {
        this.name = name;
		this.ClientID = ClientID;
        propertyCardsMap = new HashMap<>();
        propertySquares = new ArrayList<>();
        utilityList = new ArrayList<>();
    }

    public Player(String name) {
        this.name = name;
        propertyCardsMap = new HashMap<>();
        propertySquares = new ArrayList<>();
        utilityList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(int position) {
        this.targetPosition = position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int newmoney) {
        money = newmoney;
    }

    public HashMap<String, ArrayList<PropertySquare>> getPropertyCardsMap() {
        return propertyCardsMap;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }

    public ArrayList<PropertySquare> getPropertySquares() {
        return propertySquares;
    }

    public void propertySquares(ArrayList<PropertySquare> properties) {
        this.propertySquares = properties;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setinJail(boolean b) {
        inJail = b;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public ArrayList<UtilitySquare> getUtilityList() {
        return this.utilityList;
    }

    public void addSquare(PropertySquare propertySquare) {
        // propertyCardsMap.put(propertySquare)
        propertySquares.add(propertySquare);
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    @Override
    public String toString() {
        String temp = "";
        return "Player name: " + name + "\n" +
                "Money: " + money + "\n" +
                "Owned properties: " + propertySquares.toString() + "\n" +
                "Owned utilities: " + utilityList;
    }

}