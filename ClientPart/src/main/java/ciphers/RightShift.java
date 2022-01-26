package ciphers;

public class RightShift implements Ciphers {

    public void rightShift() {
    }
    public String cipher(String input){
        int[] text = new int[input.length()];

        for(int i = 0; i< input.length(); i++){
            text[i] = input.charAt(i)+1;
        }
        return new String (text,0,text.length);
    }

    public String decipher(String input) {
        int[] text = new int[input.length()];

        for(int i = 0; i < input.length(); ++i) {
            text[i] = input.charAt(i) - 1;
        }
        return new String(text, 0, text.length);
    }

    public String toString(){return "RightShift";}

}
