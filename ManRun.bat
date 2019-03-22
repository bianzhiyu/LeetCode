set curpath=%cd%
set outpath=%curpath%\bin
set cpath="%classpath%;%curpath%\src\;%outpath%;"

java -classpath %cpath% test.MutualC1

pause