import string
import random
import time

lowercase = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
uppercase = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
numbers = ['0','1','2','3','4','5','6','7','8','9']
symbols = ['!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '=', '+', '[', ']', ';', ':', '<', '>', '?']

possibleCharacters = lowercase + uppercase + symbols + numbers
#possibleNumbers = numbers

target = ": '-------------------------------------------------------------------------------------------------------------------------'"
#var1 = ''.join(random.choice(possibleCharacters) for a in range(len(target))) 
#var2 = ''.join(random.choice(possibleNumbers) for b in range(len(target)))
varA = ""
#var = ''.join(random.choice(var1 + var2) for c in range(len(target)))
completed = False
generation = 0
while completed == False:
	var = ''.join(random.choice(possibleCharacters) for a in range(len(target)))
#	var2 = ''.join(random.choice(possibleCharacters) for b in range(len(target)))
#	var = ''.join(random.choice(var1 + var2) for c in range(len(target)))
# I was going to seperate them, but I don't need too since they are all strings now
	print(var)
	varA = ""
	completed = True
	for i in range(len(target)):
		if var[i] != target[i]:
			completed = False
			varA += random.choice(var)
		else:
			varA += target[i]
		
	#	if var1[x] != target[x]:
	#		completed = False
	#		varA += random.choice(possibleNumbers)
	#	else:
	#		varA += target[x]
	# This code ^, was going to be used for making the program decide whether or not the variable is a char or an int
	if generation == 10000:
			completed = True
#This code below, was going to be the logic of generating the number for the program, but I don't think it'll run
#while completed == False:
#	print (var1)
#	varA = ""
#	comepleted = True
#	for x in range(len(target)):
#		if var[x] != target[x]:
#			completed = False
#			varA += random.choice(possibleNumbers)
#		else:
#			varA += target[x]
#
#
	generation += 1
#	if generation == 10000:
#		completed = True
#This piece ^, was from before, I was going to add it out of both while loops so they dont conflict
	var = varA
	time.sleep(0.1)

print( str(generation) + " generation(s)")
