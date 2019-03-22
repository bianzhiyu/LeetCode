set curpath=%cd%
set outpath=%curpath%\bin
set cpath="%classpath%;%curpath%\src\;%outpath%"

rem:goto:sincetest

javac -d  %outpath% -classpath %cpath% %curpath%\src\test\MutualC1.java

goto:sincetest
rem:goto:EOF
rem:exit

:sinceownlib
javac -d  %outpath% -classpath %cpath% %curpath%\src\bbst\BBST.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\bbst\TestBBST.java

javac -d  %outpath% -classpath %cpath% %curpath%\src\dijkstra\EdgeTo.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\dijkstra\Dijkstra.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\dijkstra\DijkstraResult.java

javac -d  %outpath% -classpath %cpath% %curpath%\src\kmp\KMP.java

javac -d  %outpath% -classpath %cpath% %curpath%\src\qsort\Qsort.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\qsort\TestQsort.java

javac -d  %outpath% -classpath %cpath% %curpath%\src\treeCodec\BinaryTree.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\treeCodec\TreeNode.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\treeCodec\TreeCodec.java

rem:exit



:since1
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc1_10\LC1_10.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc11_20\LC11_20.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc21_30\BackGround21_30.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc21_30\LC21_30.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc31_40\LC31_40.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc41_50\LC41_50.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc51_60\LC51_60.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc61_70\LC61_70.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc71_80\LC71_80.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc81_90\LC81_90.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc91_100\LC91_100.java

:since101
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc101_110\OuterClasses.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc101_110\BBST.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc101_110\BackGround101_110.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc101_110\LC101_110.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc111_120\LC111_120.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc121_130\LC121_130.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc131_140\LC131_140.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc141_150\LC141_150.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc151_160\LC151_160.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc161_170\LC161_170.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc171_180\LC171_180.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc181_190\LC181_190.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc191_200\LC191_200.java


:since201
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc201_210\OutClasses.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc201_210\StrTrie.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc201_210\LC201_210.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc211_220\LC211_220.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc221_230\LC221_230.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc231_240\LC231_240.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc251_260\LC251_260.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc261_270\LC261_270.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc271_280\LC271_280.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc281_290\LC281_290.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc291_300\LC291_300.java


:since301
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc301_310\LC301_310.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc311_320\LC311_320.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc321_330\LC321_330.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc331_340\LC331_340.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc341_350\LC341_350.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc351_360\LC351_360.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc361_370\LC361_370.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc371_380\LC371_380.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc381_390\LC381_390.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc391_400\LC391_400.java


:since401
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc401_410\LC401_410.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc411_420\LC411_420.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc421_430\LC421_430.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc431_440\LC431_440.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc441_450\LC441_450.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc451_460\LC451_460.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc461_470\LC461_470.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc471_480\LC471_480.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc491_500\LC491_500.java

:since501
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc501_510\LC501_510.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc511_520\LC511_520.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc521_530\LC521_530.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc531_540\LC531_540.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc541_550\LC541_550.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc551_560\LC551_560.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc561_570\LC561_570.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc591_600\LC591_600.java

:since601
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc621_630\LC621_630.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc631_640\LC631_640.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc641_650\LC641_650.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc651_660\LC651_660.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc671_680\LC671_680.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc691_700\LC691_700.java

:since701
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc701_710\LC701_710.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc711_720\LC711_720.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc721_730\LC721_730.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc731_740\LC731_740.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc741_750\LC741_750.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc761_770\LC761_770.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc771_780\LC771_780.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc781_790\LC781_790.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc791_800\LC791_800.java

:since801
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc811_820\LC811_820.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc821_830\LC821_830.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc831_840\LC831_840.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc841_850\LC841_850.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc851_860\LC851_860.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc871_880\LC871_880.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc891_900\LC891_900.java

:since901
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc901_910\LC901_910.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc921_930\LC921_930.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc941_950\LC941_950.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc961_970\LC961_970.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc971_980\LC971_980.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\lc991_1000\LC991_1000.java

:since1001

:sincetest
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\FileIOTest.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\OutClasses.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\MutualC1.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\MutualC2.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\MutualC3.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\Test.java

javac -d  %outpath% -classpath %cpath% %curpath%\src\test\subtest\M1.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\subtest\M2.java
javac -d  %outpath% -classpath %cpath% %curpath%\src\test\subtest\Subp.java

:EndPause
pause