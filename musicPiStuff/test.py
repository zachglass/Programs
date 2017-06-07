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

panda = "Panda.m4a",
tiimmy = "Timmy\ Turner.m4a",
monster = "Monster\ Caliber.m4a",
lambo = "Purple\ Lambo.m4a",
calmDown = "Calm\ Down.m4a",
meanIt = "Mean\ It.m4a",
business = "Bout\ The\ Business.m4a",
regardless = "Talk\ Regardless.m4a",
pain = "Sucker\ For\ Pain.m4a",
bars = "44\ Bars.m4a",
jam = "The\ Jam.m4a",
really = "Really\ Really.m4a",
lowLife = "Low\ Life.m4a",
garage = "18.\ Avicii\ -Pure\ Grinding.mp4",
flow = "17.\ Locate-Flow.mp4",
myHouse = "09\ My\ House.mp3",
thunderstruck = "01\ Thunderstruck.mp3"

#I would like to get this list to work so that I can make this program look a little better, also think that I should use more lists, since what I usually do is list based objects
songs = [panda, tiimmy, monster, lambo, calmDown, meanIt, business, regardless, pain, bars, jam, really, lowLife, garage, flow, myHouse, thunderstruck]

while (True):

    print("1. panda")
    print("2. tiimmy")
    print("3. monster")
    print("4. lambo")
    print("5. calm down")
    print("6. mean it")
    print("7. business")
    print("8. regardless")
    print("9. pain")
    print("10. bars")
    print("11. jam")
    print("12. really")
    print("13. low life")
    print("14. garage")
    print("15. flow")
    print("16. my house")
    print("17. thunderstruck")
    print("18. all")

    song = input("What song? \n")
        
    if song == "panda":
        song = song[0]
    if song == "tiimmy":
        song = tiimmy
    if song == "monster":
        song = monster
    if song == "lambo":
        song = lambo
    if song == "calm down":
        song = calmDown
    if song == "mean it":
        song = meanIt
    if song == "business":
        song = business
    if song == "regardless":
        song = regardless
    if song == "pain":
        song = pain
    if song == "bars":
        song = bars
    if song == "jam":
        song = jam
    if song == "really":
        song = really
    if song == "low life":
        song = lowLife
    if song == "garage":
        song = garage
    if song == "flow":
        song = flow
    if song == "my house":
        song = myHouse
    if song == "thunderstruck":
        song = thunderstruck
    if song == "all":
        sys("python music.py")
    if song == "shuffle":
        random = random.randint(0,17)
        song = songs[random]
    if song == "exit":
        break
    if song == "quit":
        break
    if song == "clear":
        sys("clear")
    if song == "":
        continue
    print("The Song is: " + str(song))
    sys(path+song)
    sys("clear")
sys("clear")
