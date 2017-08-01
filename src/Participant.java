/**
 * Created by SimonSchwieler on 2017-06-08.
 */
public class Participant {



    private String name;
    private String lastName;
    private Team team;
    private int number;


    public Participant(String name, String lastName, Team team, int number){
        this.name = name;
        this.lastName = lastName;
        this.team = team;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Team getTeam() {
        return team;
    }

    public int getNumber() {
        return number;
    }

    public void checkAttempts(Event event){

    }

    public void addResult(Result result){

    }

    public void printResults(){

    }

    public String toString(){
        return name + " " + lastName + " " + team + " " + number;
    }





}
