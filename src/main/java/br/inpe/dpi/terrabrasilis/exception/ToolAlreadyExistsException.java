package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class ToolAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ToolAlreadyExistsException(String message) {
		super(message);
	}
}
