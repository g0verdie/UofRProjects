#!/bin/bash
java Sudoku example-4.1.sdk
MiniSat_v1.14_linux toTranslate.txt translated.txt
java Sudoku example-4.1.sdk
