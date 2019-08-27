package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class SubdomainNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SubdomainNotFoundException(String message) {
		super(message);
	}
}
