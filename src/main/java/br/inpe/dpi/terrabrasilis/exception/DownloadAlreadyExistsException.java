package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class DownloadAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DownloadAlreadyExistsException(String message) {
		super(message);
	}
}
