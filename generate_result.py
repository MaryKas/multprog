#!/usr/bin/env python

import subprocess
import math
import sys

threads = [1,2,3,4,5,7,10,12,15,20]
arr = ['LinkedBlockingQueue','ArrayBlockingQueue','PriorityBlockingQueue','PriorityQueue','LinkedList']
time_arr=[]
operations=['add','pop']
n_tests = 50

for o in operations:
	fl = open("result_"+o+".txt",'w')
	fl.write("N_Threads "+" ".join(arr)+'\n')
	for t in threads:
		time_arr = [str(t)]
		for q in range(len(arr)):
			res=0
			for n in range(n_tests):
				p = subprocess.Popen(['java', '-jar', 'MultiQueue.jar',str(q+1),str(t),o], stdout=subprocess.PIPE,stderr=subprocess.PIPE)
				out, err = p.communicate()
				print(arr[q],t,n,o,out)
				res+=int(out)
			time_arr.append(str(res/n_tests))
		fl.write(" ".join(time_arr)+'\n')		
	fl.close()	

