//rodi0231_sisc7379_arho2993

public class Event {

    private String eventName;
    private int attemptsAllowed;

    private int medal = 0;

    public Event(String eventName, int attemptsAllowed) {
        this.eventName = eventName;
        this.attemptsAllowed = attemptsAllowed;
    }

    public String getEventName() {
        return eventName;
    }

    public int getAttemptsAllowed() {
        return attemptsAllowed;
    }

    public int amountOfMedalsAwarded(){
        return medal;
    }

    public void awardMedal(){
        ++medal;
    }

    public void resetMedals(){
        medal = 0;
    }

    public String toString(){
        return eventName + "";
    }


}