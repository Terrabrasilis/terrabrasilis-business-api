package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class VisionAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VisionAlreadyExistsException(String message) {
		super(message);
	}
}
