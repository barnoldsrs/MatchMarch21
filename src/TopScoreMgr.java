import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TopScoreMgr {
    private  static ArrayList<TopScoreEntry> speedmodelist;
    private static ArrayList<TopScoreEntry> timedmodelist;

    private static final String SPEED_LIST_FILENAME = "TopScoreListSpeed.ser";
    private static final String TIMED_LIST_FILENAME = "TopScoreListTimed.ser";

    public TopScoreMgr(ArrayList<TopScoreEntry> S, ArrayList<TopScoreEntry> T){
        speedmodelist = S;
        timedmodelist = T;
    }

    public TopScoreMgr(){

    }

    ///////////////////////////////////////Build First Time Methods//////////////////////////////////

    public static void buildTopScoreList_SpeedFirstTime()
    {
        // Do makeCandidates() when creating the serialized file...
        System.out.println("Starting TopScoreMgr.buildTopScoreList_SpeedFirstTime()...");
        speedmodelist = new ArrayList<TopScoreEntry>();

        // Write the serialized ArrayList to a file
        try {
            FileOutputStream fs = new FileOutputStream(SPEED_LIST_FILENAME);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(speedmodelist);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("buildTopScoreList_SpeedFirstTime: Error writing Top Score Speed List to file.");
        }
        System.out.println("Finished with TopScoreMgr.buildTopScoreList_SpeedFirstTime()...  B'bye...");
    }

    public static void buildTopScoreList_TimedFirstTime()
    {
        // Do makeCandidates() when creating the serialized file...
        System.out.println("Starting TopScoreMgr.bbuildTopScoreList_TimedFirstTime()...");
        timedmodelist = new ArrayList<TopScoreEntry>();

        // Write the serialized ArrayList to a file
        try {
            FileOutputStream fs = new FileOutputStream(TIMED_LIST_FILENAME);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(timedmodelist);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("buildTopScoreList_TimedFirstTime: Error writing Top Score Timed List to file.");
        }
        System.out.println("Finished with TopScoreMgr.buildTopScoreList_TimedFirstTime()...  B'bye...");
    }

    public static void initializeTopScoreLists(){
        buildTopScoreList_SpeedFirstTime();
        buildTopScoreList_TimedFirstTime();
    }

    ////////////////////////////////Restore Methods////////////////////////////////////////////

    public static void restoreTopScoreList_Speed()
    {
        int numElements;

        try {
            FileInputStream fileStream = new FileInputStream(SPEED_LIST_FILENAME);
            ObjectInputStream os = new ObjectInputStream(fileStream);
            Object one1 = os.readObject();

            speedmodelist = (ArrayList<TopScoreEntry>) one1;

            numElements = speedmodelist.size();
            System.out.println("speedmodelist.size(): " + numElements);

            // Print the list for sanity purposes...
            showList(speedmodelist);


        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("restoreTopScoreList_Speed(): Error in restoreTopScoreList_Speed().");
        }
        System.out.println("Done with restoreTopScoreList_Speed()...");

    }

    public static void restoreTopScoreList_Timed()
    {
        int numElements;

        try {
            FileInputStream fileStream = new FileInputStream(TIMED_LIST_FILENAME);
            ObjectInputStream os = new ObjectInputStream(fileStream);
            Object one1 = os.readObject();

            timedmodelist = (ArrayList<TopScoreEntry>) one1;

            numElements = timedmodelist.size();
            System.out.println("timedmodelist.size(): " + numElements);

            // Print the list for sanity purposes...
            showList(timedmodelist);


        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("restoreTopScoreList_Timed(): Error in restoreTopScoreList_Timed().");
        }
        System.out.println("Done with restoreTopScoreList_Timed()...");

    }

    /////////////////////////Save Methods//////////////////////////////

    public static void saveTopScoreList_Speed()
    {

        // Write the serialized ArrayList to a file
        try {
            FileOutputStream fs = new FileOutputStream(SPEED_LIST_FILENAME);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(speedmodelist);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("saveTopScoreList_Speed: Error writing Top Score Speed List to file.");
        }
        System.out.println("Finished with saveTopScoreList_Speed()...  B'bye...");
    }

    public static void saveTopScoreList_Timed()
    {

        // Write the serialized ArrayList to a file
        try {
            FileOutputStream fs = new FileOutputStream(TIMED_LIST_FILENAME);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(timedmodelist);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("saveTopScoreList_Timed: Error writing Top Score Timed List to file.");
        }
        System.out.println("Finished with saveTopScoreList_Timed()...  B'bye...");
    }

    public static void showList(ArrayList<TopScoreEntry> list)
    {
        for(TopScoreEntry entry : list)
        {
            entry.printEntryInfo();
            System.out.println("-------------------");
        }
    }

    //Checks if score is valid for being entered into the top 10 scores and returns the index of where it should go
    public static int newSpeedScoreIndex(TopScoreEntry tes){
        for (int i = 0; i < 10; i--) {
            if(tes.getTime() < speedmodelist.get(i).getTime()){

                return i;
            }
            System.out.println("<-");
        }
        return -1;
    }

    //Checks if score is valid for being entered into the top 10 scores and returns the index of where it should go
    public static int newTimedScoreIndex(TopScoreEntry tes){
        for (int i = 0; i < 10; i++) {
            if(tes.getCount() > timedmodelist.get(i).getCount()){
                System.out.println(i + "<-");
                return i;
            }
        }
        return -1;
    }

    //tests if the score can be add and if it can i will shift the scores accordingly
    //and inserts the new score where it should be
    public static void submitSpeedScore(TopScoreEntry tse){
        ArrayList<TopScoreEntry> templist = speedmodelist;

        if(newSpeedScoreIndex(tse) != -1){
            for(int i = 0; i < newSpeedScoreIndex(tse); i++){
                templist.set(i, speedmodelist.get(i));
                //System.out.println(i);
            }
            templist.set(newSpeedScoreIndex(tse),speedmodelist.get(newSpeedScoreIndex(tse)));
            for(int i = newSpeedScoreIndex(tse)+1; i < speedmodelist.size(); i++){

                templist.set(i, speedmodelist.get(i-1));
                //System.out.println(i);
            }
            speedmodelist = templist;
        }else {
            System.out.println("Not a worthy score");
        }
    }

    //tests if the score can be add and if it can i will shift the scores accordingly
    //and inserts the new score where it should be
    public static void submitTimedScore(TopScoreEntry tse){
        ArrayList<TopScoreEntry> templist = timedmodelist;

        if(newTimedScoreIndex(tse) != -1){
            for(int i = 0; i < newTimedScoreIndex(tse); i++){
                templist.set(i ,timedmodelist.get(i));
            }
            templist.set(newTimedScoreIndex(tse), timedmodelist.get(newTimedScoreIndex(tse)));
            for(int i = newTimedScoreIndex(tse)+1; i < timedmodelist.size(); i++){
                templist.set(i, timedmodelist.get(i-1));
            }
            timedmodelist = templist;
        }
    }

    //////////////// Testing Methods //////////////////

    public static void testModifySpeedList(){
        speedmodelist.add(0, new TopScoreEntry("Jake", 20, 345));
        speedmodelist.add(1, new TopScoreEntry("Daniel", 20, 443));
        speedmodelist.add(2, new TopScoreEntry("Pam", 20, 590));
        speedmodelist.add(3, new TopScoreEntry("Flynn", 20, 591));
        speedmodelist.add(4, new TopScoreEntry("Nathan", 20, 602));
        speedmodelist.add(5, new TopScoreEntry("Sarah", 20, 629));
        speedmodelist.add(6, new TopScoreEntry("Person 1", 20, 655));
        speedmodelist.add(7, new TopScoreEntry("Person 2", 20, 708));
        speedmodelist.add(8, new TopScoreEntry("Person 3", 20, 730));
        speedmodelist.add(9, new TopScoreEntry("Person 4", 20, 746));
    }

    public static void testModifyTimedList(){
        timedmodelist.add(0, new TopScoreEntry("Jake", 56, 600));
        timedmodelist.add(1, new TopScoreEntry("Daniel", 50, 600));
        timedmodelist.add(2, new TopScoreEntry("Pam", 45, 600));
        timedmodelist.add(3, new TopScoreEntry("Flynn", 40, 600));
        timedmodelist.add(4, new TopScoreEntry("Nathan", 35, 600));
        timedmodelist.add(5, new TopScoreEntry("Sarah", 30, 600));
        timedmodelist.add(6, new TopScoreEntry("Person 1", 25, 600));
        timedmodelist.add(7, new TopScoreEntry("Person 2", 20, 600));
        timedmodelist.add(8, new TopScoreEntry("Person 3", 15, 600));
        timedmodelist.add(9, new TopScoreEntry("Person 4", 10, 600));
    }

    //////////////// Getters & Setters /////////////////

    public static TopScoreEntry getSpeedEntry(int i){
        return speedmodelist.get(i);
    }

    public static void setSpeedEntry(TopScoreEntry tse, int i){
        speedmodelist.set(i, tse);
    }

    public static TopScoreEntry getTimedEntry(int i){
        return  timedmodelist.get(i);
    }

    public static void setTimedEntry(TopScoreEntry tse, int i){
        timedmodelist.set(i, tse);
    }

    public static ArrayList<TopScoreEntry> getSpeedmodelist(){
        return speedmodelist;
    }

    public static ArrayList<TopScoreEntry> getTimedmodelist(){
        return timedmodelist;
    }

    //////////////// Main ////////////////////

    public static void main(String[] args) {
        initializeTopScoreLists();
        testModifySpeedList();
        System.out.println(newSpeedScoreIndex(new TopScoreEntry("Jerry", 20, 563)));
    }
}
