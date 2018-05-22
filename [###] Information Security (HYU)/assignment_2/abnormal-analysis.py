import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import scipy.fftpack
import sys

#usage: ./abnormal-analysis.py {filename}

def timeseries(filename):

	start_date = '2002-01-01'

	y = np.load(filename)
	x = pd.date_range(start=start_date, periods=y.size, freq='D')

	fig = plt.figure()
	plt.plot(x,y,'.')
	fig.savefig('timeseries.pdf')
	print 'timeseries.pdf is created'
	return 0

def cdf(filename):
	x = np.load(filename)

	sor_x = np.sort(x)
	y= np.arange(sor_x.size)/float(len(sor_x))
	
	fig2 = plt.figure()
	plt.plot(sor_x, y)
	fig2.savefig('cdf.pdf')
	print 'cdf.pdf is created'
	return 0

def fft(filename):
	y = np.load(filename)
	N = y.size
	Fs = 150.0
	T = N/Fs
	x = np.linspace(0.0, N*T, N)

	k = np.arange(N)
	T = N/Fs
	frq = k/T
	frq = frq[range(N/2)]

	yf = scipy.fftpack.fft(y)
	xf = np.linspace(0.0, 1.0/(2.0*T), N/2)

	fig3 = plt.figure()

	fig3, ax = plt.subplots()

	ax.plot(xf, 2.0/N * np.abs(yf[:N/2]))
	ax.set_xlabel('Day')
	ax.set_ylabel('Power')
	fig3.savefig('fft.pdf')
	print 'fft.pdf is created'
	return 0
	
npyfile = sys.argv[1]

timeseries(npyfile)
cdf(npyfile)
fft(npyfile)
