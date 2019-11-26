# SimpleCalculator
This was a small assignment I was given. Written in Java.
This text-based calculator can either be started straight away, or by adding the path to a .txt file in the command line argument. In which case the program reads and complies with instructions that follow the proper syntax.

Syntax for the program is:
<register> <operator> <register>
Example:
a add 3

Valid commands are "print <register>" and "quit"
Calculating other registers is done through lazy evaluation
Example:
  
A add 5

B add 2

A multiply B

A add 5

print A


Results in:
20

Valid Operators are limited to: Add, Subtract, Multiply

