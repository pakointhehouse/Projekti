package hr.java.vjezbe.iznimke;

public class NemoguceOdreditiProsjekStudentaException extends Exception{
	private static final long serialVersionUID = 2711724378809456882L;
	
	public NemoguceOdreditiProsjekStudentaException() {
		super("Dogodila se pogre�ka u radu programa");
	}
	
	
	public  NemoguceOdreditiProsjekStudentaException(String message) {
		super(message);
	}
	
	public NemoguceOdreditiProsjekStudentaException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public NemoguceOdreditiProsjekStudentaException(Throwable cause) {
		super(cause);
	}
	
}
