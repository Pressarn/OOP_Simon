import java.util.ArrayList;

/**
 * Created by SimonSchwieler on 2017-06-08.
 */
public class Event {

    private String eventName;
    private int attempts;
    private boolean biggerBetter;

    public Event(String eventName, int attempts, boolean biggerBetter){
        this.eventName = eventName;
        this.attempts = attempts;
        this.biggerBetter = biggerBetter;
    }


    public String getEventName(){
        return eventName;
    }

    public int getAttempts(){
        return attempts;
    }


    public boolean isBiggerBetter(){
        return biggerBetter;
    }

    public void addResult(Result result){

    }

    public void eventResult(){

    }

    public ArrayList<Result> sortResult(ArrayList<Result> resultArrayList){
        return resultArrayList;

    }
    public void removeResult(Participant participant){

    }

    public int getAttemptsAllowed(){
        return attempts;
    }

    public String toString(){
        return eventName + " " + attempts + " " + biggerBetter;
    }



}
