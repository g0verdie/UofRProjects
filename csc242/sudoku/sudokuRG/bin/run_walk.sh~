#!/bin/bash
java Sudoku $1 in
./solvers/walksat -out solvers/translated.txt solvers/toTranslate.txt
java Sudoku $1 out
