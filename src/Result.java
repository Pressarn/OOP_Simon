/**
 * Created by SimonSchwieler on 2017-06-08.
 */
public class Result {

    private double value;
    private Participant participant;
    private Result result;
    private double newResult;
    private Event event;

    public Result(Event e, Result r, Participant p) {
        this.event = e;
        this.participant = p;
        this.result = r;
    }

    public Result (double newResult, Participant participant, Event event){
        this.newResult = newResult;
        this.participant = participant;
        this.event = event;
    }

    public Result(Participant participant, double value, Result result){
        this.participant = participant;
        this.value = value;
        this.result = result;
    }

    public double getValue(){
        return value;
    }


    //Behövs både getResult och getResultFromList?

    public double getResult(){
        return newResult;
    }

    public Result getResultFromList() {
        return result;
    }

    public Event getEvent() {
        return event;
    }

    public Participant getParticipant(){
        return participant;
    }

    public double getNewResult(){
        return newResult;
    }

    public String toString(){
        return "resultToString" + result + "";
    }

    //Borttagna pga verkar inte användas?
//    public String toString(){
//        return "ResultString: " + value + " " + participant + " " + result;
//    }
//
//    public String toString(){
//        return "   ---" + result.getResult() + " " + event.getEventName() + " " +  participant.getFirstName() + " " + participant.getLastName();
//    }

}
