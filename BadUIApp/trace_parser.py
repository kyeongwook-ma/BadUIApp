# -*- coding:utf-8 -*-
import sys, os, json, subprocess, re, signal


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
    subprocess.Popen("adb shell am start " + pid + " /sdcard/method.trace")

def stop_recording(pid):
    subprocess.Popen("adb shell am stop " + pid)

def pull_trace():
    subprocess.Popen("adb pull /sdcard/method.trace " + os.getcwd())

def main():
    
    if len(sys.argv) < 2:
        print('Usage : python trace_parser.py "keyword" ')
        sys.exit(1)
    
    pid = get_pid()
    start_recording(pid)
    
    signal.signal(signal.SIGINT, signal_hadler)
 
    def signal_hadler(signal, frame):
        stop_recording(pid)
        pull_trace()
        for line in lines[24:]:
            info_dict = parse(line, sys.argv[1])

            if info_dict is not None:
                write_bm(str(info_dict))

if __name__ == "__main__":
    main()
