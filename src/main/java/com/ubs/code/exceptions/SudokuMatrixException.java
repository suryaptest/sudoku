package com.ubs.code.exceptions;

/**
 * Custom exception for Validator app 
 * We can further segregate this into file exception and solutioninvalid exception
 *
 */
public class SudokuMatrixException extends Exception {

	
	private static final long serialVersionUID = -5978268000667646733L;
	
	public SudokuMatrixException(String message) {
		super(message);
	}

}
