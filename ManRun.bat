set curpath=%cd%
set outpath=%curpath%\bin
set cpath="%classpath%;%curpath%\src\;%outpath%;"

javac -d  %outpath% -classpath %cpath% %curpath%\src\lc401_410\LC401_410.java

java -classpath %cpath% lc401_410.LC401_410

pause