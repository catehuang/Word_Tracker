Pre-condition
	1. A user provides a text file named textfile.txt and put it to res/textfile.txt.
	2. A user may provide a binary file repository.ser storing a binary search tree and located at res/repository.ser 
	3. The function serialization at line 33, WordTracker is disable. The following particular cases can work:
		3.1 Without res/repository.ser, using textfile.txt to create a tree and store into res/repository.ser. The tree will be stored in res/repository.ser.
		3.2 With res/repository.ser and textfile.txt, but disable serialization at line 33, WordTracker. It will deserialize a tree from res/repository.ser and adds nodes which come from textfile.txt.

Usage
	java -jar Tracker.jar [-pf/pl/po] [-f report.txt]
	
	[] means optional
	Mutually exclusive options (default is po):
	-pf to print in alphabetic order all words along with the corresponding list of files in which the words occur
	-pl to print in alphabetic order all words along with the corresponding list of files and numbers of the lines in which the word occur
	-po to print in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words.
	Other option:
	-f filename to redirect the report in the previous step to a specified file
	
How does this program work?
	1. If res/repository.ser exists, this program will deserialize and restore a binary search tree from repository.ser 
	2. The res/textfile.txt is provided by users. This program will read this text file and construct a binary search tree.
	   If repository.ser exists, the binary search tree built from text file will be added on the restored binary search tree which is from repository.ser.
	3. Then the binary search tree will be store in res/repository.ser.
	4. According to user specified output format, the program will generate result output to console (-pf/pl/po) or file (-f filename.txt)
	
