public class Enigma{

    private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"};


    private Rotor rotors[];
        
    public Enigma(int id1, int id2, int id3, String start){

        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));
        
    }


    public String decrypt(String message){        
        String decrypted = "";

        for (char c : message.toCharArray()) {
            int Outer = rotors[2].indexOf(c); // Find where the char is in the outer rotor
            char Middle = rotors[1].charAt(Outer); // Find the char corresponding to the outer index
            int Outer2 = rotors[2].indexOf(Middle); // Find the index for the char of the middle rotor

            decrypted = decrypted + rotors[0].charAt(Outer2); // Add the char at the inner's index

            rotate(); // Rotate for each character
        }

        return decrypted;
    }


    
    public String encrypt(String message){
        String encrypted = "";

        for (char c : message.toCharArray()) {
            int Inner = rotors[0].indexOf(c); // Find where the char is in inner rotor
            char Outer = rotors[2].charAt(Inner); // Find the char corresponding to the inner index
            int Middle = rotors[1].indexOf(Outer); // Find the index for the char of the outer rotor

            encrypted = encrypted + rotors[2].charAt(Middle); // Add the char at the middle's index

            rotate(); // Rotate for each character
        }

        return encrypted;
    }

    
    private void rotate(){
        if(rotors[0].rotate()){
            if(rotors[1].rotate()){
                rotors[2].rotate();
            }
        }
    }
    
}
