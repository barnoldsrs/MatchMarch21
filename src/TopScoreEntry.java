public class TopScoreEntry {

    private String name = null; //Name of user entering score
    private int count = -1; //number of questions the got through in their run
    private long time = -1; //how long their run went on for
    private boolean mode; //determines if the run is from speed mode (true) or timed mode (false)

    TopScoreEntry(String n, int c, long t){
        name = n;
        count = c;
        time = t;
    }

    TopScoreEntry(String n, int c, long t, boolean m){
        name = n;
        count = c;
        time = t;
        mode = m; //true is speed mode, false is timed mode
    }



    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int c){
        count = c;
    }

    public long getTime(){
        return time;
    }

    public void setTime(long t){
        time = t;
    }

    public boolean getMode(){
        return mode;
    }

    public void setMode(boolean m){
        mode = m;
    }

    public void printEntryInfo(){
        System.out.print("Name: " + name);
        System.out.print(", Count: " + count);
        System.out.print(", Time: " + time);
        System.out.println(", Mode (Speed = f, Timed = t): " + mode);
    }
    public void OLDprintEntryInfo(){
        System.out.println("Name: " + name);
        System.out.println("Count: " + count);
        System.out.println("Time: " + time);
        System.out.println("Mode (Speed = f, Timed = t): " + mode);
    }

}
