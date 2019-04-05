set curpath=%cd%
set outpath=%curpath%\bin
set cpath="%classpath%;%curpath%\src\;%outpath%;"

javac -d  %outpath% -classpath %cpath% %curpath%\src\lc1001_1010\LC1001_1010.java

java -classpath %cpath% lc1001_1010.LC1001_1010

pause