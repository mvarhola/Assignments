The Assembler successfully parses and assembles all of the test files in this chapter.
It requires two arguments: the input file and the output file.

Usage: python assemble.py input_file.asm output_file.hack

It does not check for file type yet, so it's possible to feed it a
file with a different extension that supported by the CPU Emulator.

Also, it throws a fit when you feed it more or less than 2 arguments.

Tested under python 2.7.2
