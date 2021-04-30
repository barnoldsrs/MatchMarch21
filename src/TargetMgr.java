/**
 *
 * @author Brian Arnold
 * @version Dec 2020
 * @assign.ment Software Engineering
 * @descrip.tion This class implements a utility function, to manage the Candidate choices
 * used for every round.
 * Not entirely implemented yet.
 *
 */

import java.util.ArrayList;
import java.util.Collections;

public class TargetMgr {


    // Want to avoid reusing a target name too soon after using it previously
    static final int MAX_RECENT_TARGETS = 5;

    // Number of non-target names to show on the screen
    static final int NUM_DECOYS = 3;

    // List built consisting of both one target and N decoy Candidates.
    static ArrayList<Candidate> targetDecoyList;

    // List of target Candidates used recently.  Managed to prevent showing the
    // same target too soon after the last time.  TBD.
    static ArrayList<Candidate> recentTargets = new ArrayList<Candidate>();

    /*
     * getTargetDecoys()
     * Given the big (entire) Candidate list as a parameter, create and return a new list.
     * The new list should consist of one target Candidate, and NUM_DECOYS Candidates.
     * Typically we place the target Candidate at position 0 of the list, and the
     * decoy candidates after.
     * Returns the new list of target and decoys.
     */
    public static ArrayList<Candidate> getTargetDecoys(ArrayList<Candidate> canList)
    {
        // Shuffle the list
        Collections.shuffle(canList);

        /*
         * Method validatedTarget will return a name from nameList
         * that hasn't been used in the last N times.
         * N could be 3, or 5 or 9 or whatever makes sense.
         * (MAX_RECENT_TARGETS)
         */
        targetDecoyList = validatedTargetDecoys(canList, recentTargets);

        return targetDecoyList;
    }

    /*
     * isCandidateInList()
     * Given a reference to a Candidate object, and a reference to an
     * ArrayList of Candidates (the list should contain four Candidates in the list),
     * Does the candidate object appear anywhere in the list?
     * Return true if it does, false if it doesn't
     */
    private static boolean isCandidateInList(Candidate canTarg, ArrayList<Candidate>recentList)
    {
        for(int i=0; i<recentList.size(); i++)
        {
            if (canTarg == recentList.get(i))
            {
                return true;
            }
        }
        return false;
    }

    /*
     * validatedTarget()
     * Checks to see if the first element in the list (the candidate target)
     * has been used recently.  If it has, reshuffle the entire list and pick
     * four new targets and decoys (target @ index 0; three decoys follow).
     * When a candidate is found that hasn't been used recently, this candidate
     * will be the new target, so copy all four candidates to a new list to be returned.
     * Update the recent target list (purge the oldest one if it's full),
     * and place this target at the front of the recent target list).
     * Return the new list with the valid target and three decoys.
     */
    public static ArrayList<Candidate> validatedTargetDecoys(ArrayList<Candidate> sourceList, ArrayList<Candidate> cantUseList)
    {
        while (isCandidateInList(sourceList.get(0), cantUseList) == true)
        {
            /*
             * if the first in the list has been used recently, reshuffle
             * the entire list and pick the first four again.
             */
            if(SceneMaker.isDebugging() == true) {
                System.out.println("Oops! Can't use that one again so soon. "
                        + sourceList.get(0).getFirstName() + " " + sourceList.get(0).getLastName());
            }
            Collections.shuffle(sourceList);
        }

        /*
         * Copy target and decoys to ArrayList to be returned.
         * This is the list that will be returned to populate the
         * names on the buttons
         */
        ArrayList<Candidate> resultCan = new ArrayList<Candidate>();
        for(int i=0; i<NUM_DECOYS + 1; i++){
            resultCan.add(sourceList.get(i));
        }

        /*
         * Add this valid target to the front of the cantUseList.
         * If the list is full, get rid of the oldest one
         * and still the newest one at the front of the cantUseList.
         */
        if (cantUseList.size() >= MAX_RECENT_TARGETS)
        {
            // get rid of the oldest one in this list
            cantUseList.remove(MAX_RECENT_TARGETS-1);
        }
        // add this new candidate to the front of the list
        cantUseList.add(0,sourceList.get(0));

        return resultCan;
    }
}
