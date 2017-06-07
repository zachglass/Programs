import csv

writer = csv.writer(open('stocks.csv', 'wb', buffering=0))
writer.writerows([
	('GOOG', 'Google, Inc.', 505.24, 0.47, 0.67),
	('YHOO', 'Yahoo! Inc.', 27.38, -0.33, -1.22),
	('CNET', 'CNET Networks, Inc.', 8.62, 0.13, 3.00)
])

stocks = csv.reader(open('stocks.csv', 'rn'))
status_labels = {-1: 'down', 0: 'unchanged', 1: 'up'}
for ticker, name, price, change, pct in stocks:
	status = status_labels[cmp(float(change), 0.0)]
	print '%s is %s (%s%%)' % (name, status, pct)
