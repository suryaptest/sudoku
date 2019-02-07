package com.ubs.code;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ubs.code.exceptions.SudokuMatrixException;

public class SudokuCSVParser {

	private static final String DELIMITER = ",";
	public static final int DIMENSION = 9;

	public static int[][] parseToSudokuMatrix(String[] fileName) throws SudokuMatrixException {

		int[][] sudokuMatrix = new int[DIMENSION][DIMENSION];

		if (fileName.length < 1) {
			throw new SudokuMatrixException("1: No filename provided.");
		}

		File file = new File(fileName[0]);
		if (!file.exists()) {
			throw new SudokuMatrixException("2: File doesn't exist.");
		}

		try (DataInputStream dataIn = new DataInputStream(new FileInputStream(fileName[0]))) {

			BufferedReader br = new BufferedReader(new InputStreamReader(dataIn));
			String line;

			List<String> rowList = new ArrayList<String>();

			while ((line = br.readLine()) != null) {
				rowList.add(line);
			}

			if (rowList.size() != DIMENSION ) {
				throw new SudokuMatrixException("3: Not a 9x9 Sudoku!!!");
			}

			for (int i = 0; i < DIMENSION; i++) {
				String row = rowList.get(i);
				String[] cols = row.split(DELIMITER);
				if (cols.length != DIMENSION) {
					throw new SudokuMatrixException("3: Not a 9x9 Sudoku!!!");
				}
				for (int j = 0; j < cols.length; j++) {
					sudokuMatrix[i][j] = Integer.parseInt(cols[j]);
				}
			}

		} catch (SudokuMatrixException e) {
			throw e;
		} catch (Exception e) {
			throw new SudokuMatrixException("4: Error while parsing the sudoku file! ");
		}

		return sudokuMatrix;
	}

}
