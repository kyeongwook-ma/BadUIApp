#!/usr/bin/python
# -*- coding: utf-8 -*-

import os, sys, log_extractor

file_name = "logs.txt"

def find_device():
    def generate_logs():
        os.popen3("adb shell getevent -l > " + file_name)
    generate_logs()

    device_logs = open(file_name, "r").readlines()
 
    os.popen3("rm " + file_name)

    touch_device_line = ""

    for idx, log in reversed(list(enumerate(device_logs))):
        if "touchscreen" in log:
            touch_device_line = device_logs[idx-1]
        break
    touch_device_line = touch_device_line.replace(" ", "").replace("\t", "")
    device_num = touch_device_line[len(touch_device_line)-3:]
    return device_num

def main():
    device_num = log_extractor.find_device()
    os.popen3("adb shell getevent | grep event " + str(device_num))

main()
