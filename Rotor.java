public class Rotor {

    
    private String rotorValues;
    private char startChar;
        
    public Rotor(String v, char c){
        this.rotorValues = new String(v);
        this.startChar = c;
        
        while(!this.rotate());
            
    }
    
    public boolean rotate(){
        // Rotate by moving the last char to the front and putting the rest of the string after it
        rotorValues = rotorValues.charAt(rotorValues.length() - 1) + rotorValues.substring(0, rotorValues.length() - 1);
               
        // True if full rotation
        return rotorValues.charAt(0) == startChar;
    }
    

    public int indexOf(char c){
        for (int i=0; i<rotorValues.length(); i++) {
            if (charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public char charAt(int idx){
        if (idx < 0 || idx >= rotorValues.length()) {
            return 'z';
        }
        
        char rotorArray[] = rotorValues.toCharArray();
        return rotorArray[idx];

    }
}
    
