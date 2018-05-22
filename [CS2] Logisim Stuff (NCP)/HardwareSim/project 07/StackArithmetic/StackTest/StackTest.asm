@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
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
@EQ_T_0
D;JEQ
@EQ_F_0
D;JNE
(EQ_T_0)
@SP
A=M
M=-1
@SP
M=M+1
@EQ_E_0
0;JMP
(EQ_F_0)
@SP
A=M
M=0
@SP
M=M+1
(EQ_E_0)
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
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
@LT_T_0
D;JLT
@LT_F_0
D;JGE
(LT_T_0)
@SP
A=M
M=-1
@SP
M=M+1
@LT_E_0
0;JMP
(LT_F_0)
@SP
A=M
M=0
@SP
M=M+1
(LT_E_0)
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
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
@GT_T_0
D;JGT
@GT_F_0
D;JLE
(GT_T_0)
@SP
A=M
M=-1
@SP
M=M+1
@GT_E_0
0;JMP
(GT_F_0)
@SP
A=M
M=0
@SP
M=M+1
(GT_E_0)
@56
D=A
@SP
A=M
M=D
@SP
M=M+1
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
@53
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M+D
@SP
M=M+1
@112
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
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
@SP
M=M-1
A=M
M=-M
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=D&M
@SP
M=M+1
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=D|M
@SP
M=M+1
