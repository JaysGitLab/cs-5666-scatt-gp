#
# makefile 
#
# input file for 'make' build tool ( /usr/bin/make )
# to build solution for CS 5666 JUnit sample
#
# @author Kara Beason, Cydney Caldwell, Michelle Melton 
# @version Spring 2017
#

JUNIT_JAR = /usr/share/java/junit-4.10.jar
HAMCREST_JAR = /usr/share/java/hamcrest/core-1.1.jar
CKSTYLE_COMMAND =  -jar /usr/local/checkstyle-5.5/checkstyle-5.5-all.jar

default: 
	@echo "usage: make target"
	@echo "available targets: compile, test, clean, check, customcheck"

compile: Scatt.java ScattTest.java Submission.java SubmissionTest.java FileUtils.java Report.java ReportTest.java
	javac -cp .:$(JUNIT_JAR) ScattTest.java SubmissionTest.java ReportTest.java
	javac Scatt.java Submission.java FileUtils.java Report.java

jar: Scatt.class Submission.class FileUtils.class Report.class
	jar -cvmf MANIFEST.MF Scatt.jar Scatt.class Submission.class FileUtils.class Report.class

clean:
	rm -f *.class
	rm -f Scatt.jar
	rm -rf expected
	rm -rf zips 
	rm -rf unzips

test: Submission.class SubmissionTest.class Scatt.class ScattTest.class FileUtils.class Report.class ReportTest.class
	java -cp .:$(JUNIT_JAR):$(HAMCREST_JAR) org.junit.runner.JUnitCore SubmissionTest
	java -cp .:$(JUNIT_JAR):$(HAMCREST_JAR) org.junit.runner.JUnitCore ScattTest
	java -cp .:$(JUNIT_JAR):$(HAMCREST_JAR) org.junit.runner.JUnitCore ReportTest

check: Scatt.java ScattTest.java Submission.java SubmissionTest.java FileUtils.java Report.java ReportTest.java
	checkstyle Scatt.java ScattTest.java Submission.java SubmissionTest.java FileUtils.java Report.java ReportTest.java
