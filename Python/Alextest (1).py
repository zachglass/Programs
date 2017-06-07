import string
import random
import time

possibleCharacters = string.ascii_lowercase + string.digits + string.ascii_uppercase  


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
