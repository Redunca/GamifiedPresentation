/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamificationpresentationcounter;

/**
 *
 * @author Augustin
 */
public class Answer {
    private int answerId;
    private boolean isJoker;
    private boolean isBlackHat;
    private boolean isWhiteHat;
    private User responseGiver;
    private String answerText;
    public Answer(){}
    public Answer(int id,boolean isJoker,boolean isBlackHat,boolean isWhiteHat,User responseGiver,String text){
        this.answerId = id;
        this.isBlackHat = isBlackHat;
        this.isJoker = isJoker;
        this.responseGiver = responseGiver;
        this.answerText = text;
    }
    /**
     * @return the answerId
     */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * @param answerId the answerId to set
     */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    /**
     * @return the isJoker
     */
    public boolean isIsJoker() {
        return isJoker;
    }

    /**
     * @param isJoker the isJoker to set
     */
    public void setIsJoker(boolean isJoker) {
        this.isJoker = isJoker;
    }

    /**
     * @return the isBlackHat
     */
    public boolean isIsBlackHat() {
        return isBlackHat;
    }

    /**
     * @param isBlackHat the isBlackHat to set
     */
    public void setIsBlackHat(boolean isBlackHat) {
        this.isBlackHat = isBlackHat;
    }

    /**
     * @return the responseGiver
     */
    public User getResponseGiver() {
        return responseGiver;
    }

    /**
     * @param responseGiver the responseGiver to set
     */
    public void setResponseGiver(User responseGiver) {
        this.responseGiver = responseGiver;
    }

    /**
     * @return the answerText
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * @param answerText the answerText to set
     */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /**
     * @return the isWhiteHat
     */
    public boolean isIsWhiteHat() {
        return isWhiteHat;
    }

    /**
     * @param isWhiteHat the isWhiteHat to set
     */
    public void setIsWhiteHat(boolean isWhiteHat) {
        this.isWhiteHat = isWhiteHat;
    }
    
}
