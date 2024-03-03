# Cron-Expression-Parser
This is a command line application which parses a cron string and expands each field to show the times at which it will run.

We should only consider the standard cron format with five time fields (minute, hour, day of month, month, and day of week) plus a command, and you do not need to handle the special time strings such as "@yearly". The input will be on a single line.

The cron string will be passed to your application as a single argument.
~$ your-program ＂*/15 0 1,15 * 1-5 /usr/bin/find＂

The output should be formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.

# Example
For the following input argument:
*/15 0 1,15 * 1-5 /usr/bin/find

It should yield the following output:

minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find

# Instructions to Run the Program

./cron_script.sh "*/15 0 1,15 * 1-5 /usr/bin/find"

The shell script checks if there is a java installation available. If not it throws and error. Else it checks if the version is >= 11. If yes, then it compiles the java source code main class, builds the Java project and packages it into an executable jar.

This jar gets executed finally to display the output.

# Tests

Sample tests are present in the tes/ directory, to cover some basic cases.