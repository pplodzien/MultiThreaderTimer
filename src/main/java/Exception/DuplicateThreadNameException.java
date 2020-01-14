package Exception;

public class DuplicateThreadNameException extends Exception {


    public DuplicateThreadNameException(){
        super();
    }

    public DuplicateThreadNameException(String message){
        super(message);
    }
}
