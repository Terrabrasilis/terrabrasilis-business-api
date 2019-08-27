package br.inpe.dpi.terrabrasilis.exception;

/**
 * 
 * @author jether
 *
 */
public class DatasourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatasourceNotFoundException() {
		super("Datasource not found!");
	}
	
	public DatasourceNotFoundException(String message) {
		super(message);
	}
}
