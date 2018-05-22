@echo off
cd %0\..
"C:\Program Files (x86)\Java\jre6\bin\java" -classpath "%CLASSPATH%;.;bin/classes;bin/lib/Hack.jar;bin/lib/HackGUI.jar;bin/lib/Simulators.jar;bin/lib/SimulatorsGUI.jar;bin/lib/Compilers.jar" HardwareSimulatorMain %1




