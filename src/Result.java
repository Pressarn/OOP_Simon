/**
 * Created by SimonSchwieler on 2017-06-08.
 */
public class Result {

    private double value;
    private Participant participant;
    private Result result;



    public Result(Participant participant, double value, Result result){
        this.participant = participant;
        this.value = value;
        this.result = result;
    }


    public double getValue(){
        return value;
    }

    public Participant getParticipant(){
        return participant;
    }

    public Result getResult(){
        return result;
    }

    public String toString(){
        return "ResultString: " + value + " " + participant + " " + result;
    }



}
