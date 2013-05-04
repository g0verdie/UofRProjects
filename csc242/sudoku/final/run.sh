#!/bin/bash
java Sudoku wikipedia-examplle.sdk in
./minisat toTranslate.txt translated.txt
java Sudoku wikipedia-example.sdk out
