import java.util.*;
import java.util.Arrays;
import java.util.Random;
/**
 * Write a description of class BACImplementation here.
 *
 * @author Kumalasari Sondjaja
 * @version March 24, 2020
 */
public class BACImplementation implements BACPlayer
{
    private String Lastname = "Sondjaja";
    private String Firstname = "Kumalasari";
    private String StudentID = "501002972";
    
    @Override public String getAuthor(){
        return Lastname+", "+Firstname;
    }
    
    @Override public String getStudentID(){
        return StudentID;
    }
    
    private static List<String> w = new ArrayList<String>();
    
    private static HashMap<Integer, ArrayList<String>> bylength = new HashMap<Integer, ArrayList<String>>();
    
    private static HashMap<Character, Integer> alphabet = new HashMap<Character, Integer>();
    char[] alpha = new char[]{'e','a','o','i','u','y','t','n','s','r','h','l','d','c','m','f','p','g','w','b','v','k','x','j','q','z'};
    
    private static HashMap<Character, Integer> palphas = new HashMap<Character, Integer>();
    
    private static ArrayList<Character> pwrongalphas = new ArrayList<Character>();
    
                                
    private static class LengthComparator implements Comparator<String>{
        public int compare(String a, String b){
            int l1 = a.length();
            int l2 = b.length();
            
            if(l2 != l1){return l2-l1;}
            return b.compareTo(a);
        }
    }
    
    private static class BACComparator implements Comparator<String>{
        public int compare(String a, String b){
            int t1 = 0;
            int t2 = 0;
                      
            for(int n=0; n<a.length(); n++){
                t1 = t1 + palphas.get(a.charAt(n));
            }
            
            for (int m=0; m<b.length(); m++){
                t2 = t2+ palphas.get(b.charAt(m));
            }
            
            if(t2 != t1){return t2-t1;}
            return b.compareTo(a);
        }
    }
    
    public void initializeWordList(List<String> words){
        this.w = new ArrayList<String>(words);

        Collections.sort(w, new LengthComparator());
  
        for(int i=0; i<alpha.length; i++){
            alphabet.put(alpha[i], 26-i);
        }
        
        palphas = new HashMap<Character, Integer>(alphabet);
        
        int i = 0;
        for(int l=15; l>=5; l--){
            
            ArrayList<String> wlength = new ArrayList<String>();
            while(i<w.size() && w.get(i).length()==l){
                //System.out.println(w.get(i));
                wlength.add(w.get(i));
                i++;
            }
            
            Collections.sort(wlength, new BACComparator());
            
            bylength.put(l, wlength);
            //System.out.println("l="+l+", has"+wlength.size()+"words "+bylength.get(l));
        }
    }
    
    private static ArrayList<String> possible = new ArrayList<String>();
    
    
    public String firstGuess(int n){
        //Reset lists & counters for every new secret word
        possible = new ArrayList<String>(bylength.get(n));
        palphas = new HashMap<Character, Integer>(alphabet);
        
        pwrongalphas.clear();


        //Guess all the most commonly used alphabets, starting with vowels
        String g = "";
        for(int i=0; i<n; i++){
            char a = alpha[i];
            g = g.concat(Character.toString(a));
        }
        
        
        //System.out.println("");
        //System.out.println("Guess no 1: "+g);
        return g;
    }
    
    public String noBullsOrCows(int n, List<String> guesses, int lastbulls, int lastcows, String lastguess){
        //If no Bulls or Cows in the last guess...
        
        ArrayList<String> wrongalphas = new ArrayList<String>();
        
        //Remove all letters from the last guess from list of alphabet
        for(int i=0; i<lastguess.length(); i++){
            char a = lastguess.charAt(i);
            wrongalphas.add(Character.toString(a));
            palphas.remove(a);
            pwrongalphas.add(a);
        }
                
        //System.out.println("Wrong letters: "+wrongalphas);
                
        //Then remove all possible words that contain the wrong letters
        for(int j = possible.size()-1; j>=0; j--){
            String p = new String(possible.get(j));
            for(int k=0; k<wrongalphas.size(); k++){
                if(p.contains(wrongalphas.get(k))){
                     //System.out.println(p+" contains a wrong letter");
                     possible.remove(j);
                     break;
                }
            }
        }
                
        //System.out.println("Updated possible words:"+possible);
                
        //Get the new word at top of possible list
        String g = new String(possible.get(0));
        possible.remove(0);
        
        //System.out.println("");
        //System.out.println("Guess no "+(guesses.size()+1)+": "+g);
        return g;
    }
    
     
    
    public void removeCowWords(String lastguess){
        //Remove all words with letters in cow positions
        
        char[] c = lastguess.toCharArray();
        
        for(char a:c){
            palphas.remove(a);
            pwrongalphas.add(a);
        }
        
        for(int j=possible.size()-1; j>=0; j--){
            String p = new String(possible.get(j));
            for(int i=0; i<lastguess.length(); i++){
                if(c[i]==p.charAt(i)){
                    possible.remove(j);
                    break;
                }
            }
        }

    }
    
       
    public String oneMoreBulls(int n, String lastguess, List<String>guesses){
        
        for(int i=0; i<possible.size(); i++){
            int count = 0;
            String p = new String(possible.get(i));
            //System.out.print(p+ " ");
            for(int j=0; j<n; j++){
                if(lastguess.charAt(j)==p.charAt(j)){
                    count++;
                }
            }
            if(count==n-1){
                possible.remove(i);
                
                //System.out.println(p+" has "+count+" matches");
                //System.out.println("Guess no "+(guesses.size()+1)+": "+p);
                
                return p;
            }
            
        }
        
        
        String g = possible.get(0);
        possible.remove(0);
        
        return g;
    }
    
    public void findWordsWithLetters(int n, int lastbulls, int lastcows, String lastguess){
        //Find words with the same number of cows+bulls number of matching letters with last guess
        
        int correctnum = lastbulls+lastcows;
        //System.out.println("Find words with "+correctnum+" correct letters");
        
        HashMap<Character, Integer> guesschar = new HashMap<Character, Integer>();
        
        for(int i=0; i<n; i++){
            guesschar.put(lastguess.charAt(i), i);
        }
        
        //System.out.println("Letters in last guess: "+guesschar);
        //System.out.println("Possible words: "+ possible);
        
        for(int j=possible.size()-1; j>=0; j--){
            String w = possible.get(j);
            int countletters = 0;
            int countbulls = 0;
            
            
            for(int i=0; i<n; i++){
                char a = lastguess.charAt(i);
                char c = w.charAt(i);
                if(guesschar.containsKey(c)){
                    countletters++;
                }
                if(a==c){
                    countbulls++;
                }
            }
            
            if(countletters!=correctnum){
                possible.remove(j);
            }
            else if(countbulls!=lastbulls){
                possible.remove(j);
            }
        }
    }
       
    
    public String guess(int n, List<String> guesses, List<Integer> bulls, List<Integer> cows){
        if(n==15){
            possible = new ArrayList<String>(bylength.get(n));
            return possible.get(0);
        }
        
        if(n==14){
            possible = new ArrayList<String>(bylength.get(n));
            String g = new String(possible.get(0));
            possible.remove(0);
        
            return g;
        }
        
        else{
        
            if(guesses.size()==0){
                return firstGuess(n);
            }
            else{
                //Next guesses
                String lastguess = guesses.get(guesses.size()-1);
                int lastbulls = bulls.get(bulls.size()-1);
                int lastcows = cows.get(cows.size()-1);
                int lastcorr = lastbulls+lastcows;

                
                //System.out.println("Bulls="+lastbulls+", Cows="+lastcows);
                //System.out.println("Old possible words:"+possible);
                
                
                if(lastbulls==0 && lastcows==0){
                    return noBullsOrCows(n, guesses, lastbulls, lastcows, lastguess);
                }
                else if(lastbulls==0 && lastcows!=0){
                    removeCowWords(lastguess);
                    findWordsWithLetters(n, lastbulls, lastcows, lastguess);
                }
                else if(lastbulls==n-1){
                    //System.out.println("One more bulls");
                    return oneMoreBulls(n, lastguess, guesses);
                }
                else{findWordsWithLetters(n, lastbulls, lastcows, lastguess);}
            }
        
            //System.out.println("Old possible words:"+possible);
        
            String g = new String(possible.get(0));
            possible.remove(0);
        
            //System.out.println("");
            //System.out.println("Guess no "+(guesses.size()+1)+": "+g);
        
            return g;
        }
    }

}
