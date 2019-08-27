package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class DatasourceAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatasourceAlreadyExistsException(String message) {
		super(message);
	}
}
