/**
 * Created by SimonSchwieler on 2017-06-08.
 */
public class Result {

    private double value;
    private Participant participant;
    private Result result;
    private Event event;

    public Result(Participant participant, double value, Result result){
        this.participant = participant;
        this.value = value;
        this.result = result;
    }

    public Result(Event e, Result r, Participant p) {
        this.event = e;
        this.participant = p;
        this.result = r;
    }

    public double getValue(){
        return value;
    }

    public Participant getParticipant(){
        return participant;
    }

    //Behövs både getResult och getResultFromList?
    public Result getResult(){
        return result;
    }

    public Result getResultFromList() {
        return result;
    }

    public Event getEvent() {
        return event;
    }

    public String toString(){
        return "ResultString: " + value + " " + participant + " " + result;
    }

    public String toString(){
        return "   ---" + result.getResult() + " " + event.getEventName() + " " +  participant.getFirstName() + " " + participant.getLastName();
    }

}
