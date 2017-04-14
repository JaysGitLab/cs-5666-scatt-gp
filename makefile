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
JSON_SIMPLE_JAR = json_simple.jar
CKSTYLE_COMMAND =  -jar /usr/local/checkstyle-5.5/checkstyle-5.5-all.jar
APP_FILES = Scatt.java Submission.java Report.java FileUtils.java
TEST_FILES = ScattTest.java SubmissionTest.java ReportTest.java
CLASS_FILES = Scatt.class Submission.class Report.class FileUtils.class

default: 
	@echo "usage: make target"
	@echo "available targets: compile, jar, run, scatt, clean, test, check"

compile: $(APP_FILES) $(TEST_FILES)
	javac -cp .:$(JUNIT_JAR):$(JSON_SIMPLE_JAR) $(TEST_FILES)
	javac -cp .:$(JSON_SIMPLE_JAR) $(APP_FILES)

jar: $(CLASS_FILES)
	jar -cvmf MANIFEST.MF Scatt.jar $(JSON_SIMPLE_JAR) $(CLASS_FILES)

run: Scatt.jar
	java -jar Scatt.jar

scatt: $(APP_FILES)
	javac -cp .:$(JSON_SIMPLE_JAR) $(APP_FILES)
	jar -cvmf MANIFEST.MF Scatt.jar $(CLASS_FILES)
	java -jar Scatt.jar

clean:
	rm -f *.class
	rm -f Scatt.jar
	rm -rf expected
	rm -rf zips 
	rm -rf unzips
	rm -f Report.txt

test: Submission.class SubmissionTest.class Scatt.class ScattTest.class FileUtils.class
	java -cp .:$(JUNIT_JAR):$(HAMCREST_JAR):$(JSON_SIMPLE_JAR) org.junit.runner.JUnitCore SubmissionTest
	java -cp .:$(JUNIT_JAR):$(HAMCREST_JAR):$(JSON_SIMPLE_JAR) org.junit.runner.JUnitCore ReportTest
	java -cp .:$(JUNIT_JAR):$(HAMCREST_JAR) org.junit.runner.JUnitCore ScattTest	

check: $(APP_FILES) $(TEST_FILES)
	checkstyle $(APP_FILES) $(TEST_FILES)
