#!/usr/bin/python

import argparse
import os.path

DEFAULT_PATH="/var/log/nginx/access.log"

#The scripts should be deploid on the server to count unic IP from nginx access.log
def count_visitors(filename):
    if not os.path.isfile(filename):
        print "No such file: " + filename
        return
    # count visitors
    ipAdr = set()
    with open(filename) as f:
        for line in f:
            index = line.index("-")
            ipAdr.add(line[0:index])
    print ipAdr.__len__()

# example of insert parameters : --path=/path/file.log
if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--path', default=DEFAULT_PATH, help='path to the ngings access log file', type=str)
    args = parser.parse_args()
    #launch the method
    count_visitors(args.path)
