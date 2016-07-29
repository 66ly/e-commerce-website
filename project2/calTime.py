from __future__ import division
import sys
import re

class LogTime:
	
	# serT = 0
	# jdbcT = 0
	# jdbcNum = 0
	# serNum = 0
	
	def __init__(self, filename = "log_SearchServlet.txt"):
		self.filename = filename
		self.serT = 0
		self.jdbcT = 0
		self.jdbcNum = 0
		self.serNum = 0
	
	def openLog(self):
		with open(self.filename) as f:
			for line in f.readlines():
				#print line
				self.jdbcTime(line)
				self.servletTime(line)
		print "Average servlet time is: " , self.serT/self.serNum, " ns"
		print "Average jdbc time is: " , self.jdbcT/self.jdbcNum, " ns"

	def jdbcTime(self, line):
		pattern = re.search(r'JDBC.(\d+).end' , line)
		
		if pattern:
			self.jdbcT += int(pattern.group(1))			
			self.jdbcNum += 1

	def servletTime(self, line):
		pattern = re.search(r'Servlet.(\d+).end' , line)
		
		if pattern:
			self.serT += int(pattern.group(1))
			self.serNum += 1

if __name__=="__main__":
	Log = LogTime()
	Log.openLog()
