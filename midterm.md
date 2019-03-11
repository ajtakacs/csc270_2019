## N-Grams
1.Definition and how n-grams are used in various disciplines:
An n-gram is a model that finds sequences within a text. It looks for commonalities, then groups and condenses them; this allows simplicity and scalabity. 
In the humanities, n-grams are most often used in computational linguistics to analyze text, predict outcomes, find spelling error, etc.
In the sciences, n-grams can be used for DNA sequencing. It helps researchers expedite their research with its space-time tradeoff, for they can produce more data in less time.
 Businesses could use n-grams to detect common search terms and include them within their marketing so that they have better visibility on the internet. 


1.Steps necessary to find N-Grams: 
Text should be in Vector[String] format and should be stripped of all punctuation and special characters.


1.Methods on Scala for analyzing a text for N-Grams:
..- .size : Gives the user the number of elements within a collection. It can be a commonality between sequences that could be considered an n-gram.
..- .toUpperCase : Converts all of the characters within a text to capital letters. A program might differentiate between lower and uppercase letters. If all of the characters are of the same kind, the computer is more efficient at finding a pattern.
..- .map : Creates key-value combinations. This helps condense the text by essentially eliminating repetition or simplifying expressions.
..-.filter : Allows the user to create a collection of items that share something. An n-gram could be created on a shorter list, thus decreasing the run-time of the program.
