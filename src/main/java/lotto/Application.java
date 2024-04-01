package lotto;
import camp.nextstep.edu.missionutils.Console;

import java.text.NumberFormat;
import java.util.ArrayList;


public class Application {

    public enum MatchEnum{

        matchSix(2000000000),
        matchFiveB(30000000),
        matchFive(1500000),
        matchFour(50000),
        matchThree(5000);
        private final int value;
        MatchEnum(int value){
            this.value = value;
        }
        public String getFormattedAmount() {
            return NumberFormat.getInstance().format(this.value);
        }
    }




    static final int numThousand = 1000;
    static final int numSix = 6;
    static final int numFourtyFive = 45;
    static final int numFive = 5;
    static final int numFour = 4;
    static final int numThree = 3;
    static final int numZero = 0;
    static final int priceSix = 2000000000;
    static final int priceFiveB = 30000000;
    static final int priceFive = 1500000;
    static final int priceFour = 50000;
    static final int priceThree = 5000;
    static RandomNum randomnum = new RandomNum();
    static String winningNumStr;
    static int winnerInt = 0;
    static int winnerBonusInt = 0;
    static int money;
    static int chance = 0;
    static int bonusNum = 0;
    static int matchSix = 0;
    static int matchFive = 0;
    static int matchFiveB = 0;
    static int matchFour = 0;
    static int matchThree = 0;
    static float averagePrice;
    public static ArrayList<String> priceList = new ArrayList<>();
    public static ArrayList<Integer> lotto = new ArrayList<>();
    public static ArrayList<Integer> randomWinningNum = new ArrayList<>();
    public static ArrayList<Integer> ticket = new ArrayList<>();
    static void InputTicket(){
        //금액 입력 메서도, 오류체크
        money = Integer.parseInt(Console.readLine());
        boolean moneybool = false;
        while(!moneybool){
            try{
                if(money / numThousand ==0){
                    throw new IllegalArgumentException("숫자가 1000원 단위가 아닙니다");
                }
                moneybool = true;
            } catch(NumberFormatException e){
                System.out.println("유효하지 않은 입력. 정수가 아닙니다");
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        } chance = money/numThousand;
    }
    static void ErrorCheck(){
        //6자리 번호 입력 메서드, 오류확인
        winningNumStr = Console.readLine();
        boolean lottoBool = false;
        String [] winningNumArr = winningNumStr.split(",");
        while(!lottoBool){
            try{
                if(winningNumArr.length != numSix) {
                    throw new IllegalArgumentException("갯수가 맞지 않습니다.");
                }
                lottoBool = true;
            } catch (NumberFormatException e) {
                System.out.println("유효하지 않은 입력");
            } catch (IllegalArgumentException e){
                System.out.println("숫자가 6개가 아닙니다.");
            }
        }
    }
    static void InputWinningNum() {
        //6자리 숫자 리스트화
        ErrorCheck();
        String[] winningNumArr = winningNumStr.split(",");
        Integer[] winningNum = new Integer[numSix];
        for (int i = 0; i < winningNumArr.length; i++) {
            winningNum[i] = Integer.parseInt(winningNumArr[i]);
        }
        for (int i = 0; i < winningNumArr.length; i++) {
            lotto.add(i, winningNum[i]);
        }
    }

    static void InputBonusNum(){
        //보너스 번호 입력 메서드, 오류확인
        String bonusNumStr = Console.readLine();
         boolean bonusCheck = false;
         while(!bonusCheck){
             try{
                 bonusNum = Integer.parseInt(bonusNumStr);
                 if(lotto.contains(bonusNum)){
                     throw new NumberFormatException("로또6자리 숫자안에 이미 포함되어 있습니다.");
                 }
                 bonusCheck = true;
             } catch (NumberFormatException e){
                 System.out.println("정수형이 아닙니다.");
             }
         }
    }
//_______________________________________입력단
    static void RandomWinningNumMethod(){
        //1~45 리스트 생성 메서드
        //1~45 숫자 리스트 생성
        randomWinningNum.clear();
        for(int i = 0; i < numFourtyFive; i++){
        randomWinningNum.add(i, i+1);
        }
    }

    static void TicketCreate(){
        //6자리 로또 한장 생성
        RandomWinningNumMethod();
        for(int i = 0; i < numSix; i++){
            int tempNum = randomnum.RandomNumOneTicket();
            ticket.add(tempNum);
        }
System.out.println(ticket);
    }
    static void CompareOneLotto(){
        //당첨 번호 수
        TicketCreate();

        for (Integer integer : lotto) {
            if (ticket.contains(integer)) {
                winnerInt++;
            }
        }
        for(int i = 0; i < lotto.size(); i++){
            if(ticket.contains(bonusNum)){
                winnerBonusInt++;
            }
        }
    }

    static void WinningMoney() {
        for(int i = 0; i < chance; i++){
            CompareOneLotto();
            if(winnerInt == numSix){ matchSix++; }
            if(winnerInt == numFive && winnerBonusInt == numZero){ matchFiveB++; }
            if(winnerInt == numFive && winnerBonusInt != numZero){ matchFive++; }
            if(winnerInt == numFour){ matchFour++; }
            if(winnerInt == numThree){ matchThree++; }
            winnerInt = 0;
            winnerBonusInt = 0;
            ticket.clear();
        }
    }
    static void moneyCalculate(){
        WinningMoney();
        int totalPrice = (matchSix * priceSix + matchFive * priceFive + matchFiveB * priceFiveB + matchFour * priceFour + matchThree * priceThree);
        averagePrice = (float) totalPrice /money;
    }
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        for(MatchEnum matchenum : MatchEnum.values()){
            priceList.add(matchenum.getFormattedAmount());
        }
        System.out.println("구입금액을 입력해주세요.");
        InputTicket();
        System.out.print(chance + "개를 구매했습니다.");
        System.out.println("당첨 번호를 입력해주세요");
        InputWinningNum();
        System.out.println("보너스 번호를 입력해 주세요.");
        InputBonusNum();
        moneyCalculate();
        System.out.println("당첨 통계 \n ---");
        System.out.println("3개 일치 ("+                priceList.get(4) + "원) - " + matchThree + "개");
        System.out.println("4개 일치 (" +               priceList.get(3) + "원) - " + matchFour + "개");
        System.out.println("5개 일치 (+"+               priceList.get(2) + "원) - " + matchFive + "개");
        System.out.println("5개 일치, 보너스볼 일치 (" +  priceList.get(1) + "원) - " + matchFiveB + "개");
        System.out.println("6개 일치(" +                priceList.get(0) + "원) - " + matchSix   + "개");
        System.out.println("총 수익률은" +               averagePrice     + "%입니다.");
    }
}
