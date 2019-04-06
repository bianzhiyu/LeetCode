set curpath=%cd%
set outpath=%curpath%\bin
set cpath="%classpath%;%curpath%\src\;%outpath%;"

javac -d  %outpath% -classpath %cpath% %curpath%\src\lc811_820\LC811_820.java

java -classpath %cpath% lc811_820.LC811_820

pause