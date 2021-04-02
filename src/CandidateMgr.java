/**
 *
 * @author Brian Arnold
 * @version Dec 2020
 * @assign.ment Software Engineering
 * @descrip.tion This class implements a 2D-array of Strings,
 * from which a list of Candidate objects will be built.
 *
 */

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.*;

public class CandidateMgr
{
    private static final String CAND_SET_FILE_NAME = "CandidateSet2.ser";   // list of candidates
    private static ArrayList<Candidate> candSet1;           // List used when creating candidates the first time
    private static ArrayList<Candidate> restoredCandSet1;   // List created when building from serialized file (restore)
    //private static ArrayList<Image> imageSet1;

    /*
     * main() - used for creating the serialized list of Candidate objects, and testing them.
     */
    public static void main(String[] args)
    {
        // Do makeCandidates() when creating the serialized file...
        System.out.println("Starting CandidateSet.main()...");
        makeCandidates();

        // Do restoreCandidates when testing rebuilding from serialized file...
        //System.out.println("Starting restoreCandidates()...");
        //restoreCandidates();
        System.out.println("Finished with CandidateSet.main().  B'bye...");
    }

    /*
     * modifyCandidates()
     * Use this method to invoke getters and setters on individual Candidate objects,
     * for testing with makeCandidates() and restoreCandidates()
     */
    public static void modifyCandidates()
    {
        candSet1.get(0).incNumDecoys();
        candSet1.get(1).incNumTargets();
        candSet1.get(2).incNumTargetCorrect();
        candSet1.get(3).incNumTargetWrong();
    }

    /*
     * getCanList()
     * return a reference to the Candidate List (the list restored from the serialized file)
     */
    public static ArrayList<Candidate> getCanList()
    {
        return restoredCandSet1;
    }

    /*
     * makeCandidates()
     * 1. Use the 2D list of Candidates names to create an ArrayList of Candidates.
     * 2. Optional: Invoke modifyCandidates to call setters to test whether changes
     * survive the serialization.
     * 3. Write the serialized ArrayList to a file
     * Note: modifies the candSet1
     */
    public static void makeCandidates()
    {

        int numCandidates = CanNames.getNumNames();
        System.out.println("Debug:" + numCandidates);

        candSet1 = new ArrayList<Candidate>();
        for (int i=0; i<numCandidates; i++)
        {
            // get the names of a Candidate from the CanNames class
            String[] cCand = CanNames.getCandInfo(i);

            // Create and add a Candidate to the list
            candSet1.add(new Candidate(cCand[0], cCand[1], cCand[2], cCand[3]));
            System.out.println(candSet1.get(i));
        }


        // Modify candidates for testing...
        // Comment out if not using...
        /*
        System.out.println("Starting modifyCandidates()...");
        modifyCandidates();
        System.out.println("After modifyCandidates()...");
        for (int i=0; i<numCandidates; i++)
        {
            System.out.println(candSet1.get(i));
        }
        */

        // Write the serialized ArrayList to a file
        try {
            FileOutputStream fs = new FileOutputStream(CAND_SET_FILE_NAME);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(candSet1);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("makeCandidates(): Error writing candidate set file.");
        }
        System.out.println("Done with makeCandidates()...");
    }

    /*
     * checkForCandidateFile()
     * Check to see if the candidate set file is present.
     * If it's not present, create it from CanNames.java.
     */
    public static void checkForCandidateFile()
    {

        /*
         * Is the candidate set file present?  If not, create it.
         */
        try {
            FileInputStream fileStream = new FileInputStream(CAND_SET_FILE_NAME);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("checkForCandidateFile: Could not open candidate set file");
            System.out.println("Attempting to create it...");
            makeCandidates();
            System.out.println("Done preparing candidate file...");
        }


    }

    /*
     * restoreCandidates()
     * 1. Try to read the serialized Candidate ArrayList file, and rebuild the ArrayList.
     * 2. Modifies the restoreCandSet1 reference (used elsewhere)
     * 3. Calls setImage for each Candidate to open up the .jpg image file.
     */
    public static void restoreCandidates()
    {
        int numElements;
        String imageName;
        Image canImage;

        try {
            FileInputStream fileStream = new FileInputStream(CAND_SET_FILE_NAME);
            ObjectInputStream os = new ObjectInputStream(fileStream);
            Object one1 = os.readObject();

            restoredCandSet1 = (ArrayList<Candidate>) one1;
            numElements = restoredCandSet1.size();
            System.out.println("restoredCandSet1 length: " + numElements);

            // Print the list for sanity purposes...
            System.out.println("Restored List...");
            for (int i=0; i<numElements; i++)
            {
                System.out.println(restoredCandSet1.get(i));
            }
            System.out.println("Restoring Candidate images...");
            for (int i=0; i<numElements; i++)
            {
                restoredCandSet1.get(i).setImage();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("restoreCandidates(): Error in restoreCandidates().");
        }
        System.out.println("Done with restoreCandidates()...");

    }
}
