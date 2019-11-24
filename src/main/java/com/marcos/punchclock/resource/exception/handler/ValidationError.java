package com.marcos.punchclock.resource.exception.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class serializes a fieldErrorList 
 * 
 * @author Marcos André
 *
 */

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> fieldMessages = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer status, String msg, long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getFieldMessages() {
		return fieldMessages;
	}
	
	public void addFieldMessage(FieldMessage fieldMessage) {
		fieldMessages.add(fieldMessage);
	}
	
	
}
