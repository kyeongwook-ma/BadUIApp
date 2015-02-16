#!/usr/bin/python
# -*- coding: utf-8 -*-

import subprocess, signal,os

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

def main():
    device_num = find_device()
    subprocess.Popen("adb shell getevent | grep event" + str(device_num) + " > touch_logs.txt", shell=True)

main()
