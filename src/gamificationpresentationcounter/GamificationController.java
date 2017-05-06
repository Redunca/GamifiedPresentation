/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamificationpresentationcounter;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Augustin
 */
public class GamificationController {
    private GamificationView view;
    private GamificationEndingView endView;
    private ArrayList<User> users;
    private ArrayList<Answer> answers;
    private boolean shouldSetBlackHatDominant;
    public GamificationController(){
        view = new GamificationView(this);
        users = new ArrayList<>();
        answers = new ArrayList<>();
        createUsers();
        view.setVisible(true);
        shouldSetBlackHatDominant = false;
    }
    private void createUsers(){
        users.add(new User("DOE John",0));
        users.add(new User("JOBS Steve",1));
        users.add(new User("TORVALD Linus",2));
        users.add(new User("GATES Bill",3));
    }
    public void addAnswer(int answerID,int userId,boolean isJoker,String answerText){
        this.answers.add(new Answer(answers.size(), isJoker, isBlackHat(answerID),isWhiteHat(answerID), getUserById(userId),answerText));
        
        if(answers.size()==9){
            showResultsWindow();
        }
    }
    public boolean isBlackHat(int answerID){
        switch(answerID){
            case 4:
            case 5:
            case 6:
                return true;
            default :
                return false;
        }
    }
    public User getUserById(int id){
        for(User u : users){
            if(u.getId()==id){
                return u;
            }
        }
        return null;
    }
    public void showResultsWindow(){
        this.endView = new GamificationEndingView(this);
        this.endView.setUpWindow();
        this.view.setVisible(false);
        this.endView.setVisible(true);
    }
    public void changePlayerName(int playerId,String newName){
        User user = getUserById(playerId);
        user.setName(newName);
    }
    public boolean canUserStillUseJoker(int userId){
        for(Answer a : answers){
            if(a.isIsJoker() && a.getResponseGiver().getId()==userId){
                return false;
            }
        }
        return true;
    }

    public int getPointsById(int id) {

        if(this.getUserById(id).getScore()!= (-1)){
            return this.getUserById(id).getScore();
        }
        int blackhats=0;
        int whitehat=0;
        int numberOfAnswers=0;
        int total=0;
        boolean skippedJoker = false;
        Random ran = new Random();
        for(Answer a : answers){
            if(a.isIsBlackHat()){
                blackhats++;
                if(a.isIsJoker()){
                    skippedJoker = true;
                }
            }else if(a.isIsWhiteHat()){
                whitehat++;
            }
            if(a.getResponseGiver().getId()==id){
                numberOfAnswers++;
            }
        }
        if(canUserStillUseJoker(id)){
            int value = ((ran.nextInt(4))+1);
            this.endView.setRandomGain(id,value);
            total+= value;
        }
        if(this.isShouldSetBlackHatDominant()){
            total+=numberOfAnswers*2;
            total--;
            if(skippedJoker){
                total++;
            }
        }else{
            total+=numberOfAnswers*2;
        }
        this.getUserById(id).setScore(total);
        return total;
    }
    public String getNameOfWinner(){
        int player0 = getPointsById(0);
        int player1 = getPointsById(1);
        int player2 = getPointsById(2);
        int player3 = getPointsById(3);
        int result;
        Random ran = new Random();
        result = ((ran.nextInt()%(player0+player1+player2+player3))+1);
        if(result<=player0){
            return getUserById(0).getName();
        }
        if(result<=(player0+player1)){
            return getUserById(1).getName();
        }
        if(result<=(player0+player1+player3)){
            return getUserById(2).getName();
        }
        return getUserById(3).getName();
    }

    private boolean isWhiteHat(int answerID) {
         switch(answerID){
            case 1:
            case 2:
            case 8:
                return true;
            default :
                return false;
        }
    }

    public String getAllAnswerTexts() {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<answers.size();i++){
            builder.append("Question "+(i+1)+" ("+answers.get(i).getResponseGiver().getName()+"):\n");
            builder.append(answers.get(i).getAnswerText());
            builder.append("\n\n");
        }
        return builder.toString();
    }

    /**
     * @return the shouldSetBlackHatDominant
     */
    public boolean isShouldSetBlackHatDominant() {
        int blackhats=0;
        int whitehat=0;
        for(Answer a : answers){
            if(a.isIsBlackHat()){
                
                if(!a.isIsJoker()){   
                    blackhats++;
                }
            }else if(a.isIsWhiteHat()){
                 if(!a.isIsJoker()){
                    whitehat++;
                 }
            }
        }
        if(blackhats>whitehat){
            shouldSetBlackHatDominant = true;
        }
        return shouldSetBlackHatDominant;
    }

    /**
     * @param shouldSetBlackHatDominant the shouldSetBlackHatDominant to set
     */
    public void setShouldSetBlackHatDominant(boolean shouldSetBlackHatDominant) {
        this.shouldSetBlackHatDominant = shouldSetBlackHatDominant;
    }
}
