import java.util.*;

/**
 * Created by SimonSchwieler on 2017-06-08.
 */
public class Contest {


    Scanner keyboard = new Scanner(System.in);
    private ArrayList<Result> resultArrayList = new ArrayList<>();
    private ArrayList<Result> resultListArrayHandler = new ArrayList<>();
    private ArrayList<Participant> participantArrayList = new ArrayList<>();
    private ArrayList<Event> eventArrayList = new ArrayList<>();
    private int id = 99;


    private String readString(String prompt) {
        System.out.print("> " + prompt);
        return keyboard.nextLine();
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        int number = keyboard.nextInt();
        keyboard.nextLine();
        return number;
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        double number = keyboard.nextDouble();
        keyboard.nextLine();
        return number;
    }

    private String readCommand() {
        return readString("Command: ").toLowerCase();
    }

    private void initiate() {
        System.out.println("Welcome");
    }

    private void writeMenu() {
        System.out.println("Following commandos are possible:");
        System.out.println(" Add event");
        System.out.println(" Add participant");
        System.out.println(" Remove participant");
        System.out.println(" Add result");
        System.out.println(" Participant");
        System.out.println(" Teams");
        System.out.println(" Message (followed by your message)");
        System.out.println(" Reinitialize");
        System.out.println(" Exit");
    }

    private void manageCommand(String command) {

        if (command.matches("message.+")) {
            message(command);
        }
        else if (getEvent(command) != null){
            resultEvent(command);
        }

        else {
            switch (command) {
                case "add event":
                    addEvent();
                    break;
                case "add participant":
                    addParticipant();
                    break;
                case "remove participant":
                    removeParticipant();
                    break;
                case "add result":
                    addResultParticipant();
                    break;
                case "participant":
                    resultParticipant();
                    break;
                case "result event":
                    break;
                case "teams":
                    resultTeam();
                    break;
                case "message":
                    break;
                case "reinitialize":
                    reinitialize();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    System.out.println("Unknown command: " + command);

            }
        }
    }

    private void addEvent() {

        String eventName = "";
        while (eventName.isEmpty() || getEvent(eventName) != null) {
            eventName = readString("Enter name of event: ");
            eventName = eventName.trim();
            if (eventName.isEmpty()) {
                System.out.println("Names can't be empty!");
            } else if (getEvent(eventName) != null) {
                System.out.println(normalizer(eventName) + " has already been added!");
                return;
            }

        }
        normalizer(eventName);


        int attemptsAllowed = readInt("Attempts allowed: ");

        while (attemptsAllowed <= 0){
            attemptsAllowed = readInt("Number to small, write something else: ");
        }

        String biggerBetterString = readString("Bigger better? (yes/no): ");
        boolean biggerBetter = false;
        boolean biggerBetterOptionDone = false;

        while(!biggerBetterOptionDone) {

            if (biggerBetterString.equalsIgnoreCase("yes") || biggerBetterString.equalsIgnoreCase("y")) {
                biggerBetter = true;
                biggerBetterOptionDone = true;
            } else if (biggerBetterString.equalsIgnoreCase("no") || biggerBetterString.equalsIgnoreCase("n")) {
                biggerBetter = false;
                biggerBetterOptionDone = true;
            } else {
                biggerBetterString = readString("Wrong insertion, write something else: ");
            }
        }

        eventName = eventName.trim().substring(0,1).toUpperCase() + eventName.substring(1).toLowerCase();
        Event e = new Event(eventName, attemptsAllowed);
        eventArrayList.add(e);

        System.out.println(e.getEventName() + " has been added.");
    }

    private String normalizer(String s){
        s = s.toLowerCase();
        char[] name = s.toCharArray();
        name[0] = ("" + (name[0])).toUpperCase().charAt(0);
        s = new String(name);
        return s;
    }

    private void addParticipant() {

        String firstName = readString("Enter participants first name: ");
        firstName = firstName.trim();

        while (firstName.equals("")) {
            System.out.println("Names can't be empty!");

            firstName = readString("Enter participants first name: ");
            firstName = firstName.trim();
        }

        String lastName = readString("Enter participants last name: ");
        lastName = lastName.trim();

        while (lastName.equals("")) {
            System.out.println("Names can't be empty!");

            lastName = readString("Enter participants last name: ");
            lastName = lastName.trim();
        }

        String teamName = readString("Enter participants team: ");
        teamName = teamName.trim();

        while (teamName.equals("")) {
            System.out.println("Names can't be empty!");

            teamName = readString("Enter participants team: ");
            teamName = teamName.trim();
        }

        firstName = normalizer(firstName);
        lastName = normalizer(lastName);
        teamName = normalizer(teamName);


        Participant p = new Participant(firstName, lastName, teamName, id);
        participantArrayList.add(p);

        System.out.println(p.getFirstName() + " " + p.getLastName() + " from "
                + p.getTeamName() + " with number " + p.getId() + " has been added.");
    }


    private void removeParticipant() {

        int enterID = readInt("Enter ID on participant you would like to remove: ");

        boolean participantFound = false;

        for (int x = 0; x < participantArrayList.size(); x++) {

            if (participantArrayList.get(x).getId() == (enterID)) {

                System.out.println(
                        participantArrayList.get(x).getFirstName() + " " + participantArrayList.get(x).getLastName()
                                + " from " + participantArrayList.get(x).getTeamName() + " with number "
                                + participantArrayList.get(x).getId() + " removed");
                participantArrayList.remove(x);
                participantFound = true;

            }
        }

        if (!participantFound) {
            System.out.println("No participant with number " + enterID + " exists.");

        }
    }

    private void addResultParticipant() {

        int writtenId = readInt("Enter participants ID: ");
        boolean participantFound = false;


        for (int x = 0; x < participantArrayList.size(); x++) {
            if (participantArrayList.get(x).getId() == (writtenId)) {
                addResultEvent(participantArrayList.get(x));
                participantFound = true;
            }
        }
        if (!participantFound) {
            System.out.println("No participant with number " + writtenId + " exists.");
        }
    }

    private void addResultEvent(Participant p) {

        String enterEventName = readString("Enter event: ");

        Event e = getEvent(enterEventName);
        if (e == null){
            System.out.println("No event called " + enterEventName + " exists.");
            return;
        }

        if (p.getAmountOfAttempts(e) < e.getAttemptsAllowed()) {

            double result = readDouble("Result for " + p.getFirstName() + " " + p.getLastName() + " from " + p.getTeamName()
                    + " in the event " + e.getEventName() + ": ");

            while (result < 0.0) {
                result = readDouble("To low value entered, write something else: ");
            }

            Result r = new Result(result, p, e);
            resultArrayList.add(r);
            p.setResultToList(e, r, p);
            resultListArrayHandler.add(new Result(e, r, p));

            System.out.println("Result " + result + " in " + e.getEventName() + " has been registred.");
        }
        else {
            System.out.println("To many tries!");
        }
    }

    private void resultParticipant() {

        int participantID = readInt("Resultlist for participants.\nType in the ID number: ");
        Participant p = getParticipant(participantID);

        try {

            String allResults = "";
            for (Event e : eventArrayList) {


                ArrayList<Double> resultsByEvent = p.getResultsByEvent(e);
                String resultString = "";
                if(!resultsByEvent.isEmpty()){
                    for (Double r : resultsByEvent){
                        resultString += r;
                        if(resultsByEvent.indexOf(r) != resultsByEvent.size() -1){
                            resultString += ", ";
                        }
                    }
                    allResults += "Results for " + p.getFirstName() + " "
                            + p.getLastName() + " in " + e.getEventName() + ": " + resultString + "\n";
                }
            }
            if (allResults.length() == 0){
                System.out.println("No results found.");
            }
            else{
                System.out.print(allResults);
            }
        }
        catch(NullPointerException e){
            System.out.println("No participant with that ID");

        }
    }

    private void resultEvent(String command) { //Kolla hur Gabbes ser ut, om den gör samma saker.

        List<TopListPosition> topList = new ArrayList();

        Event e = getEvent(command);
        double participantsBestResult;
        String name;

        if (participantArrayList.isEmpty() || resultArrayList.isEmpty()){
            System.out.println("No event with that name or no results found.");

        }

        else{

            System.out.println("Results for " + normalizer(command) + ":");


            for (Participant participant : this.participantArrayList) {

                participantsBestResult = participant.getBestResultBiggerBetterTrue(e);
                name = participant.getFirstName() + " " + participant.getLastName();

                topList.add(new TopListPosition(name, participantsBestResult, e));

            }

        }

        Collections.sort(topList, new Comparator<TopListPosition>() {
                    public int compare(TopListPosition obj1, TopListPosition obj2) {
                        if (e.getBiggerBetter())
                            return (int) (obj2.getScore() - obj1.getScore());
                        else if(obj2.getScore() == obj1.getScore()){
                            return 0;
                        }
                        else
                            return (int) (obj1.getScore() - obj2.getScore());
                    }
                }
        );
        int placement = 1;
        TopListPosition previousPosition = null;
        for (int i = 0; i < topList.size(); i++) {
            TopListPosition currentPosition = topList.get(i);
            if (previousPosition != null) {
                if(currentPosition.getScore() != previousPosition.getScore()){
                    placement = i;
                    placement++;
                }
            }
            previousPosition = currentPosition;

            if(currentPosition.getScore() > 0) {

                System.out.println((placement) + " " + currentPosition.getScore() + " " + currentPosition.getName());
            }

        }

    }

    public class ScoreComparator implements Comparator<TopListPosition> {
        public int compare(TopListPosition t1, TopListPosition t2){

            if (t1.getScore() == t2.getScore())
                return 0;
            else if (t1.getScore() > t2.getScore())
                return 1;
            else
                return -1;

        }
    }

    private Integer getPosition(Participant p,  final Event event) {


        List<TopListPosition> topListForTeams = new ArrayList<>();

        String participantName = null;
        Double participantResult = 0.0;
        Event  participantEvent = event;



        for (Participant participant : this.participantArrayList) {
            for (Result result : participant.getResults()) {

                if(event.getBiggerBetter()) {

                    participantName = result.getParticipant().getFirstName() + " " + result.getParticipant().getLastName();
                    participantResult = result.getParticipant().getBestResultBiggerBetterTrue(event);


                }
//                else if (!event.getBiggerBetter()){
//
//                    participantName = result.getParticipant().getFirstName() + " " + result.getParticipant().getLastName();
//                    participantResult = result.getParticipant().getBestResultBiggerBetterFalse(event);
//
//
//                }

                if (participantResult >= 0) {
                    topListForTeams.add(new TopListPosition(participantName, participantResult, participantEvent));
                    break;
                }
            }
        }

        if (event.getBiggerBetter()){
            Collections.sort(topListForTeams,Collections.reverseOrder(new ScoreComparator()));

        }
        else if (!event.getBiggerBetter()){
            Collections.sort(topListForTeams, new ScoreComparator());

        }

        try {

            double firstPlace = topListForTeams.get(0).getScore();
            double secondPlace = 0;
            double thirdPlace = 0;

            for (TopListPosition topListPosition : topListForTeams) {
                double score = topListPosition.getScore();



                if(event.getBiggerBetter()) {

                    if (score >= firstPlace) {
                        event.awardMedal();
                        firstPlace = score;
                    } else if (score >= secondPlace && event.ammountOfMedalsAwarded() < 2) {
                        event.awardMedal();
                        secondPlace = score;
                    } else if (score >= thirdPlace && event.ammountOfMedalsAwarded() < 3) {
                        thirdPlace = score;
                    }

                }
                else if(!event.getBiggerBetter()){

                    if (score <= firstPlace && firstPlace != 0.0) {
                        event.awardMedal();
                        firstPlace = score;
                    } else if ((score <= secondPlace || secondPlace == 0) && event.ammountOfMedalsAwarded() < 2)  {
                        event.awardMedal();
                        secondPlace = score;
                    } else if ((score <= thirdPlace || thirdPlace == 0) && event.ammountOfMedalsAwarded() < 3 ){
                        thirdPlace = score;
                    }
                }
            }

            event.resetMedals();

            for (TopListPosition topListPosition : topListForTeams) {

                if (topListPosition.getName().equals(p.getFirstName() + " " + p.getLastName()) && topListPosition.getEvent().equals(event)) {
                    double score = topListPosition.getScore();
                    if (firstPlace == score) {
                        return 1;
                    } else if (secondPlace == score) {
                        return 2;
                    } else if (thirdPlace == score) {
                        return 3;
                    }
                }
            }

        }catch(Exception e){}
        return null;
    }

    private void resultTeam() {

        if (participantArrayList.isEmpty()) {
            System.out.println("No teams available.");

        } else {
            Map<String, Team> teams = new HashMap<>();
            for (Event event : this.eventArrayList) {
                for (Participant participant : this.participantArrayList) {

                    Integer position = this.getPosition(participant, event);
                    if (position != null) {
                        Team team = teams.get(participant.getTeamName());
                        if (team == null) {
                            team = new Team(0, 0, 0);
                            teams.put(participant.getTeamName(), team);
                        }

                        if (position == 1) {
                            team.incrementFirstPlace();

                        } else if (position == 2) {
                            team.incrementSecondPlace();

                        } else if (position == 3) {
                            team.incrementThirdPlace();
                        }
                    }

                }
            }
            for (Participant participant : this.participantArrayList) {
                Team team = teams.get(participant.getTeamName());
                if(team == null) {
                    team = new Team(0, 0, 0);
                    teams.put(participant.getTeamName(), team);
                }
            }

            List<Team> teamResults = new ArrayList<>();
            for (Map.Entry<String, Team> entry : teams.entrySet()) {
                teamResults.add(new Team(entry.getValue(), entry.getKey()));
            }
            Collections.sort(teamResults, new Comparator<Team>() {
                public int compare(Team obj1, Team obj2) {
                    if (obj1.getTeamMedals().getFirstPlace() != obj2.getTeamMedals().getFirstPlace()) {
                        return obj2.getTeamMedals().getFirstPlace() - obj1.getTeamMedals().getFirstPlace();
                    }

                    if (obj1.getTeamMedals().getSecondPlace() != obj2.getTeamMedals().getSecondPlace()) {
                        return obj2.getTeamMedals().getSecondPlace() - obj1.getTeamMedals().getSecondPlace();
                    }

                    else{
                        return obj2.getTeamMedals().getThirdPlace() - obj1.getTeamMedals().getThirdPlace();
                    }}
            });
            System.out.println("");
            System.out.println("1st    2nd    3rd    Team name");
            System.out.println("*******************************");
            for (Team teamResult : teamResults) {
                System.out.println(teamResult.getTeamMedals().getFirstPlace() + "      " +
                        teamResult.getTeamMedals().getSecondPlace() + "       " +
                        teamResult.getTeamMedals().getThirdPlace() + "      " +
                        teamResult.getTeamMedals());

            }
            teamResults.clear();
        }

        for (Event e : eventArrayList){
            e.resetMedals();
        }
    }

    private void reinitialize() {

        String message = " ALL DATA HAS BEEN REMOVED";
        Participant.reinitializeID();
        char fill = ' ';

        String toPad = "#";
        String specialInLine = new String(new char[toPad.length() + 57 - message.length()]).replace('\0', fill) + toPad;
        System.out.println("");
        printSign(60, '#');
        System.out.println("");
        System.out.println("#                                                          #");
        System.out.print("#" + message);
        System.out.println(specialInLine);
        System.out.println("#                                                      	   #");
        printSign(60, '#');
        System.out.println("");
        while (participantArrayList.size() > 0) {
            participantArrayList.clear();
        }

        while (resultArrayList.size() > 0) {
            resultArrayList.clear();
        }

        while (eventArrayList.size() > 0) {
            eventArrayList.clear();
        }
    }

    private void message(String message) {

        message = message.replaceFirst("message", "");

        if (message.length() > 56) {
            message = message.substring(0, 56);
        }

        char fill = ' ';

        String toPad = "#";
        String specialInLine = new String(new char[toPad.length() + 56 - message.length()]).replace('\0', fill) + toPad;
        System.out.println("");
        printSign(60, '#');
        System.out.println("");
        System.out.println("#                                                          #");
        System.out.print("# " + message.toUpperCase());
        System.out.println(specialInLine);
        System.out.println("#                                                          #");
        printSign(60, '#');
        System.out.println("");

    }

    private void printSign(int ammount, char type){
        for (int x = 0; x < ammount; x++){
            System.out.print(type);
        }
    }

    //Fundera på om denna ska ligga här eller i Participant
    private Participant getParticipant(int id) {
        for (Participant p : participantArrayList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    //Fundera på om denna ska ligga här eller i Event
    private Event getEvent (String eventName){
        for (Event e : eventArrayList){
            if (e.getEventName().equalsIgnoreCase(eventName)){
                return e;
            }
        }
        return null;
    }

    private void exit() {
        System.out.println("Good bye!");
        System.exit(0);
    }

    private void execute() {
        writeMenu();
        while (true) {
            String command = readCommand();
            manageCommand(command);
        }
    }

    public static void main(String[] args) {
        Contest competition = new Contest();
        competition.initiate();
        competition.execute();
        competition.exit();
    }

    public class TopListPosition implements Comparable<TopListPosition> {

        private String name;
        private double score;
        private Event event;

        public TopListPosition(String name, double score, Event event) {
            this.name = name;
            this.score = score;
            this.event = event;
        }

        @Override
        public int compareTo(TopListPosition o) {
            return 0;
        }

        public class ScoreComparator implements Comparator<TopListPosition> {
            public int compare(TopListPosition t1, TopListPosition t2){

                if (t1.score == t2.score)
                    return 0;
                else if (t1.score > t2.score)
                    return 1;
                else
                    return -1;

            }
        }

        public String toString(){
            return name + " " + event.getEventName() + " " + score + " ";
        }

        public String getName(){
            return name;
        }

        public double getScore(){
            return score;
        }

        public Event getEvent(){
            return event;
        }

    }

}
