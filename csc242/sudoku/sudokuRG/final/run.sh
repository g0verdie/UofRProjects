#!/bin/bash
java Sudoku $1 in
./minisat toTranslate.txt translated.txt
java Sudoku $1 out
