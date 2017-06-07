

name = raw_input('What is your name?\n')
print 'Hi, %s.' % name
feeling = int(raw_input('Are you feeling good? 1 for yes, 2 for no\n'))
if feeling == 1:
	print 'YES! Great! :)'
if feeling == 2:
	print 'No, that sucks :('
