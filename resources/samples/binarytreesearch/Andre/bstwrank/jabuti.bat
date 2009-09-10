set TOOL=Y:\jabuti.old
set JAVA_HOME=Y:\jdk1.3.1_08\bin

%JAVA_HOME%\java -cp ".;%TOOL%;%TOOL%\lib\bcel.jar;%TOOL%\lib\junit.jar;%TOOL%\lib\jviewsall.jar;%TOOL%\lib\dom.jar;%TOOL%\lib\crimson.jar;%TOOL%\lib\jaxp-api.jar" gui.JabutiGUI

