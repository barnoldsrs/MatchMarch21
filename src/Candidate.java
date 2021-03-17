/**
 *
 * @author Brian Arnold
 * @version Dec 2020
 * @assign.ment Software Engineering
 * @descrip.tion This class represents one person, their names, their picture,
 * and statistics (counters) regarding use within the aface-name matching program.
 *
 */


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.Serializable;

public class Candidate implements Serializable
{
    private String firstName;
    private String lastName;
    private String nickName;
    private int numTargets;                 // num times selected as a target
    private int numDecoys;                  // num times selected as a decoy
    private int numTargetGuessedCorrect;    // num times was target and was guessed correctly
    private int numTargetGuessedWrong;      // num times was target but not guessed
    private int numDecoyGuessedWrong;       // num times was decoy but was guessed (incorrectly)
    private String imageName;               // file name of .jpg file
    private transient Image canImage;       // reference to Image object.  Can't be serialized, so
                                            // .jpg file must be opened and canImage assigned after serialized file
                                            // has been opened and restored.


    /*
     * Candidate() constructor will (try to) open the .jpg filename and assign the canImage field.
     * This happens when making the candidate list the first time, using makeCandidates().  That list is
     * then written to a serialized file.
     * The restoreCandidates() method will read from the serialized file and reconstitute the
     * ArrayList of Candidates.  At that time the image file for each Candidate object will be opened,
     * and the image assigned to the canImage field.
     *
     */
    public Candidate(String fn, String ln, String nn, String iName)
    {
        firstName = fn;
        lastName = ln;
        nickName = nn;
        numTargets = 0;
        numDecoys = 0;
        numTargetGuessedCorrect = 0;
        numTargetGuessedWrong = 0;
        numDecoyGuessedWrong = 0;
        imageName = iName;
        try {
            //canImage = new Image(new FileInputStream(imageName));
            //canImage = new Image(new FileInputStream(getClass().getClassLoader().getResource(imageName)));
            canImage = new Image(new FileInputStream("./MatchMarch21/res/" + imageName)); // FIXME.  The right way.
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    /*
     * setImage():
     * Open the filename in the Candidate instance, and assign the Image structure to the canImage field
     */
    public void setImage()
    {
        try {
            //canImage = new Image(new FileInputStream(imageName));
            //canImage = new Image(new FileInputStream(getClass().getClassLoader().getResource(imageName)));
//            this.canImage = new Image(new FileInputStream(".//res//" + imageName));
 //           String currentDirectory = System.getProperty("user.dir");
 //           System.out.println("The current working directory is " + currentDirectory);


            String openName = "./MatchMarch21/res/" + imageName;
            System.out.println(openName);
            this.canImage = new Image(new FileInputStream(openName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Getters...
     */
    public int getNumTargets()
    {
        return numTargets;
    }
    public int getNumDecoys()
    {
        return numDecoys;
    }
    public Image getImage()
    {
        return canImage;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getNickName()
    {
        return nickName;
    }
    public String getImageName()
    {
        return imageName;
    }
    public int getNumTargetCorrect()
    {
        return numTargetGuessedCorrect;
    }
    public int getNumTargetWrong()
    {
        return numTargetGuessedWrong;
    }
    public int getNumDecoyWrong()
    {
        return numDecoyGuessedWrong;
    }

    /*
     * Setters...
     */

    public void incNumTargets()
    {
        numTargets += 1;
    }
    public void incNumDecoys()
    {
        numDecoys += 1;
    }

    public void incNumTargetCorrect()
    {
        numTargetGuessedCorrect += 1;
    }
    public void incNumTargetWrong()
    {
        numTargetGuessedWrong += 1;
    }
    public void incNumDecoyWrong()
    {
        numDecoyGuessedWrong += 1;
    }


    /*
     * toString()
     */
    public String toString()
    {
        String result;
        result = firstName + " " + lastName + ": T/D/Tgc/Tgw/Dgw: " +
                numTargets + "/" + numDecoys + "/" +
                numTargetGuessedCorrect + "/" +
                numTargetGuessedWrong + "/" +
                numDecoyGuessedWrong;

        return result;
    }
}
