#!/usr/bin/env python

import subprocess
import math
import sys



threads = [1,2,3,4,5,7,10,12,15,20]
arr = ['LinkedBlockingQueue','ArrayBlockingQueue','PriorityBlockingQueue','PriorityQueue','LinkedList']
operations=['pop']

#p = subprocess.Popen(['java', '-jar', 'MultiQueue.jar',"1","1"], stdout=subprocess.PIPE,stderr=subprocess.PIPE)
#out, err = p.communicate()
#print(str(out))


for o in operations:
	for q in range(len(arr)):
		fl = open(arr[q]+'_'+o+".txt",'w')
		for t in threads:
			res=0
			for n in range(50):
				
				p = subprocess.Popen(['java', '-jar', 'MultiQueue.jar',str(q+1),str(t),o], stdout=subprocess.PIPE,stderr=subprocess.PIPE)
				out, err = p.communicate()
				print(arr[q],t,n,o,out)
				res+=int(out)
			res/50
			fl.write(str(res)+" "+str(t)+'\n')	
		fl.close()

