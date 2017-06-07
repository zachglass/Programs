from os import system
print("1: Set Alarm for 6:00 am")
print("2: Turn off Zach Light")
print("3: Turn on Zach Light")
print("4: Stop")
for i in range(0,10):
    number = input("Input your number here: ")
    if (number == 1):
	    system("say Alexa, set an alarm for 6 in the morning")
    if (number == 2):
	    system("say Alexa, turn off Zach Light")
    if (number == 3):
	    system("say Alexa, turn on Zach Light")
    if (number == 4):
	    system("say Alexa, stop")
