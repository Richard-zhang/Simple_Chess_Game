/**
 * Created by R on 11/30/15.
 */
public enum Color {
    BLACK('B'), WHITE('W'), NONE('.');
    private char property;
    Color(char p) {
        property = p;
    }

    char getProperty(){
        return property;
    }
}


