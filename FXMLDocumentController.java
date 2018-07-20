
package spongify;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import static javafx.scene.media.MediaPlayer.INDEFINITE;
import javax.swing.JOptionPane;

/**
 *
 * @author Shad
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextArea pre;
    @FXML
    private TextArea post;
    @FXML
    private TextField songText;

    //1=reddit 2=sponge
    public static int textType = 0;
    //0=lower 1=upper
    public static int capDiff = 0;
    //if true, play music
    public static boolean musicCheck = true;
    //default song
    public AudioClip audio = new AudioClip(getClass().getResource("spongesong.wav").toExternalForm());
    //switch for dif songs
    public static int songCheck = 0;
   
    //clears all text from text areas
   @FXML
    void clearAll(ActionEvent event) {
        pre.clear();
        post.clear();
       
    }
    
   //displays a help message to the user via a dialogue popup
   @FXML
    void helpUser(ActionEvent event) {
        JOptionPane.showMessageDialog(null,
                "TEXT TAB \n"
                + "Freaking die: will capitalize letters 60% of the time and leave lowercase the remaining 40% \n"
                + "Spongebob: Will alternate each letter between being capital and lowercase \n"
                + "Clear: Will clear text from all fields with user input \n\n\n"
                + "MUSIC TAB \n"
                + "Next Song: Plays the next queued song \n"
                + "Previous Song: Plays the last played song \n\n\n"
                + "CHANGE TEXT BUTTON \n"
                + "Rerolls text for instances where too few/many letters are capitalized\n\n\n"
                + "ALL IMAGES AND SONGS CONTAINED IN THIS APPLICATION ARE THE PROPERTY OF NICKELODEAN"
                );
    }
    //onAction for next song item, moves to next song
    //possible future change:relocate volume to each if statement for finetuning quieter songs (roadsong)
    @FXML
    void stopMusic(ActionEvent event) {
        songCheck=0;
        audio.stop();
        songText.setText("Nothing");
    }
    @FXML
    void nextSong(ActionEvent event) {
        int s = INDEFINITE;
        audio.stop();
        if(songCheck==0){
           songText.setText("Gary Come Home");
           audio = new AudioClip(getClass().getResource("Spongebob_Squarepants_-_Gary_Come_Home.wav").toExternalForm()); 
        }
        else if(songCheck==1){
           songText.setText("Road Song");
           audio = new AudioClip(getClass().getResource("SpongeBob Road Song.wav").toExternalForm());    
        }
        else if(songCheck==2){
           songText.setText("F.U.N");
           audio = new AudioClip(getClass().getResource("Spongebob - F.U.N. Song.wav").toExternalForm());    
        }
        else if(songCheck==3){
           songText.setText("First Christmas");
           audio = new AudioClip(getClass().getResource("Spongebob Very First Christmas.wav").toExternalForm());    
        }
        else if(songCheck==4){
            songText.setText("Best Day Ever");
            audio = new AudioClip(getClass().getResource("spongesong.wav").toExternalForm());
        }
        else{
            songText.setText("Nothing");
            songCheck = 0;
            musicCheck=false;
        }
    if(musicCheck==true){
        songCheck++;
        audio.setVolume(0.5f);
        audio.setCycleCount(s);
        audio.play();
    }
    musicCheck=true;
    }

    //onAction for previous song menu item
    @FXML
    void previousSong(ActionEvent event) {
         int s = INDEFINITE;
       audio.stop();
        songCheck--;
        if(songCheck==0){
           songText.setText("Nothing");
           musicCheck=false;
        }
        else if(songCheck==1){
           songText.setText("Gary Come Home");
           audio = new AudioClip(getClass().getResource("Spongebob_Squarepants_-_Gary_Come_Home.wav").toExternalForm()); 
            
        }
        else if(songCheck==2){
           songText.setText("Road Song");
           audio = new AudioClip(getClass().getResource("SpongeBob Road Song.wav").toExternalForm());   
              
        }
        else if(songCheck==3){
           songText.setText("F.U.N");
           audio = new AudioClip(getClass().getResource("Spongebob - F.U.N. Song.wav").toExternalForm()); 
             
        }
        else if(songCheck==4){
            songText.setText("First Christmas");
            audio = new AudioClip(getClass().getResource("Spongebob Very First Christmas.wav").toExternalForm());  
        }
        else{
            songText.setText("Best Day Ever");
            songCheck = 5;
            audio = new AudioClip(getClass().getResource("spongesong.wav").toExternalForm());
            
        }
    if(musicCheck==true){
        audio.setVolume(0.5f);
        audio.setCycleCount(s);
        audio.play();
    }
    musicCheck=true;
    }

    //switches static variable textType to 1 so that text changes will be /r/peoplefreakingdying style
    @FXML
    void redditChange(ActionEvent event) {
        textType = 1;
    }
    
    //switches static variable textType to 0(default) so that text changes will be spongebob meme style
    @FXML
    void spongebobChange(ActionEvent event) {
        textType = 0;
    }
   
    //onAction for the change text button
    //applies styling of the current user choice to text
    //basically re-rolls text in the text area to a new type, or the same type but done differently
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(textType == 0){
          ArrayList<String> stringArray = new ArrayList<>();
          String finalString = "";
          Character c = ' ';
          post.setWrapText(true);
          pre.setWrapText(true);
          stringArray.add(pre.getText());
          for(String s : stringArray){
            for(int i = 0; i<s.length(); i++){
              if(capDiff == 0){
                c = Character.toUpperCase(s.charAt(i));
                finalString+=c;
                capDiff++;
              }
              else{
                  c = s.charAt(i);
                  finalString+=c;  
                  capDiff--;
              }
            }
         }
        post.setText(finalString);
        post.autosize();
      }
      else if(textType == 1){
        ArrayList<String> stringArray = new ArrayList<>();
        String finalString = "";
        Random rand = new Random();
        Character c = ' ';
        post.setWrapText(true);
        pre.setWrapText(true);
        stringArray.add(pre.getText());
        for(String s : stringArray){
          for(int i = 0; i<s.length(); i++){
              if(rand.nextInt(100) > 49){
                  c = Character.toUpperCase(s.charAt(i));
                  finalString+=c;
              }
              else{
                  c = s.charAt(i);
                  finalString+=c;  
              }
          }
        }
       post.setText(finalString);
       post.autosize();
      } 
    }
  
    //responsible for outputting text as it is typed to the second text area
    //will do spongebob text if textType is 0 and reddit text if type is 1
    @FXML
    public void keyPressed() {
        if(textType == 0){
        ArrayList<String> keyList = new ArrayList<>();
        String keys = " ";
        keyList.add(pre.getText());
        //inefficient currently, iterates entire array.
        for(String s : keyList) {
            for(int i = 0; i <s.length(); i++){
                if(capDiff==0){
                    keys+=Character.toUpperCase(s.charAt(i));
                    capDiff++;
                }
                else{
                    capDiff--;
                    keys+=s.charAt(i);
                }
            }
        }
        post.setText(keys);
        }
        else if(textType ==1){
            Random r = new Random();
        ArrayList<String> keyList = new ArrayList<>();
        String keys = " ";
        keyList.add(pre.getText());
        for(String s : keyList) {
            for(int i = 0; i <s.length(); i++){
                if(r.nextInt(100) > 49){
                    keys+=Character.toUpperCase(s.charAt(i));
                }
                else{
                    keys+=s.charAt(i);
                }
            }
        }
        post.setText(keys);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
