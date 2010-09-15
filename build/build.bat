@echo off
SET JAVA_HOME=D:\Java\jdk1.6.0_20
SET DEPS=D:\Workspaces\XBlink\org.xblink\lib
SET OUTPUT=D:\XBlink
SET PROJECT_HOME=D:\Workspaces\XBlink\org.xblink

D:
cd %PROJECT_HOME%\build
ant

@echo on
