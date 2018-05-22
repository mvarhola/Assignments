// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Put your code here.

//Memory Addressess: 
//Keyboard:24576
//Screen First Pixel: 16384
//Screen Last:24575

//RAM[16384 + r·32 + c/16]
//16 bit word at address of 16384 represents first 16 pixels
//Must set each word to (1111111111111111) (-1 in base10)



(FILL)

	@24576
	D = M
	@UNFILL
	D;JEQ	//jump to UNFILL if no key is pressed
	@24575
	D = M
	@UNFILL
	D;JLT	//repeat loop while last pixel isn't filled (prevents overflow error)
	@i
	D = M	//set D register to value in memory of counter i, which is 0
	@SCREEN
	D=A+D	//set D register to to screen's first pixel address and the counter offset (16384 + offset)
	A=D		//set the address to the value of the D register
	M=-1	//fill in that address with -1
	@i
	M=M+1	//increment counter
	@FILL
	0;JMP	//Restart loop
	
(UNFILL)

	@24576
	D = M
	@UNFILL
	D;JNE	//jump to FILL if key is pressed
	@i
	D = M	//set D register to value in memory of counter i, which is 0
	@SCREEN
	D=A+D	//set D register to to screen's first pixel address and the counter offset (16384 + offset)
	A=D		//set the address to the value of the D register
	M=0		//fill in that address with 0
	@i
	M=M-1	//decrement counter
	@FILL
	0;JMP	//Restart loop
	