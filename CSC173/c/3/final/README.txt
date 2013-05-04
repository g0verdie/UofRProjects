Dan Scarafoni
dscarafo@u.rochester.edu
csc 173
project 3 + 4

requirements for input file- please enter all well-formed equations, separated by ';'

HOW TO RUN
	-type "make" (without quotes) in the folder
	-type "runtest"

scanner.h
	-enum for token_class
	-struct token
		token_class
		location_t
		length (in chars)
	-prototypes
		void scan(location_t * loc, token_t * tok)
	
reader.h
	-struct line_t- a line of characters from input
		char * data
	    int line_num
	    int length
	    struct line
	-struct location_t- location of character on a line
		line_t *line
    	int column
	-prototypes
		void initialize_reader(FILE* FP)
		void set_to_beginning(location_t *loc)
		int get_character(location_t *loc)
		void finalize_reader()

parser.h
	-prototypes
		void parse()
		
char_classes.h
	-enum char_class
		- classes for characters, a-c, b-z, 0-3, 4-9, quotes, etc...
		
keywords.h
	-includes scanner.k
	-prototypes
		void filter_keywords(token_t * tok)
		
char_classes.c
	-which character_class are each ascii value
	
keywords.c
	-struct keyword_t
		length, class, name
	-array of java keywords
	-functions
		int compar (const void * key, const void * elem)
			used by below
		void filter_keywords(token_t * tok)
			looks up token in table, if found, changes token_class accordingly	

parser.c
	-functions
		static void newline()
			prints newline
		static void put_token()
			prints token
		static void get_token()
			get next token from scanner
		static void parse_error()
			print error
		static void match(token_class tc)
			parser expects a certain class token, check this and print
		void parse()
			call scanner, parse token, print
			parse imports, other stuff
		static void parse_compilation_unit()
			intermident step in parsing (recurse until it's over)
		***need parsing tree to describe the rest***

reader.c
	functions
		void initialize_reader(FILE* FP)
			makes a linked list of lines long enough for the file, scan in text
		void set_to_beginning(location_t *loc)
			go to front of list
		int get_character(location_t *loc)
			get character
		void finalize_reader()
			deconstruct reader

scanner.c
	-enum state
		what character (or lack thereof) the scanner has right now
	functions
		static void print_location (token_t *tok)
			prints location of token
		void scan(location_t * loc, token_t * tok)
			while not done
				switch state
					start-> get char
					change state
				other
					get char, or return char

Sample Output

1*(1+2) /        2.12345 + 34;

1+1+

2+2;

.25 + 3;

smnt_list
smnt
E
T
F
1.000000
Ttail
VALUE = 1.000000
 mult
F
( E
T
F
1.000000
 Ttail
VALUE = 1.000000
null
Etail
Value 1.000000
 add
T
F
2.000000
 Ttail
VALUE = 2.000000
null
Etail
Value 3.000000
num
 )Ttail
VALUE = 3.000000
 div
F
2.123450
 Ttail
VALUE = 2.123450
null
Etail
Value 1.412795
 add
T
F
34.000000
 Ttail
VALUE = 34.000000
null
Etail
Value 35.412796
num
35.412796
 smnt_list
smnt
E
T
F
1.000000
 Ttail
VALUE = 1.000000
null
Etail
Value 1.000000
 add
T
F
1.000000
 Ttail
VALUE = 1.000000
null
Etail
Value 2.000000
 add
T
F
2.000000
 Ttail
VALUE = 2.000000
null
Etail
Value 4.000000
 add
T
F
2.000000
 Ttail
VALUE = 2.000000
null
Etail
Value 6.000000
num
6.000000
 smnt_list
smnt
E
T
F
0.250000
 Ttail
VALUE = 0.250000
null
Etail
Value 0.250000
 add
T
F
3.000000
 Ttail
VALUE = 3.000000
null
Etail
Value 3.250000
num
3.250000
 smnt_list
end
			
