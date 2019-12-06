package farm.exception;

public class FarmException extends RuntimeException {
	   
	   public FarmException(String message) {

	      super(message);
	   }

	   public FarmException(Throwable cause) {
	      super(cause);
	   }

	   public FarmException(String message, Throwable throwable) {
	      super(message, throwable);
	   }
	}
