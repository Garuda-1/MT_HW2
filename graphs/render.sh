#!/bin/bash

for filename in *.dot
do
    dot -Tpng "$filename" -o "$filename".png
done
