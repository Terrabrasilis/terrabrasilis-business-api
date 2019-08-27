package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class VisionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VisionNotFoundException() {
		super("Vision not found!");
	}

	public VisionNotFoundException(String message) {
		super(message);
	}
}
