package com.ubs.test;

import static org.junit.Assert.assertArrayEquals;

import java.net.URL;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ubs.code.SudokuCSVParser;
import com.ubs.code.exceptions.SudokuMatrixException;

public class SudokuCSVParserTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testEmptyFile() throws SudokuMatrixException {
		expectedEx.expect(SudokuMatrixException.class);
		expectedEx.expectMessage("1: No filename provided.");
		SudokuCSVParser.parseToSudokuMatrix(new String[] {});
	}

	@Test
	public void testFileDoesnotExist() throws SudokuMatrixException {
		expectedEx.expect(SudokuMatrixException.class);
		expectedEx.expectMessage("2: File doesn't exist.");
		SudokuCSVParser.parseToSudokuMatrix(new String[] { "" });
	}

	@Test
	public void testParseExceptionFile() throws SudokuMatrixException {
		expectedEx.expect(SudokuMatrixException.class);
		expectedEx.expectMessage("4: Error while parsing the sudoku file! ");
		SudokuCSVParser.parseToSudokuMatrix(new String[] { getFilePath("sdk3_broken.txt") });
	}

	@Test
	public void testInvalidFile() throws SudokuMatrixException {
		expectedEx.expect(SudokuMatrixException.class);
		expectedEx.expectMessage("3: Not a 9x9 Sudoku!!!");
		SudokuCSVParser.parseToSudokuMatrix(new String[] { getFilePath("sdk3_broken2.txt") });
	}

	@Test
	public void testValidFile() throws SudokuMatrixException {
		int[][] expected = new int[][] { { 8, 7, 6, 4, 2, 5, 9, 3, 1 }, { 4, 5, 9, 7, 3, 1, 2, 6, 8 },
				{ 3, 2, 1, 8, 9, 6, 5, 7, 4 }, { 1, 4, 2, 5, 6, 8, 7, 9, 3 }, { 9, 6, 3, 2, 4, 7, 1, 8, 5 },
				{ 7, 8, 5, 9, 1, 3, 6, 4, 2 }, { 6, 3, 4, 1, 7, 2, 8, 5, 9 }, { 2, 9, 8, 6, 5, 4, 3, 1, 7 },
				{ 5, 1, 7, 3, 8, 9, 4, 2, 6 } };

		assertArrayEquals(expected, SudokuCSVParser.parseToSudokuMatrix(new String[] { getFilePath("sdk1.txt") }));
	}

	private String getFilePath(String filename) {
		URL url = this.getClass().getClassLoader().getResource(filename);
		return url.getPath();
	}

}
