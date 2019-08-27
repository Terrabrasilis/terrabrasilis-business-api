package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class LayerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LayerAlreadyExistsException(String message) {
		super(message);
	}
}
