#!/bin/bash
java Sudoku $1 in
./walksat -out translated.txt toTranslate.txt
java Sudoku $1 out
