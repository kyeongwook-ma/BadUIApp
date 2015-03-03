# -*- coding:utf-8 -*-
import sys, os, json, subprocess, re, signal
import datetime

trace_name = str(datetime.datetime.now()).replace(" ", "") + ".trace" 

def write_bm(conn, bm_dict):
    bm_cur = ((bm_dict["touch_class"], bm_dict["touch_mode"]))
    conn.execute("INSERT INTO BMTable (touch_class, touch_mode) values (?, ?)", bm_cur)
    conn.commit()

def parse(line, keyword):
    fragments = line.split("\t")

    try:
        if keyword in fragments[1]:
            return { "touch_class" : fragments[4], "touch_mode" : fragments[2]}
    except IndexError:
        pass

def get_pid(keyword):

    def extract_pid(ps_line):
        ps_re = re.compile("\s[0-9]+\s")
        return ps_re.findall(ps_line)[0]

    p = subprocess.Popen("adb shell ps | grep " + keyword, stdout=subprocess.PIPE, shell=True)
    ps_result = p.stdout.read().splitlines()
    for ps in ps_result:
        if keyword in ps:
            return extract_pid(ps)

def start_recording(pid):
    print "start recording"
    return os.system("adb shell am profile " + str(pid) + " start /sdcard/" + trace_name)

def stop_recording(pid):
    return os.system("adb shell am profile " + str(pid) + " stop")

def pull_trace():
    return os.system("adb pull /sdcard/" + trace_name)

def main():
    
    def signal_hadler(signal, frame):
        mutex = -1 

        while mutex != 0:
            stop_recording(pid)
            mutex = pull_trace()

        trace_file = open(trace_name, "r")

        for line in trace_file.readline():
            info_dict = parse(line, sys.argv[1])

            if info_dict is not None:
                write_bm(str(info_dict))

        sys.exit()

    if len(sys.argv) < 2:
        print('Usage : python trace_parser.py "keyword" ')
        sys.exit(1)
    
    pid = get_pid(sys.argv[1])
    start_recording(pid)
    signal.signal(signal.SIGINT, signal_hadler)
    
    while(True):
        pass

if __name__ == "__main__":
    main()
