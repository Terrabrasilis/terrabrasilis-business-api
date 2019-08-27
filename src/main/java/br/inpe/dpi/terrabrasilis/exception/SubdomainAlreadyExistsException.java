package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class SubdomainAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SubdomainAlreadyExistsException(String message) {
		super(message);
	}
}
