@echo off
cd /d %~dp0

@rem set JAVA_HOME=C:\Program Files\RedHat\java-11-openjdk-11.0.4-1

if not "%JAVA_HOME%" == "" goto OkJHome

echo.
echo Error: JAVA_HOME not found in your environment. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

:OkJHome
if exist "%JAVA_HOME%\bin\java.exe" goto init
echo.
echo Error: jave.exe not found. in JAVA_HOME>&2
echo.
goto error

:init
SET JAVA_EXE="%JAVA_HOME%\bin\java.exe"
for /f tokens^=2-5^ delims^=.-_^" %%j in ('%JAVA_EXE% -fullversion 2^>^&1') do set "JAVA_VER=%%j%%k"
if not %JAVA_VER% LSS 110 goto run
echo.
echo Error: Java version is lower than 11, please update your Java version. >&2
echo.
goto error

:run
%JAVA_EXE% -jar aqua.jar
if ERRORLEVEL 1 goto error
goto end


:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%
pause