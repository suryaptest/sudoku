package com.ubs.code;

import com.ubs.code.exceptions.SudokuMatrixException;

/**
 * Application class of Sudoku Validator
 *
 */
public class SudokuValidatorApp {

	public static void main(String[] args) {

		try {
			int[][] matrix = SudokuCSVParser.parseToSudokuMatrix(args);
			SolutionChecker solution = new SolutionChecker(matrix);
			if (solution.checkCorrectness()) {
				System.out.println("0");
			} 
		} catch (SudokuMatrixException e) {
			System.out.println(e.getMessage());
		}

	}

}
