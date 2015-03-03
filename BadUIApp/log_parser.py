#!/usr/bin/python
# -*- coding: utf-8 -*-

import os, sys, sqlite3, subprocess, signal

def find_val(line, coord_target):
    if line.rfind(coord_target) != -1:
        return int(line[line.rfind(coord_target)+2:].replace(" ", ""), 16)

def time_conversion(line):
    return line[line.find("[")+1:line.find("]")]

def write_bm(conn, bm_dict):

    bm_cur = ((bm_dict["time"], bm_dict["x"], bm_dict["y"]))

    conn.execute('INSERT INTO BMTable (time_stamp,x,y) VALUES (?,?,?)',bm_cur)
    conn.commit()

file_name = "device_logs.txt"

def find_device():

    p = subprocess.Popen("adb shell getevent -l > " + file_name, shell=True)

    device_logs = open(file_name, "r").readlines()

    os.kill(p.pid, signal.SIGINT)

    touch_device_line = ""

    for idx, log in reversed(list(enumerate(device_logs))):
        if "touchscreen" in log:
            touch_device_line = device_logs[idx-1]
            break

    touch_device_line = touch_device_line.replace(" ", "").replace("\t", "")
    device_num = touch_device_line[len(touch_device_line)-3:]
    return device_num

def generate_touch_log():
    device_num = find_device()
    subprocess.Popen("adb shell getevent | grep event" + str(device_num) + " > touch_logs.txt", shell=True)

def main():

    def signal_handler(signal, frame):
        os.kill(touch_prc_pid, signal.SIGINT)
        parse_log()

    def parse_log():
        conn = sqlite3.connect(sys.argv[2])
        logs = open(file_path).read().splitlines()
        
        for idx, line in enumerate(logs):
            x = find_val(line, "_X")
            if x is not None:
                time = time_conversion(line)
                y = find_val(logs[idx+1], "_Y")
                write_bm(conn, {"time":time, "x":x, "y":y })


    if len(sys.argv) < 2:
        print "Usage python log_parser.py logfile dbfile"
        sys.exit(1)

    touch_prc_pid = generate_touch_log()
    signal.signal(signal.SIGINT, signal_handler)
    

