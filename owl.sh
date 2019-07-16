#!/bin/bash

echo $(./owl-18.06/bin/ltl2ldba -i "$1" -n  | autfilt -B -d)
