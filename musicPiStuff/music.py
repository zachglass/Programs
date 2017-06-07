from os import system as sys

global path
global time
global speed
path = ""
time = ""
speed = ""

trail = " ~/Music/iTunes/iTunes\ Media/Music/Unknown\ Artist/Unknown\ Album/"

speed = str(input("How fast of a mulitplier do you want? "))
path = "afplay -r " + speed + trail

panda = "Panda.m4a"
tiimmy = "Timmy\ Turner.m4a"
monster = "Monster\ Caliber.m4a"
lambo = "Purple\ Lambo.m4a"
calmDown = "Calm\ Down.m4a"
meanIt = "Mean\ It.m4a"
business = "Bout\ The\ Business.m4a"
regardless = "Talk\ Regardless.m4a"
pain = "Sucker\ For\ Pain.m4a"
bars = "44\ Bars.m4a"
jam = "The\ Jam.m4a"
really = "Really\ Really.m4a"
lowLife = "Low\ Life.m4a"
garage = "18.\ Avicii\ -Pure\ Grinding.mp4"
flow = "17.\ Locate-Flow.mp4"
myHouse = "09\ My\ House.mp3"
thunderstruck = "01\ Thunderstruck.mp3"

for x in range(1,18):
    if x == 1:
        song = panda
    elif x == 2:
        song = tiimmy
    elif x == 3:
        song = monster
    elif x == 4:
        song = lambo
    elif x == 5:
        song = calmDown
    elif x == 6:
        song = meanIt
    elif x == 7:
        song = business
    elif x == 8:
        song = regardless
    elif x == 9:
        song = pain
    elif x == 10:
        song = bars
    elif x == 11:
        song = jam
    elif x == 12:
        song = really
    elif x == 13:
        song = lowLife
    elif x == 14:
        song = garage
    elif x == 15:
        song = flow
    elif x == 16:
        song = myHouse
    elif x == 17:
        song = thunderstruck
    else:
        print("What Happened")
    print("The song is: " + song)
    sys(path+song)
sys("clear")
