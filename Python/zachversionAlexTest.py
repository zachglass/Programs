import string
import random
import time

lowercase = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
uppercase = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
#numbers = [0,1,2,3,4,5,6,7,8,9]
symbols = ['!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '=', '+', '[', ']', ';', ':', '<', '>', '?']

possibleCharacters = lowercase + uppercase + symbols #+ numbers

target = input(": ")
var = ''.join(random.choice(possibleCharacters) for i in range(len(target)))
varA = ""

completed = False

generation = 0

while completed == False:
	print(var)
	varA = ""
	completed = True
	for i in range(len(target)):
		if var[i] != target[i]:
			completed = False
			varA += random.choice(possibleCharacters)

		else:
			varA += target[i]
		
		if generation == 10000:
			completed = True

	generation += 1
	var = varA
	time.sleep(0.1)

print( str(generation) + " generation(s)")
