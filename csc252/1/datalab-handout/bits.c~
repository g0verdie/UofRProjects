/* 
 * CS:APP Data Lab 
 * 
 * name- Dan Scarafoni 
 * username- dscarafo 
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting an integer by more
     than the word size.

EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result = result + 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implent floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operators (! ~ & ^ | + << >>)
     that you are allowed to use for your implementation of the function. 
     The max operator count is checked by dlc. Note that '=' is not 
     counted; you may use as many of these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* 
 * bitAnd - x&y using only ~ and | 
 *   Example: bitAnd(6, 5) = 4
 *   Legal ops: ~ |
 *   Max ops: 8
 *   Rating: 1
 */
int bitAnd(int x, int y) {
//uses demorgan's laws to implement the & function
  return ~(~x | ~y);
}
/* 
 * byteSwap - swaps the nth byte and the mth byte
 *  Examples: byteSwap(0x12345678, 1, 3) = 0x56341278
 *            byteSwap(0xDEADBEEF, 0, 2) = 0xDEEFBEAD
 *  You may assume that 0 <= n <= 3, 0 <= m <= 3
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 25
 *  Rating: 2
 */
int byteSwap(int x, int n, int m) {
	/*breaks x's nth and mth byte apart into their own 
	* separate arrays, (removing them from x)
	then recombines the three with the locations swapped
	*/
	int n1 = x >> (n << 3);
	int m1 = x >> (m << 3);
	int flip1 = n1 & 0xFF;
	int flip2 = m1 & 0xFF;
	int diff = flip1 ^ flip2;
	int readjust1 = diff << (n << 3);
	int readjust2 = diff << (m << 3);
	int cmb = readjust1 | readjust2;
	int returned = x ^ cmb;
    return returned;
}
/* 
 * logicalShift - shift x to the right by n, using a logical shift
 *   Can assume that 0 <= n <= 31
 *   Examples: logicalShift(0x87654321,4) = 0x08765432
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3 
 */
int logicalShift(int x, int n) {
//shifts number all the right
//creates bit string that is all ones from right up to (but not including) leading one
//Xor's the two to get rid of padding ones in x
	int shift = 0x80 << 24;
	int times = (shift >> n) << 1;
	int flipped = ~times;
	x = (x >> n) & flipped;
	return x;
}
/*
 * bitParity - returns 1 if x contains an odd number of 0's
 *   Examples: bitParity(5) = 0, bitParity(7) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 4
 */
int bitParity(int x) {
//shifts bits to right progressively to simulate Xor'ing each individual pair of adjascent bits
  x = x ^ (x >> 16);
  x = x ^ (x >> 8);
  x = x ^ (x >> 4);
  x = x ^ (x >> 2);
  x = x ^ (x >> 1);
  x = x & 1;
  return x;
}
//ERROR?- THE CHECKER WANTS IT TO RETURN 0 ON NEGATIVE INPUT EVEN THOUGH ! RETURNS 1 ON NEGATIVES
/* 
 * logicalNeg - implement the ! operator, using all of 
 *              the legal operators except !
 *   Examples: logicalNeg(3) = 0, logicalNeg(0) = 1
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 4 
 */
int logicalNeg(int x) {
	//continually divides x, keeping track of the significant bits, until it reaches 0 or 1
	int c1 = x | (x >> 1);
	int c2 = c1 | (c1 >> 2);
	int c3 = c2 | (c2 >> 4);
	int c4 = c3 | (c3>> 8);
	int c5 = c4 | (c4 >> 16);
	int opp = c5 ^ 1; 
	int same = opp & 1; 
	return same;
}
/* 
 * tmin - return minimum two's complement integer 
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 4
 *   Rating: 1
 */
int tmin(void) {
//shifts 1 over to fill the first bit (thus making it the lowest negative number)
	int i = 1;
	return (i << 31);
}
/* 
 * sign - return 1 if positive, 0 if zero, and -1 if negative
 *  Examples: sign(130) = 1
 *            sign(-23) = -1
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 10
 *  Rating: 2
 */
int sign(int x) {
	//negates a number and shifts it all the way to the right, reducing it to 0 or 1 or -1
	int negx = ~x + 1;
	int shifted = (negx >> 31) & 1;	
	return (x >> 31) | shifted;
}
/* 
 * divpwr2 - Compute x/(2^n), for 0 <= n <= 30
 *  Round toward zero
 *   Examples: divpwr2(15,1) = 7, divpwr2(-33,4) = -2
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int divpwr2(int x, int n) {
//performs a division by two by shifting, rounds down for negative numbers
	int bias = (x >> 31) & ((1 << n) + ((1 << 31) >>31));
	return (x + bias) >> n;
}
/* 
 * negate - return -x 
 *   Example: negate(1) = -1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 5
 *   Rating: 2
 */
int negate(int x) {
//follows standard formula for negation
  return ~x + 1;
}
/* 
 * isPositive - return 1 if x > 0, return 0 otherwise 
 *   Example: isPositive(-1) = 0.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 3
 */
int isPositive(int x) {
//converts x to either straight zeros or nmaximum negative value
//shifts all the way right
//compare with !x
//numbers with leading one will result in positive number, positive ones will be 0
	int compare =  x & (1 << 31);
	int max = compare >> 31;
	int not = !x;
  return !(max | not);
}
/* 
 * isLessOrEqual - if x <= y  then return 1, else return 0 
 *   Example: isLessOrEqual(4,5) = 1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 24
 *   Rating: 3
 */
int isLessOrEqual(int x, int y) {
//find uncommon bits between x and y
//makes all bits less than most significant 1
//checks if uncommon is negative and isolates most significant bit
//finds negative inverse of x, and finds common bits with negative inverse of ~y
//& the above with most significant bit before, if x <= y the answer will be 0
	int max;
	int uncommon = x ^ y;
	uncommon = uncommon | uncommon >> 1;
	uncommon = uncommon | uncommon >> 2;
	uncommon = uncommon | uncommon >> 4;
	uncommon = uncommon | uncommon >> 8;
	uncommon = uncommon | uncommon >> 16;
	max = 1 << 31;
	
	uncommon = uncommon & (~(uncommon >> 1) | max);
	uncommon = uncommon & (x ^ (1<<31)) & (y ^ ((max>>31))^ max);
	return !uncommon;
}
/*
 * ilog2 - return floor(log base 2 of x), where x > 0
 *   Example: ilog2(16) = 4
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 90
 *   Rating: 4
 */
int ilog2(int x) {
//reduces x to the most significant bit
//divide and conquer again to find the bit places (in powers of two) of the logarithm
	int oneshift;
	int r;
	int u = x;
	int check0 = (0xAA)|(0xAA << 8)|(0xAA << 16)|(0xAA << 24); 
	int check1 = (0xCC)|(0xCC << 8)|(0xCC << 16)|(0xCC << 24);
	int check2 = (0xF0)|(0xF0 << 8)|(0xF0 << 16)|(0xF0 << 24); 
	int check3 = (0xFF << 8)|(0xFF << 24);
	int check4 = (0xFF << 16)|(0xFF << 24);
	
	u = u | u >> 1;
	u = u | u >> 2;
	u = u | u >> 4;
	u = u | u >> 8;
	u = u | u >> 16;
	//printf("all ones- %08x\n",u);

	oneshift = u >> 1;
	u = u ^ oneshift;
	//printf("largest bit- %08x\n",u);
	//printf("check0- %08x\n",check0);
	//printf("check1- %08x\n",check1);
	//printf("check2- %08x\n",check2);
	//printf("check3- %08x\n",check3);
	
//printf("check4- %08x\n",check4);
	
	r = 0;
	r = r | !!(u & check0);//1 at odds place, 0 otherwise
	r = r | (!!(u & check4) << 4); // 1 if in second half of number
	r = r | (!!(u & check3) << 3);//1 if in first or last fourth of number
	r = r | (!!(u & check2) << 2);//same but for 8th's
	r = r | (!!(u & check1) << 1);//same but for 16th's
	return r;
}
/* 
 * float_neg - Return bit-level equivalent of expression -f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 */
unsigned float_neg(unsigned uf) {
//converts the leading one to a zero  or to one to negate number
 return (uf ^ (1 << 31));
}
/* 
 * float_i2f - Return bit-level equivalent of expression (float) x
 *   Result is returned as unsigned int, but
 *   it is to be interpreted as the bit-level representation of a
 *   single-precision floating point values.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_i2f(int x) {
//gets sign bit, exponent, and mantisse as separate components
//uses positive version of number
//rounds up or down based on remainder, rounds towards even number in tie
	int negmax;
	int sign;
	int tracker;
	int lsb;
	int sigsLeft;
	int nsbshell;
	int remainder;
	int sbshell;
	int potadd;
	unsigned int sent;
	int log;
	int exp;
	unsigned int rawmant;
	unsigned int final;
	int newadd;
	int half;


	//get sign
	negmax = 0x80000000;
	sign = x & negmax;
	
	//flip to positive
	if(x < 0)
		x = (x * -1);
	if(x == 0)
		return 0;
	
	//rounding
	tracker = 1 << 30;
	//move on top of the leftmost bit
	while((x < tracker) && (tracker != 1))
		tracker >>= 1;

	if(tracker != 1)
	{	//move on top of first non sig
		tracker >>= 1;
		sigsLeft = 22;
		while(sigsLeft > 0 && tracker != 1)
		{
			tracker >>= 1;
			sigsLeft--;
		}
	}
	//printf("tracker on last sb- %08x\n",tracker);
	lsb = tracker;
	
	//the remaining digits
	//if the last sb is the last place then no remainder
	nsbshell = 0;
	if(tracker == 1)
		nsbshell = 0;
	else
	{
		tracker >>= 1;
		//tracker is now on first bit of remainder
		while(!(tracker & 1))
			tracker |= tracker>>1 ;
		nsbshell = tracker;
	
		//preserve remainder
		remainder  = x & nsbshell;
		//create shell for sb
		sbshell = ~nsbshell;
		x &= sbshell;
		
	
		//the potential for addition (if we round up, is in the lsb's place
		potadd = lsb;
		newadd = x + potadd;
		half = potadd / 2;
		//rounding up
		if(remainder >= half)
			if((potadd & x) || remainder - half)
				x = newadd;

	}
	
	//exp
	log = 0;
	//find log base
	sent = x;
	while(sent >>= 1)
		log += 1;
	
	//bias
	{
		exp = log + 127;
		exp <<= 23;
	}
	//printf("exp- %08x\n",exp);

	//mantisse
	rawmant = x;

	//shift left till leading one in last place
	while(!(rawmant & negmax) && rawmant)
		rawmant <<= 1;

	//shift over one more
	rawmant <<= 1;

	//shift into appropriate place
	rawmant >>= 9;

	//union
	final = sign | exp;
	final |= rawmant;
	return final;
}
/* 
 * float_twice - Return bit-level equivalent of expression 2*f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representation of
 *   single-precision floating point values.
 *   When argument is NaN, return argument
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_twice(unsigned uf) {
//shifts exponent over one, or mantisse over 1 if exponent = 0
	int negmax = 1 << 31;//0x80000000;
	unsigned manthole = 0x007FFFFF;
	unsigned manthold = uf & manthole;
	unsigned exphold = uf & ~(manthole | negmax);
	unsigned signhold = uf & negmax; 
	unsigned reunite;

	
	if(!manthold && exphold == 0x7f800000)
	{
		return uf;
	}
	
	if(uf == 0 || uf == 0x80000000)
	{
		return uf;
	}
	if(!exphold)
	{
		reunite = signhold | exphold;
		manthold <<= 1;
		reunite = reunite | manthold;
		return reunite;
	}
	reunite = signhold | exphold;
	reunite = reunite | manthold;
	return	reunite + (1 << 23);
	
	return 2;
}
