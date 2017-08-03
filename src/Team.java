/**
 * Created by SimonSchwieler on 2017-06-08.
 */
public class Team {

    private Team teamMedals;
    private String team;
    private int firstPlace;
    private int secondPlace;
    private int thirdPlace;

    public Team(Team teamMedals, String team) {
        this.teamMedals = teamMedals;
        this.team = team;
    }

    public Team getTeamMedals(){
        return teamMedals;
    }

    public Team(int fp, int sp, int tp) {
        this.firstPlace = fp;
        this.secondPlace = sp;
        this.thirdPlace = tp;
    }

    public String getTeam(){
        return team;
    }

    public int getFirstPlace(){
        return firstPlace;
    }

    public int getSecondPlace(){
        return secondPlace;
    }

    public int getThirdPlace(){
        return thirdPlace;
    }

    public void incrementFirstPlace(){
        firstPlace++;
    }

    public void incrementSecondPlace(){
        secondPlace++;
    }

    public void incrementThirdPlace(){
        thirdPlace++;
    }

}
