package lotto;

import java.util.ArrayList;

public class MoneyCompare {
    ArrayList<Integer> ticket;
    int matchCount;
    int matchBonusL;

    int earnMoney;


    public MoneyCompare(ArrayList<Integer> ticket, int matchCount, int matchBonusL){
        this.matchCount = matchCount;
        this.matchBonusL = matchBonusL;
        this.ticket = ticket;


    }

    public void SetTicket(ArrayList<Integer> ticket){
        this.ticket = ticket;
    }
    public void SetMatchCount(int matchCount){
        this.matchCount = matchCount;
    }
    public void setMatchBonusL(int matchBonusL){
        this.matchBonusL = matchBonusL;
    }
    public ArrayList<Integer> GetTicket(){
        return ticket;
    }
    public int GetMatchCount(){
        return matchCount;
    }
    public int GetMatchBonusL(){
        return matchBonusL;
    }
}
