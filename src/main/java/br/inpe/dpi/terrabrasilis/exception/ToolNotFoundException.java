package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class ToolNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ToolNotFoundException() {
		super("Tool not found!");
	}
	
	public ToolNotFoundException(String message) {
		super(message);
	}
}
