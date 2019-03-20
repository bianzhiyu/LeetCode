set curpath=%cd%
set outpath=%curpath%\bin
set cpath="%classpath%;%curpath%\src\;%curpath%\bin"



javac -d  %outpath% -classpath %cpath% %curpath%\src\lc101_110\OuterClasses.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc101_110\BBST.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc101_110\BackGround101_110.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc101_110\LC101_110.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc231_240\LC231_240.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\MutualC1.java
 
