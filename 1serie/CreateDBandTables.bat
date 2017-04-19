echo off
echo Running sql scripts from group 1
sqlcmd -i %cd%\src\serie3\sql\SQLQuery.sql

set /p delExit=Press the ENTER key to exit...: