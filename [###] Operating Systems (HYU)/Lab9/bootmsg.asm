; Start of Program - Use 16 bit mode

	BITS 16
	NEWLINE: db 0xa, 0xd
	LENGTH: equ $-NEWLINE


	; Initialize the Data Segment Register and Extra Segment Register

start:
		mov ax, 07C0h
		mov ds, ax
		mov es, ax

		; Initialize the Stack Segment Register and Stack Pointer

		add ax, 544
		mov ss, ax
		mov sp, 4096

		; Print the Message

		mov si, text_string
		call print_string
		
		; Loop Forever

		jmp $

; End of main program

; Strings
	
	text_string db "Welcome to the Markiyan OS", 0x0a,0x0d, "Written by Markiyan Varhola", 0x0a,0x0d, "Press a key.....", 0x0a,0x0d, "1) Print Hello", 0x0a,0x0d, "2) Clear Screen", 0x0a,0x0d, "3) Reboot", 0x0a,0x0d, "4) Power Off", 0x0a,0x0d, 0x0a,0x0d, "Enter a Command: ", 0x0a,0x0d, 0

;	Print the string pointed to by register si

print_string:

	; load one character from the memoty to AL

load_char_to_al:
		lodsb
		cmp al, 0
		jne print_char

		ret

print_char:
		mov ah, 0Eh
		int 10h
		jmp short load_char_to_al

		times 510-($-$$) db 0
		dw 0xAA55

		;End of boot sector code
