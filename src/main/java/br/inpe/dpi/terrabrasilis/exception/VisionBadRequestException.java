package br.inpe.dpi.terrabrasilis.exception;

/**
 * @author
 */
public final class VisionBadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VisionBadRequestException() {
        super("The Vision ID is necessary to continue...");
    }
}