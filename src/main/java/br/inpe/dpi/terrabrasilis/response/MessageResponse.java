package br.inpe.dpi.terrabrasilis.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

/**
 * 
 * @author jether
 *
 */
public class MessageResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	
	public MessageResponse(HttpStatus status) {
		super();
		this.code = status.value();
		this.message = status.getReasonPhrase();
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
