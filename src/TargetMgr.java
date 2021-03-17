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
     * validatedTarget()
     * Checks to see if the first element in the list (the candidate target)
     * has been used recently.  If it has, reshuffle the list and check again.
     * When a name is found that hasn't been used recently, this name will be
     * the new target.  Update the recent target list (purge the oldest one,
     * and place this target at the front of the recent target list).
     * Return the new target name.
     */
    public static ArrayList<Candidate> validatedTargetDecoys(ArrayList<Candidate> sourceList, ArrayList<Candidate> cantUseList)
    {
        /*
         * More work to do here, as suggested by comments.
         *
         */
        ArrayList<Candidate> resultCan = new ArrayList<Candidate>();
        for(int i=0; i<NUM_DECOYS + 1; i++){
            resultCan.add(sourceList.get(i));
        }

        return resultCan;
    }
}
