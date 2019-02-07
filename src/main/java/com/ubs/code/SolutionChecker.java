package com.ubs.code;

import java.util.Arrays;

import com.ubs.code.exceptions.SudokuMatrixException;

public class SolutionChecker {

	private final int[][] solutionMatrix;

	public SolutionChecker(int[][] solutionMatrix) {
		this.solutionMatrix = solutionMatrix;
	}

	public boolean checkCorrectness() throws SudokuMatrixException {

		if (solutionMatrix == null) {
			throw new SudokuMatrixException("5: Validator array not initialized.");
		}

		for (int row = 0; row < SudokuCSVParser.DIMENSION; row++) {  //check rows
			if (!isValid(solutionMatrix[row])) {
				throw new SudokuMatrixException("6: Row "+row+" check failed.");
			}
		}

		for (int j = 0; j < SudokuCSVParser.DIMENSION; j++) {			//check columns
			int[] col = new int[SudokuCSVParser.DIMENSION];
			for (int i = 0; i < SudokuCSVParser.DIMENSION; i++) {
				col[i] = solutionMatrix[i][j];
			}
			if (!isValid(col)) {
				throw new SudokuMatrixException("7: Column "+j+" check failed.");
			}
		}

		for (int row = 0; row < SudokuCSVParser.DIMENSION; row += 3) {			//check subgrids
			for (int col = 0; col < SudokuCSVParser.DIMENSION; col += 3) {
				int k = 0;
				int[] subGrid = new int[SudokuCSVParser.DIMENSION];
				for (int pos = 0; pos < SudokuCSVParser.DIMENSION; pos++) {
					subGrid[k++] = solutionMatrix[row + pos % 3][col + pos / 3];
				}
				if (!isValid(subGrid)) {
					throw new SudokuMatrixException("8: Subgrid check failed.");
				}
			}
		}
		return true;
	}

	/**
	 * @param part A single dimension or grid from Sudoku matrix
	 * @return true if it is a valid solution
	 */
	private boolean isValid(int[] part) {

		int[] copy = Arrays.copyOf(part, part.length);
		Arrays.sort(copy);
		for (int i = 0; i < part.length; i++) {
			if (copy[i] != i + 1) {
				return false;
			}
		}
		return true;
	}

}