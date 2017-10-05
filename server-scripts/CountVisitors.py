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
    accessAmount = 0
    with open(filename) as f:
        for line in f:
            index = line.index("-")
            ipAdr.add(line[0:index])
            accessAmount += 1
    print "Unique IP: " + str(ipAdr.__len__()) + ", common amout of requests: " + str(accessAmount)


# example of insert parameters : --path=/path/file.log
if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--path', default=DEFAULT_PATH, help='path to the ngings access log file', type=str)
    args = parser.parse_args()
    #launch the method
    count_visitors(args.path)
