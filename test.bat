if not exist %~dp0\bin mkdir %~dp0\bin

javac -d bin *.java

cd %~dp0\bin

java Window

del *.class

cd ..

del *.class REM delete from current path

REM cls