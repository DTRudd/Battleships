package battleships;

public class AttackNotPermittedException extends Exception {

	static final long serialVersionUID = 32523634L;
	public AttackNotPermittedException() {
		super();
	}

	public AttackNotPermittedException(String message) {
		super(message);
	}

	public AttackNotPermittedException(Throwable cause) {
		super(cause);
	}

	public AttackNotPermittedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AttackNotPermittedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
