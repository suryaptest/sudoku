package com.ubs.test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ubs.code.SolutionChecker;
import com.ubs.code.exceptions.SudokuMatrixException;

public class SolutionCheckerTest {

	int[][] correctSolution = new int[][] { { 8, 7, 6, 4, 2, 5, 9, 3, 1 }, { 4, 5, 9, 7, 3, 1, 2, 6, 8 },
			{ 3, 2, 1, 8, 9, 6, 5, 7, 4 }, { 1, 4, 2, 5, 6, 8, 7, 9, 3 }, { 9, 6, 3, 2, 4, 7, 1, 8, 5 },
			{ 7, 8, 5, 9, 1, 3, 6, 4, 2 }, { 6, 3, 4, 1, 7, 2, 8, 5, 9 }, { 2, 9, 8, 6, 5, 4, 3, 1, 7 },
			{ 5, 1, 7, 3, 8, 9, 4, 2, 6 } };

	int[][] wrongSolution = new int[][] { { 8, 7, 6, 4, 2, 5, 9, 3, 1 }, { 4, 5, 9, 7, 3, 1, 2, 6, 8 },
			{ 3, 2, 1, 8, 9, 6, 5, 7, 4 }, { 1, 4, 2, 5, 6, 8, 7, 9, 3 }, { 9, 6, 3, 2, 4, 7, 1, 8, 5 },
			{ 7, 8, 5, 9, 1, 4, 6, 4, 2 }, { 6, 3, 4, 1, 7, 2, 8, 5, 9 }, { 2, 9, 8, 6, 5, 4, 3, 1, 7 },
			{ 5, 1, 7, 3, 8, 9, 4, 2, 6 } };

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testNullArray() throws SudokuMatrixException {
		expectedEx.expect(SudokuMatrixException.class);
		expectedEx.expectMessage("5: Validator array not initialized.");
		new SolutionChecker(null).checkCorrectness();
	}
	
	@Test
	public void testCorrectSolution() throws SudokuMatrixException {
		assertEquals(true, new SolutionChecker(correctSolution).checkCorrectness());
	}
	
	@Test
	public void testWrongSolution() throws SudokuMatrixException {
		expectedEx.expect(SudokuMatrixException.class);
		expectedEx.expectMessage("6: Row 5 check failed.");
		new SolutionChecker(wrongSolution).checkCorrectness();
	}

}
