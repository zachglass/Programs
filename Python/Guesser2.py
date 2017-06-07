import random

guesses = 0

name = raw_input('What is your name\n')

number = random.randint(1,10)
print('{0}, I am thinking of a number between 1 and 10.').format(name)

while guesses < 5:
	guesses += 1
	if guesses == 1:
		guess = int(raw_input('Take your first guess: '))
	if guesses == 2:
		guess = int(raw_input('Take your second guess: '))
	if guesses == 3:
		guess = int(raw_input('Take your third guess: '))
	if guesses == 4:
		guess = int(raw_input('Take your fourth guess: '))
	if guesses == 5:
		guess = int(raw_input('Take your final guess: '))
	if guess > number:
		print('Your guess is too high')
	if guess < number:
		print('Your guess is too low')
	if guess == number:
		break

if guess == number:
	print('Good job you guessed my number in {0} guesses!').format(guesses)
else:
	print('Oh, sorry, my number was {0}.').format(number)

