set curpath=%cd%
set outpath=%curpath%\bin
set cpath="%classpath%;%curpath%\src\;%outpath%;"

javac -d  %outpath% -classpath %cpath% %curpath%\src\lc871_880\LC871_880.java

rem:java -classpath %cpath% lc811_820.LC811_820

pause