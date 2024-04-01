package lotto;
import camp.nextstep.edu.missionutils.Randoms;
public class RandomNum {
    int tempNum;
    boolean isRemoved;

    public int RandomNumOneTicket(){
        this.tempNum = Randoms.pickNumberInList(Application.randomWinningNum);
        this.isRemoved = Application.randomWinningNum.remove(Integer.valueOf(tempNum));

        return tempNum;
    }





}
