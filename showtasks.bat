call runcrud
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo RUNCRUD has error - breaking work
goto fail

:openbrowser
Start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open browser !!!
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Showtasks work is finished succesfull.