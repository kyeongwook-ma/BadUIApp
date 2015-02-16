# -*- coding:utf-8 -*-
import sys, os, json, subprocess, re, signal

def get_lines(file_path):
    return list(open(file_path).read().splitlines())

def parse(line, keyword):
    fragments = line.split("\t")

    try:
        if keyword in fragments[1]:
            return { "file_name" : fragments[4], "method_name" : fragments[2]}
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

def main():
    
    if len(sys.argv) < 3:
        print('Usage : python trace_parser.py "tracefile path" "keyword" result.txt ')
        sys.exit(1)

    lines = get_lines(sys.argv[1])
    out = open(sys.argv[3], "w")
    
    pid = get_pid()
    
    subprocess.Popen("adb shell am start " + pid + " method.trace &") 
    signal.signal(signal.SIGINT, wrapup_record)
 
    def wrapup_record(signal, frame):
        for line in lines[24:]:
            info_dict = parse(line, sys.argv[2])

            if info_dict is not None:
                out.write(str(info_dict)+"\n")

if __name__ == "__main__":
    main()
