#!/bin/bash

FILES=$(find * | grep ".*test.*io.kaeawc.activeandroidapp.*xml")
for f in $FILES
do
 python scripts/test_results.py $f
done
