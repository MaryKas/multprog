#!/usr/bin/env python

import subprocess
import math
import sys



threads = [2,4]
arr = ['LinkedBlockingQueue','ArrayBlockingQueue','PriorityBlockingQueue','PriorityQueue','LinkedList']

#p = subprocess.Popen(['java', '-jar', 'MultiQueue.jar',"1","1"], stdout=subprocess.PIPE,stderr=subprocess.PIPE)
#out, err = p.communicate()
#print(str(out))

for q in range(len(arr)):
	fl = open(arr[q]+".txt",'w')
	for t in threads:
		for n in range(10):
			print(arr[q],t,n)
			p = subprocess.Popen(['java', '-jar', 'MultiQueue.jar',str(q+1),str(t)], stdout=subprocess.PIPE,stderr=subprocess.PIPE)
			out, err = p.communicate()
			fl.write(str(int(out))+" "+str(t)+'\n')
	fl.close()

