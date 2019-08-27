package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class LayerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LayerNotFoundException() {
		super("Layer not found!");
	}

	public LayerNotFoundException(String message) {
		super(message);
	}
}
