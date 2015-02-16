#!/usr/bin/python
# -*- coding: utf-8 -*-

import os, sys, sqlite3

def find_val(line, coord_target):
    if line.rfind(coord_target) != -1:
        return int(line[line.rfind(coord_target)+2:].replace(" ", ""), 16)

def time_conversion(line):
    return line[line.find("[")+1:line.find("]")]

def write_bm(conn, bm_dict):
    
    bm_cur = ((bm_dict["time"], bm_dict["x"], bm_dict["y"]))
 
    conn.execute('INSERT INTO BMTable (time_stamp,x,y) VALUES (?,?,?)',bm_cur)
    conn.commit()

def main():
    
    if len(sys.argv) < 2:
        print "Usage python log_parser.py logfile dbfile"
        sys.exit(1)
    
    file_path = sys.argv[1]
    logs = open(file_path).read().splitlines()

    conn = sqlite3.connect(sys.argv[2])

    for idx, line in enumerate(logs):
        x = find_val(line, "_X")
        if x is not None:
            time = time_conversion(line)
            y = find_val(logs[idx+1], "_Y")
            write_bm(conn, {"time":time, "x":x, "y":y })

if __name__ == '__main__':
    main()
