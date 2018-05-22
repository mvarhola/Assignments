#assemble.py v 0.01 alpha
#The nand2tetris Chapter 6 .asm Assembler
#usage: python assemble.py <input file> <output file>
#Example: python assemble.py herp.asm derp.hack
########################################################################################################################
#	,--.   ,--.               ,--.                                ,--.   ,--.             ,--.            ,--.         #
#	|   `.'   | ,--,--.,--.--.|  |,-. ,--. ,--.,--,--.,--,--,      \  `.'  /,--,--.,--.--.|  ,---.  ,---. |  | ,--,--. #
#	|  |'.'|  |' ,-.  ||  .--'|     /  \  '  /' ,-.  ||      \      \     /' ,-.  ||  .--'|  .-.  || .-. ||  |' ,-.  | #
#	|  |   |  |\ '-'  ||  |   |  \  \   \   ' \ '-'  ||  ||  |       \   / \ '-'  ||  |   |  | |  |' '-' '|  |\ '-'  | #
#	`--'   `--' `--`--'`--'   `--'`--'.-'  /   `--`--'`--''--'        `-'   `--`--'`--'   `--' `--' `---' `--' `--`--' #
#									  `---'     										ALL YOUR BASE ARE BELONG TO US #
########################################################################################################################

import sys, os

compTable = {'0':'101010','1':'111111','-1':'111010','D':'001100',
			'A':'110000','!D':'001101','!A':'110001','-D':'001111',
			'-A':'110011','D+1':'011111','A+1':'110111','D-1':'001110',
			'A-1':'110010','D+A':'000010','D-A':'010011','A-D':'000111',
			'D&A':'000000','D|A':'010101','M':'110000','!M':'110001',
			'-M':'110011','M+1':'110111','M-1':'110010','D+M':'000010',
			'D-M':'010011','M-D':'000111','D&M':'000000','D|M':'010101'}
				
destTable = {'null':'000','M':'001','D':'010','MD':'011',
			'A':'100','AM':'101','AD':'110','AMD':'111'}
				
jumpTable = {'null':'000','JGT':'001','JEQ':'010','JGE':'011',
			'JLT':'100','JNE':'101','JLE':'110','JMP':'111'}
				
symbolTable = {'SP':'0','LCL':'1','ARG':'2','THIS':'3',
				'THAT':'4','R0':'0','R1':'1','R2':'2',
				'R3':'3','R4':'4','R5':'5','R6':'6',
				'R7':'7','R8':'8','R9':'9','R10':'10',
				'R11':'11','R12':'12','R13':'13','R14':'14',
				'R15':'15','SCREEN':'16384','KBD':'24576'}
	
def placeLabels(x):
	readfile = open(x,'r')
	i = 0
	for line in readfile:
		if commandType(line) !='L_COMMAND':
			i+=1
		if commandType(line) =='L_COMMAND':
			label = line[line.find("(")+1:line.find(")")]
			line.strip()
			symbolTable[label]=i
	readfile.close()

def checkem(line):
	if commandType(line) =='A_COMMAND':
		symbolThing = line.split('@')[1]
		try:
		    symbolThing = int(symbolThing)
		except ValueError:
		    symbolThing = symbolThing.rstrip('\n')

		if type(symbolThing) is int:
			return '0'+str(bin(symbolThing)[2:].zfill(15))
		else:
			if symbolThing in symbolTable:
				potato = symbolTable[symbolThing]
				return '0'+bin(int(potato))[2:].zfill(15)
			elif symbolThing not in symbolTable:
				pootPlace = 16
				while str(pootPlace) in symbolTable.values():
					pootPlace += 1
				
				symbolTable[symbolThing] = str(pootPlace)
				return '0'+bin(int(pootPlace))[2:].zfill(15)
			
	elif commandType(line) =='L_COMMAND':
		pass
	elif commandType(line) =='C_COMMAND':
		comp = ''
		dest = ''
		jmp =''

		if '=' not in line:
			dest = 'null'
			comp = line.split(';')[0]
		else:
			dest = line.split('=')[0]
			comp = line.split('=')[1]
	
		if ';' not in line:
			jmp = 'null'
		else:
			jmp = line.split(";")[1]
		if 'M' in comp:
			aStatus = '1'
		else:
			aStatus = '0'
			
		return '111'+aStatus+compTable[comp.rstrip('\n')]+destTable[dest.rstrip('\n')]+jumpTable[jmp.rstrip('\n')]

def removeComments(readFile):
	file = open(readFile)
	wfile = open(tempFile,'w') #makes new file/truncates file if exists
	for line in file:
		if line.startswith('/'):
			line.strip()
		elif not line.strip(): #skips the line if it's empty
			continue
		else:
			wfile.write(line.split('/')[0].strip()+'\n')
	wfile.close()
	file.close()
	
def commandType(derp):
	if derp.startswith('@'):
		return 'A_COMMAND'
	elif derp.startswith('('):
		return 'L_COMMAND'
	else:
		return 'C_COMMAND'	
	
def Assemble(temporaryfile):
	readfile = open(temporaryfile,'r')
	writefile = open(writeFile,'w')
#	wfile = open(writeFile,'w')
	for line in readfile:
		potato = str(checkem(line))
		if potato == "None":
			continue
		else:
 			writefile.write(potato+'\n')
	readfile.close()
	writefile.close()
		
tempFile = 'notavirus.jpg.bat.exe'
readFile = sys.argv[1]
writeFile = sys.argv[2]

def main():	
	print "\tRemoving comments..."
	removeComments(readFile)
	print "\tPlacing labels into symbol table..."
	placeLabels(tempFile)
	print "\tAssembling..."
	Assemble(tempFile)
	print "\tRemoving temporary file..."
	os.remove(tempFile)
	print "\tAll Done!"

main()		