from os import system

import random

for x in range(0,10000000):

	guesses_made = 0

	name = raw_input('Why hello?! What is your name?\n')

	number = random.randint(1, 100)
	print 'Well, {0}, I am thinking of a number between 1 and 100.'.format(name)

	while guesses_made < 15:

		guess = int(raw_input('Take a guess: '))

		guesses_made += 1

		if guess < number:
			system('say You guessed too low')
			print 'You guessed too low!'

		if guess > number:
			system('say You guessed too high')
			print 'You guessed too high!'

		if guess == number:
			break
	
	if guess == number:
		print 'Great! You guess my number in {0} guesses {1}!'.format(guesses_made, name)
	else :
		print 'Well, sorry, my number was {0}. :P'.format(number)
	
	again = raw_input('Do you want to play again,  yes or no\n')
	
	if again == "yes":
		print 'OK!'
	if again == "no":
		break
