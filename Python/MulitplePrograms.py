from os import system
programs = ['solitare.py', 'test123.py']
var = ''.join(str((programs)) for x in range(1000000))
system("python {0} ").format(int(var))
