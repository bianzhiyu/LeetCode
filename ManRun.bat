set curpath=%cd%
set outpath=%curpath%\bin
set cpath="%classpath%;%curpath%\src\;%outpath%;"

javac -d  %outpath% -classpath %cpath% %curpath%\src\minesweep\MineSweeper.java

java -classpath %cpath% minesweep.MineSweeper

pause