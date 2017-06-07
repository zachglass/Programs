from os import system as sys

global command
command  = ""

while (True):
    print("Edit\nView\nRun")

    editing = raw_input("What do you want to do? ")

    if editing == "Edit":
        command = "vim "
        print("You can now Edit")
    elif editing == "edit":
        command = "vim "
        print("You can now edit")
    elif editing == "View":
        command = "cat "
        print("You can only View")
    elif editing == "view":
        command = "cat "
        print("you can only view")
    elif editing == "Run":
        command = "python "
        print("You can only Run")
    elif editing == "run":
        command == "python "
        print("You can only run")
    elif editing == "exit":
        break
    elif editing == "quit":
        break
    else:
        print("Sorry that can not be done")
        break

    print("Which file do you want to run?")
    sys("ls")
    program = raw_input("File: ")
    
    if program == "quit":
        break
    elif program == "exit":
        break
    else:
        sys(command+program)
        con = raw_input("Do you want to continue? ")
        if con == "yes":
            sys("clear")
        elif con == "no":
            break
        else:
            sys("clear")
            print("Well lets continue then")
