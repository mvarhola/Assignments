################################################
#                      _                      
#  _ __ ___   __ _ _ __| | ___   _  __ _ _ __  
# | '_ ` _ \ / _` | '__| |/ / | | |/ _` | '_ \ 
# | | | | | | (_| | |  |   <| |_| | (_| | | | |
# |_| |_| |_|\__,_|_|  |_|\_\\__, |\__,_|_| |_|
#                  _         |___/             
# __   ____ _ _ __| |__   ___ | | __ _         
# \ \ / / _` | '__| '_ \ / _ \| |/ _` |        
#  \ V / (_| | |  | | | | (_) | | (_| |        
#   \_/ \__,_|_|  |_| |_|\___/|_|\__,_|
#
################################################

#Usage:
#pyton translator.py SomeFile.vm

import sys, os

def loadFiles():
	arguments = []
	for arg in sys.argv:
		arguments.append(arg)
	arguments = arguments [1:]
	for i in arguments:
		return i

newFileName = loadFiles().split('.')[0]+'.asm'

eqCount = gtCount = ltCount = 0


bootstrap = '''@261
				D=A
				@SP
				M=D
			'''.replace('\t','')

#Advance SP after push:
pushAdvance = """@SP
				A=M
				M=D
				@SP
				M=M+1
				""".replace('\t','')

popAdvance = '''@SP
				M=M-1
				@SP
				A=M
				D=M
				@R13
				A=M
				M=D
				'''.replace('\t','')
				
pop2Advance = '''@SP
				M=M-1
				@SP
				A=M
				D=M'''.replace('\t','')

#begin push commands
pConstant = "D=A\n"

pLocal = """D=A
			@LCL
			A=M+D
			D=M
			""".replace('\t','')

pArgument = """D=A
				@ARG
				A=M+D
				D=M
				""".replace('\t','')
			
pThis = """D=A
			@THIS
			A=M+D
			D=M
			""".replace('\t','')
		
pThat = """D=A
		@THAT
		A=M+D
		D=M
		""".replace('\t','')
		
pTemp = pPointer = pStatic = "D=M\n"
#end push commands

#begin pop commands

popLocal = '''D=A
			@LCL
			D=M+D
			@13
			M=D
			'''.replace('\t','')
			
popArgument = '''D=A
				@ARG
				D=M+D
				@13
				M=D
				'''.replace('\t','')
				
popThis = '''D=A
				@THIS
				D=M+D
				@13
				M=D
				'''.replace('\t','')
				
popThat = '''D=A
				@THAT
				D=M+D
				@13
				M=D
				'''.replace('\t','')
				
popTemp = popPointer = popStatic = "M=D\n"

#end pop commands

#begin arithmetic commands

arAdd = '''@SP
			M=M-1
			A=M
			D=M
			@SP
			M=M-1
			A=M
			M=M+D
			@SP
			M=M+1
			'''.replace('\t','')
			
arSub = '''@SP
			M=M-1
			@SP
			A=M
			D=M
			@14
			M=D
			@SP
			M=M-1
			@SP
			A=M
			D=M
			@14
			D=D-M
			@SP
			A=M
			M=D
			@SP
			M=M+1
			'''.replace('\t','')
			
arNeg = '''@SP
			M=M-1
			A=M
			M=-M
			@SP
			M=M+1
			'''.replace('\t','')
			
arEq = '''@SP
			M = M - 1
			@SP
			A = M
			D = M
			@14
			M = D
			@SP
			M = M - 1
			@SP
			A = M
			D = M
			@14
			D = D - M
			@SP
			A = M
			M = D
			@SP
			A = M
			D = M
			'''.replace('\t','')+\
			'@EQ_T_'+str(eqCount)+'\n'+\
			'D;JEQ'+'\n'+\
			'@EQ_F_'+str(eqCount)+'\n'+\
			'D;JNE'+'\n'+\
			'(EQ_T_'+str(eqCount)+')'+'\n'+\
			'@SP'+'\n'+\
			'A=M'+'\n'+\
			'M=-1'+'\n'+\
			'@SP'+'\n'+\
			'M=M+1'+'\n'+\
			'@EQ_E_'+str(eqCount)+'\n'+\
			'0;JMP'+'\n'+\
			'(EQ_F_'+str(eqCount)+')'+'\n'+\
			'''@SP
			A=M
			M=0
			@SP
			M=M+1'''.replace('\t','')+'\n'+\
			'(EQ_E_'+str(eqCount)+')'+'\n'


arGt = '''@SP
		M = M - 1
		@SP
		A = M
		D = M
		@14
		M = D
		@SP
		M = M - 1
		@SP
		A = M
		D = M
		@14
		D = D - M
		@SP
		A = M
		M = D
		@SP
		A = M
		D = M
		'''.replace('\t','')+\
		'@GT_T_'+str(gtCount)+'\n'+\
		'D;JGT'+'\n'+\
		'@GT_F_'+str(gtCount)+'\n'+\
		'D;JLE'+'\n'+\
		'(GT_T_'+str(gtCount)+')'+'\n'+\
		'@SP'+'\n'+\
		'A=M'+'\n'+\
		'M=-1'+'\n'+\
		'@SP'+'\n'+\
		'M=M+1'+'\n'+\
		'@GT_E_'+str(gtCount)+'\n'+\
		'0;JMP'+'\n'+\
		'(GT_F_'+str(gtCount)+')'+'\n'+\
		'''@SP
		A=M
		M=0
		@SP
		M=M+1'''.replace('\t','')+'\n'+\
		'(GT_E_'+str(gtCount)+')'+'\n'

arLt = '''@SP
		M = M - 1
		@SP
		A = M
		D = M
		@14
		M = D
		@SP
		M = M - 1
		@SP
		A = M
		D = M
		@14
		D = D - M
		@SP
		A = M
		M = D
		@SP
		A = M
		D = M
		'''.replace('\t','')+\
		'@LT_T_'+str(ltCount)+'\n'+\
		'D;JLT'+'\n'+\
		'@LT_F_'+str(ltCount)+'\n'+\
		'D;JGE'+'\n'+\
		'(LT_T_'+str(ltCount)+')'+'\n'+\
		'@SP'+'\n'+\
		'A=M'+'\n'+\
		'M=-1'+'\n'+\
		'@SP'+'\n'+\
		'M=M+1'+'\n'+\
		'@LT_E_'+str(ltCount)+'\n'+\
		'0;JMP'+'\n'+\
		'(LT_F_'+str(ltCount)+')'+'\n'+\
		'''@SP
		A=M
		M=0
		@SP
		M=M+1'''.replace('\t','')+'\n'+\
		'(LT_E_'+str(ltCount)+')'+'\n'
		
arAnd = '''@SP
		M=M-1
		A=M
		D=M
		@SP
		M=M-1
		A=M
		M=D&M
		@SP
		M=M+1
		'''.replace('\t','')

arOr = '''@SP
		M=M-1
		A=M
		D=M
		@SP
		M=M-1
		A=M
		M=D|M
		@SP
		M=M+1
		'''.replace('\t','')
		
arNot = '''@SP
		A=M-1
		M=!M
		'''.replace('\t','')

#end arithmetic commands


def writePush(x,value):
	if x == 'local':
		return "@"+str(value)+'\n'+pLocal+pushAdvance
	elif x == 'constant':
		return "@"+str(value)+'\n'+pConstant+pushAdvance
	elif x == 'argument':
		return "@"+str(value)+'\n'+pArgument+pushAdvance
	elif x == 'this':
		return "@"+str(value)+'\n'+pThis+pushAdvance
	elif x == 'that':
		return "@"+str(value)+'\n'+pThat+pushAdvance
	elif x == 'temp':
		return "@"+str(int(value)+5)+'\n'+pTemp+pushAdvance
	elif x == 'pointer':
		return "@"+str(int(value)+3)+'\n'+pPointer+pushAdvance
	elif x == 'static':
		return '@'+newFileName+'.'+str(value)+'\n'+pStatic+pushAdvance

def writePop(x,value):
	if x == 'local':
		return '@'+str(value)+'\n'+popLocal+popAdvance
	elif x == 'constant':
		return '@'+str(value)+'\n'+popConstant+popAdvance
	elif x == 'argument':
		return '@'+str(value)+'\n'+popArgument+popAdvance
	elif x == 'this':
		return '@'+str(value)+'\n'+popThis+popAdvance
	elif x == 'that':
		return '@'+str(value)+'\n'+popThat+popAdvance
	elif x == 'temp':
		return pop2Advance+'\n'+'@'+str(int(value)+5)+'\n'+popTemp
	elif x == 'pointer':
		return pop2Advance+'\n'+'@'+str(int(value)+3)+'\n'+popPointer
	elif x == 'static':
		return pop2Advance+'\n'+'@'+newFileName+'.'+str(value)+'\n'+popStatic
	
def writeArithmetic(x):
	if x == 'add':
		return arAdd
	elif x =='neg':
		return arNeg
	elif x =='sub':
		return arSub
	elif x == 'eq':
		return arEq
		eqCount += 1
	elif x == 'gt':
		return arGt
		gtCount += 1
	elif x == 'lt':
		return arLt
		ltCount += 1
	elif x == 'and':
		return arAnd
	elif x == 'or':
		return arOr
	elif x == 'not':
		return arNot

def writeLabelFunctions(x):
	if x == 'label':
		return
	elif x == 'goto':
		return
	elif x == 'if-goto':
		return
		

def main():
	loadFile = open(loadFiles(),'r')
	writeFile = open(newFileName,'w+')
	writeFile.write(bootstrap) #write bootstrap first
	for line in loadFile:
		if line.startswith('/'):
			continue
		elif line == None or not line.strip():
			continue
		else:
			line = line.split()
			if len(line) == 1:
				a = writeArithmetic(line[0])
			elif len(line) == 2:
				labelName = line[1]
				a = 
			elif len(line) == 3:
				if line[0] == 'push':
					a = writePush(line[1],line[2])
				elif line[0] == 'pop':
					a = writePop(line[1],line[2])
				elif line[0] =='function':
					functionName = line[1]
			writeFile.write(a)
			
			
	loadFile.close()
	writeFile.close()
	pass
	
if __name__ == "__main__":
	main()