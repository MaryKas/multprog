#!/usr/bin/env python

import subprocess
import math
import sys

threads = [1,2,3,4,5,7,10,12,15,20]
arr = ['LinkedBlockingQueue','ArrayBlockingQueue','PriorityBlockingQueue','PriorityQueue','LinkedList']
metrics=["latency","throughput"]
time_arr=[]
operations=['add','pop']
n_tests = 50

for m in metrics:
	for o in operations:
		fl = open(m+"_"+o+".txt",'w')
		fl.write("N_Threads "+" ".join(arr)+'\n')
		for t in threads:
			time_arr = [str(t)]
			for q in range(len(arr)):
				res=0
				for n in range(n_tests):
					p = subprocess.Popen(['java', '-jar', 'MultiQueue.jar',str(q+1),str(t),o,m], stdout=subprocess.PIPE,stderr=subprocess.PIPE)
					out, err = p.communicate()
					print(arr[q],t,n,o,out)
					res+=int(out)
				result = res/n_tests
				if (m=='throughput'):
					result *= 10
				time_arr.append(str(result))
			fl.write(" ".join(time_arr)+'\n')		
		fl.close()	

