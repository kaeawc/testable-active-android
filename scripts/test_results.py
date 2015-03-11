# -*- coding: utf-8 -*
import os
import sys
import xml.etree.ElementTree as ET

if bool(os.getenv(u"TERM", u"") == u"dumb"):
    white = u""
    red = u""
    green = u""
    yellow = u""
else:
    white = u"[0m"
    red = u"[31m"
    green = u"[32m"
    yellow = u"[33m"

good = u"%s✔%s" % (green, white)
bad = u"%s✗%s" % (red, white)

testfile = sys.argv[1]
tree = ET.parse(testfile)

if tree is None:
    exit

root = tree.getroot()

print("    %s" % root.get(u"name"))

children = root.getchildren()

for child in children:
    if child.tag != "testcase":
        continue

    testcase = child
    test_name = testcase.get(u"name")
    test_duration = int(float(testcase.get(u"time", 0)) * 1000)

    if test_duration < 200:
        test_duration_status = green
    elif test_duration  < 1000:
        test_duration_status = yellow
    else:
        test_duration_status = red

    test_duration = u"%s(%s ms) %s" % (test_duration_status, test_duration, white)

    # Look for failures
    if len(testcase.getchildren()) == 0:
        status = good
    else:
        status = bad

    print(u"        %s %s %s" % (status, test_name, test_duration))
