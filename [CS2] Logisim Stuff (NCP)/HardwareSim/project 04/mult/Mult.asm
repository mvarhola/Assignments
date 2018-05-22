// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[3], respectively.)

// Put your code here.
	

	@R2
	M=0		//resets R2
	@R1
	D=M		//sets D register equal to value loaded into R1
	@i
	M=D		//sets decrementer to value stored in reg D
	@WOOP
	D;JLE	//checks if decrementer is <=0, if true, jumps to WOOP
	@R0
	D=M		//sets D register equal to value in R0
	@WOOP
	D;JLE	//checks if that value is <=0, if true, jumps to WOOP
(LOOP)
	@R0
	D=M		//Set Register D to value in R0(loaded by script)
	@R2
	M=D+M 	//Increases the value in Memory[R2] with value in reg D
	@i
	M=M-1	//decrement decrementer
	D=M		//set current value of D to decremented value
	@LOOP
	D;JGT	//While The value of the decrementer >0, loop
	
(WOOP)
	@WOOP
	0;JMP




