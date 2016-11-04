# Assignment1

Objectives of this assignment
-----------------------------

<ol>
	<li>Read in a set of N String and weights from a file or url.</li>
	<li>Allow the user enter a prefix string.</li>
	<li>Find and display the top strings in the set that contain the prefix.</li>
	<li>Provide a comprehensive testing strategy using JUnit.</li>
</ol><br>

BruteAutoComplete (Linear search)
---------------------------------

<b>weightOf:</b> Using a given String find the highest weighted word if its in array list of term class. Or 0.0 if the string isn't found. Also throws Illegal Argument Exception if the given string is null.<br>
<b>bestMatch:</b> Using a given String prefix finds the highest weighted word with the matching prefix. Or null if not found. Or throws a Illegal Argument Exception if the given string is null.<br>
<b>matches: </b> Using a given prefix finds a list of the highest weighted words with matching prefix. Then if the list size matches the given size k then return a sublist of matches weight the highest to lowest weight or if the total number of matching prefixes is less than k return all words with given prefix, or returns empty list.<br>

QuickAutoComplete (Binary search + Linear search)
-------------------------------------------------

<b>weightOf:</b> Using a given String find the highest weighted word if its in array list of term class. Or 0.0 if the string isn't found. Also throws Illegal Argument Exception if the given string is null.<br>
<b>bestMatch:</b> Using a given String prefix finds the highest weighted word with the matching prefix. Or null if not found. Or throws a Illegal Argument Exception if the given string is null.<br>
<b>matches: </b> Using a given prefix finds a list of the highest weighted words with matching prefix. Then if the list size matches the given size k then return a sublist of matches weight the highest to lowest weight or if the total number of matching prefixes is less than k return all words with given prefix, or returns empty list.<br><br>

BruteAutoComplete and QuickAutoComplete Testing
-----------------------------------------------

<h3>Constructor Testing:</h3> 
<ul>
<li>Tests that the constructor works given a file that has no duplicate words or weights that are less than or equal to zero.</li>
<li>Tests that the constructor throws Illegal Argument Exception if the weight is equal to or less than zero.</li>
<li>Tests that the constructor throws Illegal Argument Exception if the word is repeated.</li>
<li>Tests that the constructor throws Null Pointer Exception if the given file is empty.</li>
<li>Tests that the constructor throws File Not Found Exception if the string path to file didn't find a file.</li>
</ul>
<br>
<h3>weightOf Testing:</h3>
<ul>
<li>Tests that if the given prefix is null a Null pointer Exception is thrown.</li>
<li>Tests that if the given prefix is not found in the list of Terms, then 0.0 is returned.</li>
<li>Tests that if the given prefix is found it returns the highest weight.</li>
</ul>
<br>
<h3>bestMatch Testing:</h3>
<ul>
<li>Tests that if the given prefix is null a Null pointer Exception is thrown.</li>
<li>Tests that if the given prefix is not found in the list of Terms, then null is returned.</li>
<li>Tests that if the given prefix is found it returns the highest word.</li>
</ul>
<br>
<h3>matches Testing:</h3>
<ul>
<li>Tests that if the given prefix is null a Null pointer Exception is thrown.</li>
<li>Tests that if the given size k is less than or equal to zero or greater than the size of the list of terms an Illegal Argument is thrown.</li>
<li>Tests that if the given prefix is not found in the list of Terms, then an empty list is returned.</li>
<li>Tests that if the given prefix is found it returns a list in descending order of weight of either size k or less than k.</li>
</ul>

Term Testing
------------

<ul>
<li>Test the constructor (Note: the constructor does not validate the data).</li>
<li>Test the Test the accessors.</li>
</ul>